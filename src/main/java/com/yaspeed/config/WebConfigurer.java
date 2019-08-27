package com.yaspeed.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.yaspeed.core.cache.PermissionCacheProvider;
import com.yaspeed.core.context.SpringContextHolder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
* @Packege: com.yaspeed.config
* @ClassName: WebConfigurer 
* @Description: 
* @Author hch
* @Date  2019/5/6 17:39
*/  
@Configuration
public class WebConfigurer implements WebMvcConfigurer{

    @Bean
    public PermissionCacheProvider permissionCache(){
        return new PermissionCacheProvider();
    }

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }

    /**
     * 数据库连接dataSource
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://106.15.36.5:3306/rds1?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("rt123tr");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    /**
     * 分页pageHelper
     * @return
     */
    @Bean
    public PageHelper pageHelper() throws Exception{
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("reasonable","true");
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("params","count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean
    public ITemplateResolver resourceViewResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/static/tpl/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * 事物管理，开启事务@EnableTransactionManagement
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        //pojo别名
        //sessionFactory.setTypeAliasesPackage("com.yaspeed.pojo");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*Mapper.xml"));
       // sessionFactory.setPlugins(new Interceptor[]{new SqlCostInterceptor()});
        return sessionFactory;
    }

    /**
     * 登陆信息拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(sessionUserInterceptor()).addPathPatterns("/**");
    }
}
