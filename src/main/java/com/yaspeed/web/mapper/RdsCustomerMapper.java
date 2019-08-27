package com.yaspeed.web.mapper;

import com.yaspeed.web.pojo.RdsCustomer;
import com.yaspeed.web.pojo.RdsCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RdsCustomerMapper {
    int countByExample(RdsCustomerExample example);

    int deleteByExample(RdsCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RdsCustomer record);

    int insertSelective(RdsCustomer record);

    List<RdsCustomer> selectByExample(RdsCustomerExample example);

    RdsCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RdsCustomer record, @Param("example") RdsCustomerExample example);

    int updateByExample(@Param("record") RdsCustomer record, @Param("example") RdsCustomerExample example);

    int updateByPrimaryKeySelective(RdsCustomer record);

    int updateByPrimaryKey(RdsCustomer record);
}