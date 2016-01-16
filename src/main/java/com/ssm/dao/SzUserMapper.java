package com.ssm.dao;

import com.ssm.domain.SzUser;

public interface SzUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SzUser record);

    int insertSelective(SzUser record);

    SzUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SzUser record);

    int updateByPrimaryKey(SzUser record);
}