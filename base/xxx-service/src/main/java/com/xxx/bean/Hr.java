package com.xxx.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员
 */
public class Hr implements Serializable {
    private static final long serialVersionUID = -6599725892613005223L;
    private int uid;
    private int customerId;
    private String companyName;
    private int companyId;
    private String name;  //姓名
    private String uname; //账号
    private int roleId;
    private byte status;
    private byte isSuperman;
    private Date createTime;

    public static final String SUPER_DEFAULT_NAME = "admin";
    //是超管
    public static final byte SUPER_Y = 1;
    public static final byte SUPER_N = 0;
    public static final byte STATUS_NORMAL = 0;
    public static final byte STATUS_PAUSE = 1;
    public static final byte STATUS_DELETE = 2;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }


    public boolean isSuper() {
        return isSuperman == SUPER_Y;
    }

    public byte getIsSuperman() {
        return isSuperman;
    }

    public void setIsSuperman(byte superman) {
        isSuperman = superman;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public byte getSuperman() {
        return isSuperman;
    }

    public void setSuperman(byte superman) {
        isSuperman = superman;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
