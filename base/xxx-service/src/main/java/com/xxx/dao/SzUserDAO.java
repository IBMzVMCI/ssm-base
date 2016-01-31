package com.xxx.dao;

import com.xxx.bean.SzUser;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import java.util.List;

/**
 * Created by xujinchao on 2016/1/16.
 */
@DAO(catalog = "show_zhan")
public interface SzUserDAO {

    String ALL_FIELD = "id, account, passwd, email, create_time, update_time, state, admin";
    String INSERT_FIELD = "account, passwd, email, create_time, update_time, state, admin";
    String TABLE_NAME = "sz_user";
    @SQL("SELECT " + ALL_FIELD + " FROM sz_user" )
    List<SzUser> getAllSzUser();

    @ReturnGeneratedKeys
    @SQL("INSERT INTO sz_user(" + INSERT_FIELD +") VALUES(:1.account,:1.passwd,:1.email,:1.creatTime," +
            ":1,updateTime, :1.state, :1.admin)")
    int addNewSzUser(SzUser szUser);

    @SQL("SELECT id FROM sz_user WHERE id = :1")
    int existUser(int userId);
}
