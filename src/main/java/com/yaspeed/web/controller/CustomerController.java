/**
 * @Author wpzhang
 * @Date 2019/8/27
 * @Description
 */
package com.yaspeed.web.controller;


import com.yaspeed.common.DataGridResult;
import com.yaspeed.web.pojo.RdsCustomer;
import com.yaspeed.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: myProject
 * @description: 顾客
 * @author: wpzhang
 * @create: 2019-08-27 09:53
 **/
@Controller
@RequestMapping("/rds_customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping("/rdsCustomerList")
   public String toPage(){
        return "rds_customer/rds_customer_list";
   }
   @RequestMapping("/findRdsCustomerList")
   @ResponseBody()
    public DataGridResult findCustomerList(
            @RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
            @RequestParam(required = false, defaultValue = "2",value ="rows" ) Integer rows, RdsCustomer rdsCostumer){
      return customerService.findCustomerList(page, rows,rdsCostumer);
   }



}