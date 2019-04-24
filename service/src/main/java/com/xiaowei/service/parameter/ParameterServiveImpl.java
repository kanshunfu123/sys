package com.xiaowei.service.parameter;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.ParameterAddReq;
import com.xiaowei.common.req.ParameterByUuidReq;
import com.xiaowei.common.req.ParameterPageList;
import com.xiaowei.common.req.ParameterReq;
import com.xiaowei.common.res.ParameterPageListRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.common.vo.ParameterVo;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.DictionaryDataDAO;
import com.xiaowei.mana.mapper.dao.ParameterConfigurationDAO;
import com.xiaowei.mana.mapper.dataobject.DictionaryDataDO;
import com.xiaowei.mana.mapper.dataobject.ParameterConfigurationDO;
import com.xiaowei.mana.mapper.mapper.ParameterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanshunfu on 2019/01/08.
 */

@Service
@Slf4j
public class ParameterServiveImpl implements ParameterService {
    @Autowired
    private DictionaryDataDAO dictionaryDataDAO;
    @Autowired
    private ParameterConfigurationDAO parameterConfigurationDAO;
    @Autowired
    private ParameterMapper parameterMapper;

    @Override
    public JSONResult interParameter(ParameterAddReq parameterAddReq, RedisUser redisUser) {
        ParameterConfigurationDO parameterConfigurationDO = new ParameterConfigurationDO();
        BeanUtils.copyProperties(parameterAddReq, parameterConfigurationDO);
        List<ParameterConfigurationDO> s1 = parameterConfigurationDAO.getCountsByName(parameterConfigurationDO.getParameterName());
        if (s1.size() > 0) {
            return JSONResult.errorMap("新增参数参数名称重复");
        }
        DictionaryDataDO dictionaryDataDO = new DictionaryDataDO();
        dictionaryDataDO.setCodeValue(parameterConfigurationDO.getDictionaryId());
        int count = dictionaryDataDAO.getDicId(dictionaryDataDO);
        if (count <= 0) {
            return JSONResult.errorMap("上级字典id不存在");
        }
        parameterConfigurationDO.setCreateTime(DateUtils.getCurrentDateTime());
        parameterConfigurationDO.setCreateBy(redisUser.getSysUserName());
        parameterConfigurationDO.setUpdateTime(DateUtils.getCurrentDateTime());
        parameterConfigurationDO.setUpdateBy(redisUser.getSysUserName());
        parameterConfigurationDO.setSysParameterUuid(StrUtil.genUUID());
        parameterConfigurationDO.setDelFlag("0");
        parameterConfigurationDAO.interParameter(parameterConfigurationDO);
        return JSONResult.ok("添加参数成功", parameterConfigurationDO.getSysParameterUuid());
    }

    @Override
    public JSONResult pageList(ParameterPageList parameterPageList, RedisUser redisUser) {
        PageHelper.startPage(parameterPageList.getCurrPageNo(), parameterPageList.getLimit());
        List<ParameterConfigurationDO> list = parameterMapper.pageList(parameterPageList.getDictionaryId());
        PageInfo<ParameterConfigurationDO> pageInfo1 = new PageInfo<>(list);
        //分页后处理
        List<ParameterConfigurationDO> parameterConfigurationDOS = pageInfo1.getList();

        List<ParameterPageListRes> parameterPageListRes = new ArrayList<>();
        if(!CollectionUtils.isEmpty(parameterConfigurationDOS)){
            parameterConfigurationDOS.forEach(parameterConfigurationDO -> {
                ParameterPageListRes parameterPageListRes1=new ParameterPageListRes();
                parameterPageListRes1.setDelFlag("0");
                BeanUtils.copyProperties(parameterConfigurationDO,parameterPageListRes1);
                parameterPageListRes.add(parameterPageListRes1);
            });
        }
        PageInfo<ParameterPageListRes> pageInfo = new PageInfo<>(parameterPageListRes);
        pageInfo.setPageNum(parameterPageList.getCurrPageNo());
        pageInfo.setTotal(pageInfo1.getTotal());
        return JSONResult.ok(pageInfo);
    }

    @Override
    public JSONResult updateParameter(ParameterAddReq parameterAddReq, RedisUser redisUser) {
        ParameterConfigurationDO parameterConfigurationDO = new ParameterConfigurationDO();
        BeanUtils.copyProperties(parameterAddReq, parameterConfigurationDO);
        ParameterConfigurationDO before = parameterConfigurationDAO.getByUuid(parameterAddReq.getSysParameterUuid());

        if (before == null) {
            return JSONResult.errorMap("编辑参数信息不存在");
        }
        List<ParameterConfigurationDO> s1 = parameterConfigurationDAO.getEditCountsByName(before.getId(), parameterConfigurationDO.getParameterName());
        if (s1.size() > 0) {
            return JSONResult.errorMap("编辑参数参数名称重复");
        }
        DictionaryDataDO dictionaryDataDO = new DictionaryDataDO();
        dictionaryDataDO.setCodeValue(parameterConfigurationDO.getDictionaryId());
        int count = dictionaryDataDAO.getDicId(dictionaryDataDO);
        if (count <= 0) {
            return JSONResult.errorMap("上级字典id不存在");
        }
        ParameterConfigurationDO after = new ParameterConfigurationDO();
        BeanUtils.copyProperties(parameterAddReq, after);
        after.setDelFlag("0");
        after.setUpdateBy(redisUser.getSysUserName());
        after.setUpdateTime(DateUtils.getCurrentDateTime());
        after.setCreateBy(redisUser.getSysUserName());
        after.setCreateTime(DateUtils.getCurrentDateTime());
        parameterConfigurationDAO.updateParameter(after);
        return JSONResult.ok("更新参数成功");
    }

    @Override
    public JSONResult getInfoByUuid(ParameterByUuidReq parameterByUuidReq) {
        ParameterConfigurationDO parameterConfigurationDO = parameterConfigurationDAO.getByUuid(parameterByUuidReq.getSysParameterUuid());
        if (null == parameterConfigurationDO) {
            return JSONResult.errorMap("查询参数信息异常");
        }
        ParameterVo parameterVo = new ParameterVo();
        if (parameterConfigurationDO != null) {
            BeanUtils.copyProperties(parameterConfigurationDO, parameterVo);
        }
        return JSONResult.ok(parameterVo);
    }

    @Override
    public JSONResult delParameter(ParameterReq parameterReq) {
        ParameterConfigurationDO parameterConfigurationDO = parameterConfigurationDAO.getByUuid(parameterReq.getSysParameterUuid());
        if (null == parameterConfigurationDO) {
            return JSONResult.errorMap("待删除的参数不存在，您无法删除");
        }
        ParameterConfigurationDO parameterConfigurationDO1 = new ParameterConfigurationDO();
        parameterConfigurationDO1.setSysParameterUuid(parameterReq.getSysParameterUuid());
        parameterConfigurationDO1.setDelFlag("1");
        parameterConfigurationDO1.setUpdateTime(DateUtil.getDateTime());
        parameterConfigurationDO1.setUpdateBy("sys");
        parameterConfigurationDAO.updateParameter(parameterConfigurationDO1);
        return JSONResult.ok("删除参数成功");
    }


}
