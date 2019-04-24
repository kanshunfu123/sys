package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.TestDbDO;
import java.util.List;
import com.xiaowei.mana.mapper.mapper.TestDbDOMapper;

/**
* The Table TEST_DB.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* TEST_DB
*/
@Repository
public class TestDbDAO{

    @Autowired
    private TestDbDOMapper testDbDOMapper;

    /**
     * desc:插入表:TEST_DB.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO test_db( NAME )VALUES( #{name,jdbcType=VARCHAR} )
     * @param entity entity
     * @return int
     */
    public int insert(TestDbDO entity){
        return testDbDOMapper.insert(entity);
    }
    /**
     * desc:更新表:TEST_DB.<br/>
     * descSql =  UPDATE TEST_DB SET NAME = #{name,jdbcType=VARCHAR} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(TestDbDO entity){
        return testDbDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:TEST_DB.<br/>
     * descSql =  DELETE FROM TEST_DB WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return testDbDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:TEST_DB.<br/>
     * descSql =  SELECT * FROM TEST_DB WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return TestDbDO
     */
    public TestDbDO getById(Long id){
        return testDbDOMapper.getById(id);
    }
    /**
     * desc:testPage.<br/>
     * descSql =  select * from test_db
     * @return List<TestDbDO>
     */
    public List<TestDbDO> testPage(){
        return testDbDOMapper.testPage();
    }
    /**
     * desc:新增.<br/>
     * descSql =  insert into test_db name, #{name,jdbcType=VARCHAR}, 
     * @param entity entity
     * @return TestDbDO
     */
    public TestDbDO add(TestDbDO entity){
        return testDbDOMapper.add(entity);
    }
    /**
     * desc:根据 名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM test_db name=#{name,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    public Integer getName(TestDbDO entity){
        return testDbDOMapper.getName(entity);
    }
}
