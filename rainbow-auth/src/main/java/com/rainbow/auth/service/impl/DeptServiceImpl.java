package com.rainbow.auth.service.impl;


import com.rainbow.auth.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl  implements IDeptService {

}
