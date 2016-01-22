package com.xxx.dao;

import com.xxx.bean.HrInfo;
import com.xxx.commons.model.Hr;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 */
@DAO(catalog = "show_zhan")
public interface HrDAO {

    @SQL("SELECT uid FROM hr_user where role_id=:1 limit 1")
    public Integer findIdByRoleId(int rid);

    @SQL("SELECT uid, name, role_id, customer_id, status, is_superman FROM hr_user WHERE uid=:1")
    public Hr findUserByUid(int id);

    @SQL("SELECT uid, name, role_id, status, customer_id, is_superman, `desc` FROM hr_user WHERE uid=:1")
    public HrInfo findUserDetailByUid(int id);

    @SQL("SELECT customer_id FROM hr_user WHERE uid=:1")
    public Integer findCustomerIdByUid(int uid);

    @SQL("SELECT name FROM hr_user WHERE uid=:1")
    public String findUsernameByUid(int uid);

    @SQL("INSERT INTO hr_user(customer_id, uid, name, role_id, status, is_superman,`desc`, create_time) " +
            "VALUES (:1, :2, :3, :4, :5, :6, :7, now())"
    )
    public int addUser(int CustomerId, int uid, String name, int roleId, byte status, byte isSuper, String description);


    /**
     * 修改用户的状态
     *
     * @param uid
     * @param status
     * @return
     */
    @SQL("UPDATE hr_user SET status=:2  WHERE uid=:1")
    public int updateUserStatus(int uid, byte status);

    @SQL("select count(*) from hr_user where customer_id=:1 and is_superman=0 and status in (0,1)")
    int getAllUserCount(int CustomerId);

    /**
     * 查询公司下的用户，采用分页，按照创建时间倒排
     *
     * @param CustomerId
     * @param offset
     * @param pageSize
     * @return
     */
    @SQL("select uid, name, role_id, status, `desc` from hr_user where customer_id=:1 and is_superman=0 and status in (0,1) order by uid desc limit :2, :3 ")
    List<HrInfo> getHrByPage(int CustomerId, int offset, int pageSize);

    @SQL("update hr_user set status=:2 where uid in (:1)")
    void deleteUser(Set<Integer> uids, int status);

    @SQL("select distinct customer_id from hr_user where uid in (:1)")
    List<Integer> getUserCustomer(Set<Integer> uids);

    /**
     * 根据Id查询Hr信息
     *
     * @param uids
     * @return
     */
    @SQL("select uid, name, role_id, status, `desc` from hr_user where uid in (:1)")
    List<HrInfo> getHrByIds(List<Integer> uids);


    /**
     * 分组查询每个角色又多少人
     *
     * @param roleIds
     * @return
     */
    @SQL("select role_id , count(uid) from hr_user where role_id in (:1) group by role_id")
    Map<Integer, Integer> getUserCountByRole(List<Integer> roleIds);

    /**
     * 查询角色下的所有用户
     *
     * @param roleId
     * @return
     */
    @SQL("select uid from hr_user where role_id=:1")
    List<Integer> getUidByRole(int roleId);

    @SQL("uid, name, role_id, status, `desc` from hr_user where role_id=:1")
    List<HrInfo> getHrByRole(int roleId);

    /**
     * 更改用户信息
     *
     * @param uid
     * @param name
     * @param roleId
     * @param status
     * @param description
     */
    @SQL("update hr_user set name=:2, role_id=:3, status=:4, `desc`=:5 where uid=:1")
    void updateUser(int uid, String name, int roleId, byte status, String description);

    @SQL("select uid, name from hr_user where uid in (:1)")
    Map<Integer, String> getHrName(Set<Integer> uid);

    @SQL("select uid from hr_user where uid=:1 and is_superman=1")
    Integer isSuper(int uid);

    @SQL("select status, is_superman from hr_user where uid=:1")
    Hr getStatus(int uid);

    @SQL("select uid from hr_user where uid in (:3) and customer_id=:1 and status=:2")
    Set<Integer> filterUser(int customerId, byte statusPause, Set<Integer> uid);

    @SQL("update hr_user set status=:2 where id in (:1)")
    void updateUserStatus(Set<Integer> uid, byte statusPause);

    @SQL("SELECT uid, name, role_id, status, is_superman FROM hr_user WHERE customer_id=:1")
    List<HrInfo> getAll(int customerId);

    @SQL("select uid, name from hr_user where uid in (:1)")
    Map<Integer, String> getNameById(Set<Integer> uidSet);
}
