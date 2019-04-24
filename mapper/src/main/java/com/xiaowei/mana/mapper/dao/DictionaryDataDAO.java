package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.DictionaryDataDO;
import java.util.List;
import com.xiaowei.mana.mapper.mapper.DictionaryDataDOMapper;

/**
* The Table SYS_DICTIONARY_DATA.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 数据字典
*/
@Repository
public class DictionaryDataDAO{

    @Autowired
    private DictionaryDataDOMapper dictionaryDataDOMapper;

    /**
     * desc:插入表:SYS_DICTIONARY_DATA.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_DICTIONARY_DATA( PARENT_ID ,DEL_FLAG ,CODE_NAME ,CREATE_BY ,UPDATE_BY ,CODE_LEVEL ,SYS_DICTIONARY_UUID ,SYS_DICTIONARY_REMARK ,SYS_DICTIONARY_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{parentId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{codeName,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{codeLevel,jdbcType=VARCHAR} ,#{sysDictionaryUuid,jdbcType=VARCHAR} ,#{sysDictionaryRemark,jdbcType=VARCHAR} ,#{sysDictionarySeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(DictionaryDataDO entity){
        return dictionaryDataDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_DICTIONARY_DATA.<br/>
     * descSql =  UPDATE SYS_DICTIONARY_DATA SET PARENT_ID = #{parentId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CODE_NAME = #{codeName,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,CODE_LEVEL = #{codeLevel,jdbcType=VARCHAR} ,SYS_DICTIONARY_UUID = #{sysDictionaryUuid,jdbcType=VARCHAR} ,SYS_DICTIONARY_REMARK = #{sysDictionaryRemark,jdbcType=VARCHAR} ,SYS_DICTIONARY_SEQ = #{sysDictionarySeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(DictionaryDataDO entity){
        return dictionaryDataDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_DICTIONARY_DATA.<br/>
     * descSql =  DELETE FROM SYS_DICTIONARY_DATA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return dictionaryDataDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_DICTIONARY_DATA.<br/>
     * descSql =  SELECT * FROM SYS_DICTIONARY_DATA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return DictionaryDataDO
     */
    public DictionaryDataDO getById(Long id){
        return dictionaryDataDOMapper.getById(id);
    }
    /**
     * desc:新增表:sys_dictionary_data.<br/>
     * descSql =  insert into sys_dictionary_data id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by, code_value, #{id,jdbcType=BIGINT}, #{sysDictionaryUuid,jdbcType=VARCHAR}, #{codeName,jdbcType=VARCHAR}, #{parentId,jdbcType=BIGINT}, #{codeLevel,jdbcType=VARCHAR}, #{sysDictionarySeq,jdbcType=INTEGER}, #{sysDictionaryRemark,jdbcType=VARCHAR}, #{delFlag,jdbcType=CHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{codeValue,jdbcType=BIGINT}, 
     * @param entity entity
     * @return DictionaryDataDO
     */
    public DictionaryDataDO saveDictionary(DictionaryDataDO entity){
        return dictionaryDataDOMapper.saveDictionary(entity);
    }
    /**
     * desc:根据 字典代码名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data and code_name=#{codeName,jdbcType=VARCHAR} and sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} and parent_id=#{parentId,jdbcType=BIGINT} AND del_flag=0 
     * @param entity entity
     * @return Integer
     */
    public Integer getDictionaryCodeName(DictionaryDataDO entity){
        return dictionaryDataDOMapper.getDictionaryCodeName(entity);
    }
    /**
     * desc:根据 字典代码名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data AND code_value=#{codeValue,jdbcType=BIGINT} AND sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} AND del_flag=0 
     * @param entity entity
     * @return Integer
     */
    public Integer getDictionaryCodeValue(DictionaryDataDO entity){
        return dictionaryDataDOMapper.getDictionaryCodeValue(entity);
    }
    /**
     * desc:根据 字典代码名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data AND id=#{id,jdbcType=BIGINT} AND sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    public Integer getDictionaryId(DictionaryDataDO entity){
        return dictionaryDataDOMapper.getDictionaryId(entity);
    }
    /**
     * desc:根据 字典代码值 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_dictionary_data AND code_value=#{codeValue,jdbcType=BIGINT} AND sys_dictionary_uuid=#{sysDictionaryUuid,jdbcType=VARCHAR} AND del_flag=0 
     * @param entity entity
     * @return Integer
     */
    public Integer getDicId(DictionaryDataDO entity){
        return dictionaryDataDOMapper.getDicId(entity);
    }
    /**
     * desc:根据 父级id 获取数据:SYS_DICTIONARY.<br/>
     * descSql =  SELECT id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by FROM sys_dictionary_data WHERE id=#{id,jdbcType=BIGINT} and del_flag=0
     * @param id id
     * @return DictionaryDataDO
     */
    public DictionaryDataDO getLevelByParentId(Long id){
        return dictionaryDataDOMapper.getLevelByParentId(id);
    }
    /**
     * desc:同一层级下，代码名称是否相同.<br/>
     * descSql =  SELECT COUNT(id) FROM sys_dictionary_data AND code_name=#{codeName,jdbcType=VARCHAR} AND parent_id=#{parentId,jdbcType=BIGINT} AND del_flag=0 
     * @param parentId parentId
     * @param codeName codeName
     * @return int
     */
    public int getBycodeNameAndByparentId(Long parentId,String codeName){
        return dictionaryDataDOMapper.getBycodeNameAndByparentId(parentId, codeName);
    }
    /**
     * desc:查询子级字典.<br/>
     * descSql =  SELECT id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by FROM sys_dictionary_data WHERE code_level LIKE CONCAT( #{level,jdbcType=VARCHAR}, '%') AND del_flag=0
     * @param level level
     * @return List<DictionaryDataDO>
     */
    public List<DictionaryDataDO> getChildDictionaryListByLevel(String level){
        return dictionaryDataDOMapper.getChildDictionaryListByLevel(level);
    }
    /**
     * desc:更新字典信息.<br/>
     * descSql =  update sys_dictionary_data sys_dictionary_uuid = #{sysDictionaryUuid,jdbcType=VARCHAR}, code_name = #{codeName,jdbcType=VARCHAR}, parent_id = #{parentId,jdbcType=BIGINT}, code_level = #{codeLevel,jdbcType=VARCHAR}, sys_dictionary_seq = #{sysDictionarySeq,jdbcType=INTEGER}, sys_dictionary_remark = #{sysDictionaryRemark,jdbcType=VARCHAR}, del_flag = #{delFlag,jdbcType=CHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_time = #{updateTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, code_value = #{codeValue,jdbcType=BIGINT}, where sys_dictionary_uuid = #{sysDictionaryUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    public int updateDictionary(DictionaryDataDO entity){
        return dictionaryDataDOMapper.updateDictionary(entity);
    }
    /**
     * desc:字典更新之后，进行批量子级字典.<br/>
     * descSql =  UPDATE sys_dictionary_data SET CODE_LEVEL = #{sysDictionary.codeLevel,jdbcType=VARCHAR} ,CREATE_BY = #{sysDictionary.createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{sysDictionary.updateBy,jdbcType=VARCHAR} ,CREATE_TIME = #{sysDictionary.createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{sysDictionary.updateTime,jdbcType=TIMESTAMP} WHERE id = #{sysDictionary.id,jdbcType=BIGINT} 
     * @param list list
     * @return int
     */
    public int updatebatchUpdateLevel(List<DictionaryDataDO> list){
        return dictionaryDataDOMapper.updatebatchUpdateLevel(list);
    }
    /**
     * desc:根据parentId查询.<br/>
     * descSql =  SELECT id, sys_dictionary_uuid, code_name, parent_id, code_level, sys_dictionary_seq, sys_dictionary_remark, del_flag, create_by, create_time, update_time, update_by,code_value FROM sys_dictionary_data WHERE parent_id=#{parentId,jdbcType=BIGINT} AND del_flag=0
     * @param parentId parentId
     * @return List<DictionaryDataDO>
     */
    public List<DictionaryDataDO> getDictionaryInfoByParentId(Long parentId){
        return dictionaryDataDOMapper.getDictionaryInfoByParentId(parentId);
    }
}
