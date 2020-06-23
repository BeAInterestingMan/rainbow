package com.rainbow.task.service.service.impl;


import com.rainbow.task.service.mapper.JobMapper;
import com.rainbow.task.service.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
*  @Description oauthClient 业务层实现类
*  @author liuhu
*  @Date 2020-6-23 16:33:16
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl  implements JobService {

    private final JobMapper jobMapper;

}
