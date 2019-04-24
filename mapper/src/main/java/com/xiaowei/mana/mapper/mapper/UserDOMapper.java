package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_USER.
 * 用户
 */
public interface UserDOMapper{

    /**
     * desc:插入表:SYS_USER.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER( DEPT_ID ,GROUP_ID ,SYS_USER_AREA ,SYS_USER_CITY ,SYS_USER_PROVINCE ,DEL_FLAG ,NAME_TOP ,CREATE_BY ,ROLE_TYPE ,UPDATE_BY ,NAME_BOTTOM ,SYS_USER_PWD ,IS_FORBIDDEN ,SYS_USER_NAME ,SYS_USER_UUID ,SYS_USER_EMAIL ,SYS_USER_PHONE ,SYS_LOGIN_NUMBER ,SYS_USER_AUTH_SALT ,SYS_USER_LOGIN_NAME ,CREATE_TIME ,UPDATE_TIME )VALUES( #{deptId,jdbcType=BIGINT} ,#{groupId,jdbcType=BIGINT} ,#{sysUserArea,jdbcType=BIGINT} ,#{sysUserCity,jdbcType=BIGINT} ,#{sysUserProvince,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{nameTop,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{roleType,jdbcType=CHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{nameBottom,jdbcType=VARCHAR} ,#{sysUserPwd,jdbcType=VARCHAR} ,#{isForbidden,jdbcType=CHAR} ,#{sysUserName,jdbcType=VARCHAR} ,#{sysUserUuid,jdbcType=VARCHAR} ,#{sysUserEmail,jdbcType=VARCHAR} ,#{sysUserPhone,jdbcType=VARCHAR} ,#{sysLoginNumber,jdbcType=CHAR} ,#{sysUserAuthSalt,jdbcType=VARCHAR} ,#{sysUserLoginName,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(UserDO entity);
    /**
     * desc:更新表:SYS_USER.<br/>
     * descSql =  UPDATE SYS_USER SET DEPT_ID = #{deptId,jdbcType=BIGINT} ,GROUP_ID = #{groupId,jdbcType=BIGINT} ,SYS_USER_AREA = #{sysUserArea,jdbcType=BIGINT} ,SYS_USER_CITY = #{sysUserCity,jdbcType=BIGINT} ,SYS_USER_PROVINCE = #{sysUserProvince,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,NAME_TOP = #{nameTop,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,ROLE_TYPE = #{roleType,jdbcType=CHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,NAME_BOTTOM = #{nameBottom,jdbcType=VARCHAR} ,SYS_USER_PWD = #{sysUserPwd,jdbcType=VARCHAR} ,IS_FORBIDDEN = #{isForbidden,jdbcType=CHAR} ,SYS_USER_NAME = #{sysUserName,jdbcType=VARCHAR} ,SYS_USER_UUID = #{sysUserUuid,jdbcType=VARCHAR} ,SYS_USER_EMAIL = #{sysUserEmail,jdbcType=VARCHAR} ,SYS_USER_PHONE = #{sysUserPhone,jdbcType=VARCHAR} ,SYS_LOGIN_NUMBER = #{sysLoginNumber,jdbcType=CHAR} ,SYS_USER_AUTH_SALT = #{sysUserAuthSalt,jdbcType=VARCHAR} ,SYS_USER_LOGIN_NAME = #{sysUserLoginName,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(UserDO entity);
    /**
     * desc:根据主键删除数据:SYS_USER.<br/>
     * descSql =  DELETE FROM SYS_USER WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_USER.<br/>
     * descSql =  SELECT * FROM SYS_USER WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserDO
     */
    UserDO getById(Long id);
}
