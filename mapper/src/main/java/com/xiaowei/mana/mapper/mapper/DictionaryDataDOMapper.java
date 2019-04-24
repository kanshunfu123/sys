package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.DictionaryDataDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_DICTIONARY_DATA.
 * 数据字典
 */
public interface DictionaryDataDOMapper{

    /**
     * desc:插入表:SYS_DICTIONARY_DATA.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_DICTIONARY_DATA( PARENT_ID ,DEL_FLAG ,CODE_NAME ,CREATE_BY ,UPDATE_BY ,CODE_LEVEL ,SYS_DICTIONARY_UUID ,SYS_DICTIONARY_REMARK ,SYS_DICTIONARY_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{parentId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{codeName,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{codeLevel,jdbcType=VARCHAR} ,#{sysDictionaryUuid,jdbcType=VARCHAR} ,#{sysDictionaryRemark,jdbcType=VARCHAR} ,#{sysDictionarySeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(DictionaryDataDO entity);
    /**
     * desc:更新表:SYS_DICTIONARY_DATA.<br/>
     * descSql =  UPDATE SYS_DICTIONARY_DATA SET PARENT_ID = #{parentId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CODE_NAME = #{codeName,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,CODE_LEVEL = #{codeLevel,jdbcType=VARCHAR} ,SYS_DICTIONARY_UUID = #{sysDictionaryUuid,jdbcType=VARCHAR} ,SYS_DICTIONARY_REMARK = #{sysDictionaryRemark,jdbcType=VARCHAR} ,SYS_DICTIONARY_SEQ = #{sysDictionarySeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(DictionaryDataDO entity);
    /**
     * desc:根据主键删除数据:SYS_DICTIONARY_DATA.<br/>
     * descSql =  DELETE FROM SYS_DICTIONARY_DATA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_DICTIONARY_DATA.<br/>
     * descSql =  SELECT * FROM SYS_DICTIONARY_DATA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return DictionaryDataDO
     */
    DictionaryDataDO getById(Long id);
    /**
     * desc:新增表:sys_dictionary_data.<br/>
     * descSql =  insert into sys_dictionary_data id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by, code_value, #{id,jdbcType=BIGINT}, #{sysDictionaryUuid,jdbcType=VARCHAR}, #{codeName,jdbcType=VARCHAR}, #{parentId,jdbcType=BIGINT}, #{codeLevel,jdbcType=VARCHAR}, #{sysDictionarySeq,jdbcType=INTEGER}, #{sysDictionaryRemark,jdbcType=VARCHAR}, #{delFlag,jdbcType=CHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{codeValue,jdbcType=BIGINT}, 
     * @param entity entity
     * @return DictionaryDataDO
     */
    DictionaryDataDO saveDictionary(DictionaryDataDO entity);
    /**
     * desc:根据 字典代码名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data and code_name=#{codeName,jdbcType=VARCHAR} and sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} and parent_id=#{parentId,jdbcType=BIGINT} AND del_flag=0 
     * @param entity entity
     * @return Integer
     */
    Integer getDictionaryCodeName(DictionaryDataDO entity);
    /**
     * desc:根据 字典代码名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data AND code_value=#{codeValue,jdbcType=BIGINT} AND sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} AND del_flag=0 
     * @param entity entity
     * @return Integer
     */
    Integer getDictionaryCodeValue(DictionaryDataDO entity);
    /**
     * desc:根据 字典代码名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data AND id=#{id,jdbcType=BIGINT} AND sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    Integer getDictionaryId(DictionaryDataDO entity);
    /**
     * desc:根据 字典代码值 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data AND code_value=#{codeValue,jdbcType=BIGINT} AND sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} AND del_flag=0 
     * @param entity entity
     * @return Integer
     */
    Integer getDicId(DictionaryDataDO entity);
    /**
     * desc:根据 父级id 获取数据:SYS_DICTIONARY.<br/>
     * descSql =  SELECT id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by FROM sys_dictionary_data WHERE id=#{id,jdbcType=BIGINT} and del_flag=0
     * @param id id
     * @return DictionaryDataDO
     */
    DictionaryDataDO getLevelByParentId(Long id);
    /**
     * desc:同一层级下，代码名称是否相同.<br/>
     * descSql =  SELECT COUNT(id) FROM sys_dictionary_data AND code_name=#{codeName,jdbcType=VARCHAR} AND parent_id=#{parentId,jdbcType=BIGINT} AND del_flag=0 
     * @param parentId parentId
     * @param codeName codeName
     * @return int
     */
    int getBycodeNameAndByparentId(@Param("parentId")Long parentId,@Param("codeName")String codeName);
    /**
     * desc:查询子级字典.<br/>
     * descSql =  SELECT id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by FROM sys_dictionary_data WHERE code_level LIKE CONCAT( #{level,jdbcType=VARCHAR}, '%') AND del_flag=0
     * @param level level
     * @return List<DictionaryDataDO>
     */
    List<DictionaryDataDO> getChildDictionaryListByLevel(String level);
    /**
     * desc:更新字典信息.<br/>
     * descSql =  update sys_dictionary_data sys_dictionary_uuid = #{sysDictionaryUuid,jdbcType=VARCHAR}, code_name = #{codeName,jdbcType=VARCHAR}, parent_id = #{parentId,jdbcType=BIGINT}, code_level = #{codeLevel,jdbcType=VARCHAR}, sys_dictionary_seq = #{sysDictionarySeq,jdbcType=INTEGER}, sys_dictionary_remark = #{sysDictionaryRemark,jdbcType=VARCHAR}, del_flag = #{delFlag,jdbcType=CHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_time = #{updateTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, code_value = #{codeValue,jdbcType=BIGINT}, where sys_dictionary_uuid = #{sysDictionaryUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    int updateDictionary(DictionaryDataDO entity);
    /**
     * desc:字典更新之后，进行批量子级字典.<br/>
     * descSql =  UPDATE sys_dictionary_data SET CODE_LEVEL = #{sysDictionary.codeLevel,jdbcType=VARCHAR} ,CREATE_BY = #{sysDictionary.createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{sysDictionary.updateBy,jdbcType=VARCHAR} ,CREATE_TIME = #{sysDictionary.createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{sysDictionary.updateTime,jdbcType=TIMESTAMP} WHERE id = #{sysDictionary.id,jdbcType=BIGINT} 
     * @param list list
     * @return int
     */
    int updatebatchUpdateLevel(List<DictionaryDataDO> list);
    /**
     * desc:根据parentId查询.<br/>
     * descSql =  SELECT id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by,code_value FROM sys_dictionary_data WHERE parent_id=#{parentId,jdbcType=BIGINT} AND del_flag=0
     * @param parentId parentId
     * @return List<DictionaryDataDO>
     */
    List<DictionaryDataDO> getDictionaryInfoByParentId(Long parentId);
}
