package com.rainbow.common.datasource.configure;


import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.rainbow.common.datasource.inteceptor.DataPermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;


/**
 *  @Description
 *  @author liuhu
 *  @Date 2020/5/12 9:14
 */
@Configuration
public class RainbowDataSourceAutoconfigure {
//
//   /**
//    * @Description 注册数据权限
//    * @author liuhu
//    * @createTime 2020-05-12 09:20:28
//    * @param
//    * @return DataPermissionInterceptor
//    */
//    @Bean
//    @Order(-1)
//    public DataPermissionInterceptor dataPermissionInterceptor(){
//        return new DataPermissionInterceptor();
//    }

    /**
     * @Description 分页插件
     * @author liuhu
     * @createTime 2020-05-12 09:20:16
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    @Order(-2)
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }
}
