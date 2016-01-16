package com.xxx.service;

import com.xxx.bean.SzUser;
import com.xxx.dao.SzUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xujinchao on 2016/1/16.
 */
@Service
public class SzUserService {

    private static Logger logger = LoggerFactory.getLogger(SzUserService.class);

    @Autowired
    private SzUserDAO szUserDAO;

    public int addNewSzUser(SzUser szUser) {
        if (szUser == null) {
            return 0;
        }
        return szUserDAO.addNewSzUser(szUser);
    }
}
