package com.xxx.service;

import com.xxx.bean.Hr;
import com.xxx.dao.HrDAO;
import com.xxx.dao.HrPassportDAO;
import com.xxx.util.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrService {
    private Logger logger = LoggerFactory.getLogger(HrService.class);

    @Autowired
    private HrDAO hrDAO;

    @Autowired
    private HrPassportDAO hrPassportDAO;


    /**
     * 查询公司下所有用户的数目,超管会被过滤
     *
     * @param customerId
     * @return
     */
    public int getAllUserCount(int customerId) {
        return hrDAO.getAllUserCount(customerId);
    }


    /**
     * 添加Hr账号基本信息
     *
     * @param uid
     * @param name
     * @param roleId
     * @param status
     * @param isSuper
     * @return
     */
    public int add(Hr hr, int uid, String name, int roleId, byte status, String description, boolean isSuper){
        byte superStatus = (isSuper ? Hr.SUPER_Y : Hr.SUPER_N);
//        Integer roleCustomerId = roleDAO.getRoleCustomer(roleId);
//        if (roleCustomerId == null || roleCustomerId != hr.getCustomerId())
//            throw new ServiceException("角色权限错误");

        return hrDAO.addUser(hr.getCustomerId(), uid, name, roleId, status, superStatus, description);
    }

    /**
     * 更改用户信息
     *
     * @param uid
     * @param name
     * @param roleId
     * @param status
     * @param description
     */
    public Hr updateUser(Hr hr, int uid, String name, int roleId, byte status, String description){
//        Integer customerId = roleDAO.getRoleCustomer(roleId);
//        if (customerId == null || customerId != hr.getCustomerId())
//            throw new ServiceException("权限错误");

        Passport userPassport = hrPassportDAO.getUserPassport(uid);
        if (hr.getCustomerId() != userPassport.getCustomerId())
            throw null;

        Hr hrStatus = findByUid(uid);
        if (hrStatus.isSuper()) {
            throw null;
        }
        hrDAO.updateUser(uid, name, roleId, status, description);
        hrStatus.setCompanyId(hr.getCompanyId());
        hrStatus.setCompanyName(hr.getCompanyName());
        hrStatus.setUname(userPassport.getUname());
        return hrStatus;
    }

    /**
     * 查找用户的公司Id
     *
     * @param uid
     * @return
     */
    public int customerId(int uid) {
        Integer result = hrDAO.findCustomerIdByUid(uid);
        return result == null ? 0 : result;
    }

    /**
     * 根据uid查询Hr信息u
     *
     * @param uid
     * @return
     */
    public Hr findByUid(int uid) {
        return hrDAO.findUserByUid(uid);
    }
}
