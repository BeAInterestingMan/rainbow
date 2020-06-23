package com.rainbow.task.service.service.impl;


import com.rainbow.task.service.mapper.LoginLogMapper;
import com.rainbow.task.service.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
*  @Description job 业务层实现类
*  @author liuhu
*  @Date 2020-6-23 17:08:09
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LoginLogServiceImpl  implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

}
