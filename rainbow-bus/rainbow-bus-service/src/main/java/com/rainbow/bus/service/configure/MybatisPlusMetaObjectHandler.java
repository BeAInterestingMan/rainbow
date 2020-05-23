package com.rainbow.bus.service.configure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  @Description 字段自动填充
 *  @author liuhu
 *  @Date 2020/5/22 18:05
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("updateTime",new Date(),metaObject);
        setFieldValByName("createTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",new Date(),metaObject);
    }
}
