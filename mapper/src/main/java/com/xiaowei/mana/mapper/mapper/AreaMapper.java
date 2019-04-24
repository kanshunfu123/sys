package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.AreaDO;
import com.xiaowei.mana.mapper.resultmap.AreaTreeResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/8.
 */
public interface AreaMapper {
    /**
     * 根据id查询地区
     *
     * @param id
     * @return
     */
    public AreaDO getAreaByID(@Param("id") Long id);

    /**
     * 根据parentid 查询  区域
     * @param parentId
     * @return
     */
    public List<AreaDO> getAreaByParentId(@Param("parentId") Long parentId);

    /**
     * 根据区域uuid查询区域详情
     * @param sysAreaUuid
     * @return
     */
    AreaDO getAreaByUUID(@Param("sysAreaUuid") String sysAreaUuid);
    /**
     * 查看区域树
     * @return
     */
    public List<AreaTreeResult> AreaTree();
    /**
     * 根据uuid查询区域树
     * @param sysAreaUuid
     * @return
     */
    public AreaDO areaDo(@Param("sysAreaUuid") String sysAreaUuid);


}
