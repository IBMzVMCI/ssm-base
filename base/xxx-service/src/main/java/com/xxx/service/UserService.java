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
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SzUserDAO szUserDAO;

    public int addNewUser(SzUser szUser) {
        if (szUser == null) {
            return 0;
        }
        return szUserDAO.addNewSzUser(szUser);
    }

    public boolean isExistUser(int userId){
        return szUserDAO.existUser(userId) > 0;
    }
}
