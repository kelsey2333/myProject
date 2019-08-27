/**
 * @Author wpzhang
 * @Date 2019/8/27
 * @Description
 */
package com.yaspeed.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaspeed.common.DataGridResult;
import com.yaspeed.core.util.StringUtil;
import com.yaspeed.web.mapper.RdsCustomerMapper;
import com.yaspeed.web.pojo.RdsCustomer;
import com.yaspeed.web.pojo.RdsCustomerExample;
import com.yaspeed.web.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: myProject
 * @description:
 * @author: wpzhang
 * @create: 2019-08-27 10:14
 **/
@Service
public class CustomerSerivceImpl implements CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerSerivceImpl.class);
    @Autowired
    private RdsCustomerMapper rdsCustomerMapper;
    @Override
    public DataGridResult findCustomerList(Integer page, Integer rows, RdsCustomer rdsCostumer) {
        PageHelper.startPage(page,rows);
        //组织好example
        RdsCustomerExample example = new RdsCustomerExample();
        //创建好条件标椎模板
        RdsCustomerExample.Criteria criteria = example.createCriteria();
        //查询条件:id要和文本框中一模一样
        if (StringUtil.isNotEmpty(rdsCostumer.getId())){
            criteria.andIdEqualTo(Integer.parseInt(rdsCostumer.getId()));
        }
        //查询条件：姓名和文本框类似
        if (StringUtil.isNotEmpty(rdsCostumer.getName())){
            criteria.andNameLike("%"+ rdsCostumer.getName() + "%");
        }
        //按照id进行排序
        example.setOrderByClause("id");
        //example模板准备好后进行查询
        List<RdsCustomer> customerList = rdsCustomerMapper.selectByExample(example);
        //查询的结果进行分页布置
        PageInfo<RdsCustomer> pageInfo = new PageInfo<RdsCustomer>(customerList);
        //封装成返回类型的结果
        DataGridResult result = new DataGridResult();
        //设置需要的参数
        result.setTotal(pageInfo.getTotal());
        result.setRows(customerList);
        //返回结果
        return result;
    }
}