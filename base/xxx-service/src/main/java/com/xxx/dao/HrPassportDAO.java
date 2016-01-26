package com.xxx.dao;

import com.xxx.bean.Passport;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@DAO(catalog = "show_zhan")
public interface HrPassportDAO {

    @SQL("SELECT id, salt, `password` FROM hr_passport WHERE id=:1")
    public Passport findPassportById(int id);

    @SQL("SELECT id, `password`, salt FROM hr_passport WHERE customer_id=:1 and uname=:2")
    public Passport findPassportByUname(int customerId, String uname);

    /**
     * 根据客户Id，用户账号，查询账号Hr id
     *
     * @param customerId
     * @param uname
     * @return
     */
    @SQL("SELECT id FROM hr_passport WHERE customer_id=:1 and uname=:2")
    public Integer getPassportId(int customerId, String uname);

    @SQL("UPDATE hr_passport SET `password`=:2,salt=:3 WHERE id=:1")
    public int updatePasswordByid(int id, String password, String salt) throws SQLException;

    @SQL("DELETE FROM hr_passport WHERE id=:1")
    public void delete(int id);

    @ReturnGeneratedKeys
    @SQL("INSERT INTO hr_passport (customer_id, uname, password, salt) VALUES (:1, :2, :3, :4)")
    public int saveHrPassport(int customerId, String uname, String password, String salt);

    /**
     * 根据id查询customer_id
     *
     * @param uid
     * @return
     */
    @SQL("select customer_id, uname from hr_passport where id=:1")
    Passport getUserPassport(int uid);

    /**
     * 根据id查询customer_id
     *
     * @param uid
     * @return
     */
    @SQL("select customer_id from hr_passport where id=:1")
    Integer getUserCustomerId(int uid);


    /**
     * 批量查询账号
     *
     * @param uids
     * @return
     */
    @SQL("select id, uname from hr_passport where id in (:1)")
    Map<Integer, String> getUnameByIds(List<Integer> uids);

    /**
     * 重置用户密码
     *
     * @param uid
     * @param encryptPassword
     * @param salt
     */
    @SQL("update hr_passport set `password`=:2, salt=:3 where id=:1")
    void resetPassword(int uid, String encryptPassword, String salt);

    @SQL("select uname from hr_passport where id=:1")
    String getUname(int uid);
}