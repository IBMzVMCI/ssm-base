package com.ssm.dao;

import com.ssm.domain.SzUserDetail;

public interface SzUserDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SzUserDetail record);

    int insertSelective(SzUserDetail record);

    SzUserDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SzUserDetail record);

    int updateByPrimaryKey(SzUserDetail record);
}