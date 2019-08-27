package com.yaspeed.web.service;

import com.yaspeed.common.DataGridResult;
import com.yaspeed.web.pojo.RdsCustomer;

public interface CustomerService {
    DataGridResult findCustomerList(Integer page, Integer rows, RdsCustomer rdsCostumer);
}
