package com.xiaowei.service.village;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.VillageInfoReq;
import com.xiaowei.common.req.village.InsertStreetReq;
import com.xiaowei.common.req.village.InsertVillageReq;
import com.xiaowei.common.req.village.VaPageListReq;
import com.xiaowei.common.res.ParameterPageListRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.res.vi.VillagePageRes;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.ParameterConfigurationDO;
import com.xiaowei.mana.mapper.dataobject.StreetDO;
import com.xiaowei.mana.mapper.dataobject.VillageDO;
import com.xiaowei.mana.mapper.dataobject.XwVillageDO;
import com.xiaowei.mana.mapper.mapper.StreetMapper;
import com.xiaowei.mana.mapper.mapper.VillageMapper;
import com.xiaowei.mana.mapper.mapper.XwVillageMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOMO on 2019/1/9.
 */
@Service
@Transactional
public class VillageService {
    @Autowired
    private VillageMapper villageMapper;
    @Autowired
    private StreetMapper streetMapper;

    /**
     * 主键查询小区
     *
     * @param villageInfoReq
     * @return
     */
    public JSONResult selectByPrimaryKey(VillageInfoReq villageInfoReq) {
        return JSONResult.ok(villageMapper.selectByPrimaryKey(villageInfoReq.getId()));
    }

    /**
     * 新增小区
     *
     * @param insertVillageReq
     * @param redisUser
     * @return
     */
    public JSONResult insert(InsertVillageReq insertVillageReq, RedisUser redisUser) {
        VillageDO record = new VillageDO();
        BeanUtils.copyProperties(insertVillageReq, record);
        record.setVillageUuid(StrUtil.genUUID());
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getCurrentDateTime());
        record.setUpdateTime(DateUtils.getCurrentDateTime());
        villageMapper.insertSelective(record);
        return JSONResult.ok("新增小区成功");
    }

    /**
     * 编辑小区
     *
     * @param insertVillageReq
     * @param redisUser
     * @return
     */
    public JSONResult modify(InsertVillageReq insertVillageReq, RedisUser redisUser) {
        VillageDO xwVillageDO = villageMapper.getVillageByUUID(insertVillageReq.getVillageUuid());
        if (null == xwVillageDO) {
            return JSONResult.errorMap("待编辑的小区不存在");
        }
        VillageDO record = new VillageDO();
        BeanUtils.copyProperties(insertVillageReq, record);
        record.setId(xwVillageDO.getId());
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getCurrentDateTime());
        record.setUpdateTime(DateUtils.getCurrentDateTime());
        villageMapper.updateByPrimaryKey(record);
        return JSONResult.ok("编辑小区成功");
    }
    /**
     * 查看小区
     *
     * @param insertVillageReq
     * @return
     */
    public JSONResult uuidVillage(InsertVillageReq insertVillageReq) {
        VillageDO xwVillageDO = villageMapper.getVillageByUUID(insertVillageReq.getVillageUuid());
        if (null == xwVillageDO) {
            return JSONResult.errorMap("待查看的小区不存在");
        }
       return JSONResult.ok(xwVillageDO);
    }

    /**
     * 新增街道
     *
     * @param insertVillageReq
     * @param redisUser
     * @return
     */
    public JSONResult insertStreet(InsertStreetReq insertVillageReq, RedisUser redisUser) {
        StreetDO record = new StreetDO();
        BeanUtils.copyProperties(insertVillageReq, record);
        record.setStreetUuid(StrUtil.genUUID());
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getCurrentDateTime());
        record.setUpdateTime(DateUtils.getCurrentDateTime());
        streetMapper.insertSelective(record);
        return JSONResult.ok("新增街道成功");
    }
    /**
     * 编辑小区
     *
     * @param insertVillageReq
     * @param redisUser
     * @return
     */
    public JSONResult modifyStreet(InsertStreetReq insertVillageReq, RedisUser redisUser) {
        StreetDO xwVillageDO = streetMapper.getStreetByUUID(insertVillageReq.getStreetUuid());
        if (null == xwVillageDO) {
            return JSONResult.errorMap("待编辑的街道不存在");
        }
        StreetDO record = new StreetDO();
        BeanUtils.copyProperties(insertVillageReq, record);
        record.setId(xwVillageDO.getId());
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getCurrentDateTime());
        record.setUpdateTime(DateUtils.getCurrentDateTime());
        streetMapper.updateByPrimaryKeySelective(record);
        return JSONResult.ok("编辑街道成功");
    }
    /**
     * 查看街道
     *
     * @param insertVillageReq
     * @return
     */
    public JSONResult uuidStreet(InsertStreetReq insertVillageReq) {
        StreetDO xwVillageDO = streetMapper.getStreetByUUID(insertVillageReq.getStreetUuid());
        if (null == xwVillageDO) {
            return JSONResult.errorMap("待查看的街道不存在");
        }
        return JSONResult.ok(xwVillageDO);
    }
    /**
     * 小区列表分页
     */
    public JSONResult pageList(VaPageListReq vaPageListReq, RedisUser redisUser){
        PageHelper.startPage(vaPageListReq.getCurrPageNo(), vaPageListReq.getLimit());
        List<VillageDO> list = villageMapper.pageListVi(vaPageListReq.getVillageName());
        PageInfo<VillageDO> pageInfo1 = new PageInfo<>(list);
        //分页后处理
        List<VillageDO> villageDOS = pageInfo1.getList();

        List<VillagePageRes> villagePageResList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(villageDOS)){
            villageDOS.forEach(villageDO -> {
                VillagePageRes villagePageRes=new VillagePageRes();
                villagePageRes.setCreateTime(DateUtil.dateToString2(villageDO.getCreateTime()));
                if (StringUtils.isNotBlank(villageDO.getAddress())) {
                    villagePageRes.setLocation(villageDO.getProvinceName() + villageDO.getCity() + villageDO.getArea() +villageDO.getStreet()+villageDO.getVillageName()+ villageDO.getAddress());
                } else {
                    villagePageRes.setLocation(villageDO.getProvinceName() + villageDO.getCity() + villageDO.getArea()+villageDO.getStreet()+villageDO.getVillageName());
                }
                villagePageRes.setVillageType(villageDO.getVillageType());
                BeanUtils.copyProperties(villageDO,villagePageRes);
                villagePageResList.add(villagePageRes);
            });
        }
        PageInfo<VillagePageRes> pageInfo = new PageInfo<>(villagePageResList);
        pageInfo.setPageNum(vaPageListReq.getCurrPageNo());
        pageInfo.setTotal(pageInfo1.getTotal());
        return JSONResult.ok(pageInfo);

    }
}
