package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.TestDbDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table TEST_DB.
 * TEST_DB
 */
public interface TestDbDOMapper{

    /**
     * desc:插入表:TEST_DB.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO test_db( NAME )VALUES( #{name,jdbcType=VARCHAR} )
     * @param entity entity
     * @return int
     */
    int insert(TestDbDO entity);
    /**
     * desc:更新表:TEST_DB.<br/>
     * descSql =  UPDATE TEST_DB SET NAME = #{name,jdbcType=VARCHAR} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(TestDbDO entity);
    /**
     * desc:根据主键删除数据:TEST_DB.<br/>
     * descSql =  DELETE FROM TEST_DB WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:TEST_DB.<br/>
     * descSql =  SELECT * FROM TEST_DB WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return TestDbDO
     */
    TestDbDO getById(Long id);
    /**
     * desc:testPage.<br/>
     * descSql =  select * from test_db
     * @return List<TestDbDO>
     */
    List<TestDbDO> testPage();
    /**
     * desc:新增.<br/>
     * descSql =  insert into test_db name, #{name,jdbcType=VARCHAR}, 
     * @param entity entity
     * @return TestDbDO
     */
    TestDbDO add(TestDbDO entity);
    /**
     * desc:根据 名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM test_db name=#{name,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    Integer getName(TestDbDO entity);
}
