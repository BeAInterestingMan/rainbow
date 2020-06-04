package com.rainbow.server.system.service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.common.core.entity.system.Log;
import org.springframework.stereotype.Repository;

/**
 *  @Description 用户操作日志表
 *  @author liuhu
 *  @Date 2020-06-04 11:10:55
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {

}
