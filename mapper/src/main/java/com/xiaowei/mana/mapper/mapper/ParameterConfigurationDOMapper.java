package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.ParameterConfigurationDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_PARAMETER_CONFIGURATION.
 * 参数配置
 */
public interface ParameterConfigurationDOMapper{

    /**
     * desc:插入表:SYS_PARAMETER_CONFIGURATION.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_PARAMETER_CONFIGURATION( DICTIONARY_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,PARAMETER_NAME ,SYS_PARAMETER_UUID ,SYS_PARAMETER_REMARK ,SYS_PARAMETER_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{dictionaryId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{parameterName,jdbcType=VARCHAR} ,#{sysParameterUuid,jdbcType=VARCHAR} ,#{sysParameterRemark,jdbcType=VARCHAR} ,#{sysParameterSeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(ParameterConfigurationDO entity);
    /**
     * desc:更新表:SYS_PARAMETER_CONFIGURATION.<br/>
     * descSql =  UPDATE SYS_PARAMETER_CONFIGURATION SET DICTIONARY_ID = #{dictionaryId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,PARAMETER_NAME = #{parameterName,jdbcType=VARCHAR} ,SYS_PARAMETER_UUID = #{sysParameterUuid,jdbcType=VARCHAR} ,SYS_PARAMETER_REMARK = #{sysParameterRemark,jdbcType=VARCHAR} ,SYS_PARAMETER_SEQ = #{sysParameterSeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(ParameterConfigurationDO entity);
    /**
     * desc:根据主键删除数据:SYS_PARAMETER_CONFIGURATION.<br/>
     * descSql =  DELETE FROM SYS_PARAMETER_CONFIGURATION WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_PARAMETER_CONFIGURATION.<br/>
     * descSql =  SELECT * FROM SYS_PARAMETER_CONFIGURATION WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return ParameterConfigurationDO
     */
    ParameterConfigurationDO getById(Long id);
    /**
     * desc:根据 参数 参数值 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_parameter_configuration AND dictionary_id=#{dictionaryId,jdbcType=BIGINT} AND del_flag=0 
     * @param entity entity
     * @return Integer
     */
    Integer getCountFieldValue(ParameterConfigurationDO entity);
    /**
     * desc:更新表:sys_parameter_configuration.<br/>
     * descSql =  insert into sys_parameter_configuration id, sys_parameter_uuid, parameter_name, sys_parameter_seq, del_flag, sys_parameter_remark, dictionary_id, create_by, create_time, update_time, update_by, #{id,jdbcType=BIGINT}, #{sysParameterUuid,jdbcType=VARCHAR}, #{parameterName,jdbcType=VARCHAR}, #{sysParameterSeq,jdbcType=INTEGER}, #{delFlag,jdbcType=CHAR}, #{sysParameterRemark,jdbcType=VARCHAR}, #{dictionaryId,jdbcType=BIGINT}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, 
     * @param entity entity
     * @return ParameterConfigurationDO
     */
    ParameterConfigurationDO interParameter(ParameterConfigurationDO entity);
    /**
     * desc:查询已存在的数量.<br/>
     * descSql =  select * from sys_parameter_configuration where parameter_name=#{parameterName,jdbcType=VARCHAR} AND del_flag=0
     * @param parameterName parameterName
     * @return List<ParameterConfigurationDO>
     */
    List<ParameterConfigurationDO> getCountsByName(String parameterName);
    /**
     * desc:根据dictionId查看参数信息.<br/>
     * descSql =  SELECT id, sys_parameter_uuid, parameter_name, sys_parameter_seq, del_flag, sys_parameter_remark, dictionary_id, create_by, create_time, update_time, update_by FROM sys_parameter_configuration WHERE dictionary_id=#{dictionaryId,jdbcType=BIGINT} AND del_flag=0 order by sys_parameter_seq
     * @param dictionaryId dictionaryId
     * @return List<ParameterConfigurationDO>
     */
    List<ParameterConfigurationDO> getParameterInfoByFieldValue(Long dictionaryId);
    /**
     * desc:更新参数表.<br/>
     * descSql =  update sys_parameter_configuration sys_parameter_uuid = #{sysParameterUuid,jdbcType=VARCHAR}, parameter_name = #{parameterName,jdbcType=VARCHAR}, sys_parameter_seq = #{sysParameterSeq,jdbcType=INTEGER}, del_flag = #{delFlag,jdbcType=CHAR}, sys_parameter_remark = #{sysParameterRemark,jdbcType=VARCHAR}, dictionary_id = #{dictionaryId,jdbcType=BIGINT}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_time = #{updateTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, where sys_parameter_uuid=#{sysParameterUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    int updateParameter(ParameterConfigurationDO entity);
    /**
     * desc:根据字段值获取数据:sys_parameter_configuration.<br/>
     * descSql =  SELECT id, sys_parameter_uuid, parameter_name, sys_parameter_seq, del_flag, sys_parameter_remark, dictionary_id, create_by, create_time, update_time, update_by FROM sys_parameter_configuration WHERE sys_parameter_uuid = #{sysParameterUuid,jdbcType=VARCHAR} and del_flag=0
     * @param sysParameterUuid sysParameterUuid
     * @return ParameterConfigurationDO
     */
    ParameterConfigurationDO getByUuid(String sysParameterUuid);
    /**
     * desc:编辑时,查询已存在的数量,排除自身.<br/>
     * descSql =  select id, sys_parameter_uuid, parameter_name, sys_parameter_seq, del_flag, sys_parameter_remark, dictionary_id, create_by, create_time, update_time, update_by from sys_parameter_configuration where parameter_name=#{parameterName,jdbcType=VARCHAR} AND id!=#{id,jdbcType=BIGINT}
     * @param id id
     * @param parameterName parameterName
     * @return List<ParameterConfigurationDO>
     */
    List<ParameterConfigurationDO> getEditCountsByName(@Param("id")Long id,@Param("parameterName")String parameterName);
}
