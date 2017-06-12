package com.ysughw.bootdemo.api.dao;

import com.ysughw.bootdemo.api.entity.CustInfo;

public interface CustInfoMapper {
    int deleteByPrimaryKey(Integer clientidx);

    int insert(CustInfo record);

    int insertSelective(CustInfo record);

    CustInfo selectByPrimaryKey(Integer clientidx);

    int updateByPrimaryKeySelective(CustInfo record);

    int updateByPrimaryKey(CustInfo record);
}