package com.xxx.dao;


import com.xxx.bean.SzUser;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import java.sql.SQLException;
import java.util.List;

@DAO(catalog = "db_name")
public interface DemoDAO {

    @SQL("SELECT adid FROM zhaopin_ad WHERE available=1 ORDER BY adid DESC")
    public List<SzUser> findAllAds() throws SQLException;


}
