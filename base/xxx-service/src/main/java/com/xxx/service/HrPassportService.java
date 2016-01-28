package com.xxx.service;

import com.xxx.bean.Hr;
import com.xxx.dao.HrDAO;
import com.xxx.dao.HrPassportDAO;
import com.xxx.util.MD5;
import com.xxx.bean.Passport;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class HrPassportService {

    @Autowired
    private HrPassportDAO hrPassportDAO;

    @Autowired
    private HrDAO hrDAO;

    /**
     * 采用md5(md5(password) + salt) 生成密码
     *
     * @param userPassword
     * @param salt
     * @return
     */
    public String generatePassword(String userPassword, String salt) {
        return MD5.digest(MD5.digest(userPassword + salt));
    }

    /**
     * 通过用户公司id，账号查询Passport信息
     *
     * @param customerId
     * @param uname
     * @return
     */
    public Passport findPassportByUname(int customerId, String uname) {
        return hrPassportDAO.findPassportByUname(customerId, uname);
    }

    /**
     * 判断当前uname在客户下是否存在
     *
     * @param customerId
     * @param uname
     * @return
     */
    public boolean isPassportExist(int customerId, String uname) {
        Integer id = hrPassportDAO.getPassportId(customerId, uname);
        return id != null;
    }

    /**
     * 根据Hr的id查询其企业客户Id
     *
     * @param uid
     * @return
     */
    public int getUserCustomerId(int uid) {
        Integer customerId = hrPassportDAO.getUserCustomerId(uid);
        if (customerId == null)
            return 0;
        return customerId;
    }

    /**
     * 添加Passport
     *
     * @param customerId
     * @param uname
     * @param password
     * @return 用户Hr的id
     */
    public int addPassport(int customerId, String uname, String password) {
        Integer id = hrPassportDAO.getPassportId(customerId, uname);
        if (id != null) {
            Hr hrStatus = hrDAO.getStatus(id);
            if (hrStatus.getStatus() != Hr.STATUS_DELETE) {
//                throw new ServiceException("账号已存在，请换个账号");
                return  -1;
            }
        }
        String salt = RandomStringUtils.random(6, true, true);
        String encryptPassword = generatePassword(password, salt);
        return hrPassportDAO.saveHrPassport(customerId, uname, encryptPassword, salt);
    }

    /**
     * 重置密码
     *
     * @param uid
     * @param password
     */
    public void modifyPassword(int uid, String password) {
        String salt = RandomStringUtils.random(6, true, true);
        String encryptPassword = generatePassword(password, salt);
        hrPassportDAO.resetPassword(uid, encryptPassword, salt);
    }

    /**
     * 判断密码是否正确
     *
     * @param uid
     * @param password
     * @return
     */
    public boolean isPasswordValid(int uid, String password) {
        Passport passport = hrPassportDAO.findPassportById(uid);
        if (passport == null)
//            throw new ServiceException("用户不存在");
            return false;

        String encryptPassword = generatePassword(password, passport.getSalt());
        return encryptPassword.equals(passport.getPassword());
    }

    public String getUname(int uid) {
        return hrPassportDAO.getUname(uid);
    }
}
