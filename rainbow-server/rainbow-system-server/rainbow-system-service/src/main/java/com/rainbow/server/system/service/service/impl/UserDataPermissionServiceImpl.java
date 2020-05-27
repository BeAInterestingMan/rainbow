package com.rainbow.server.system.service.service.impl;

import com.rainbow.server.system.service.service.IUserDataPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserDataPermissionServiceImpl  implements IUserDataPermissionService {


    @Override
    public void deleteByDeptIds(List<String> deptIds) {

    }

    @Override
    public void deleteByUserIds(String[] userIds) {

    }

    @Override
    public String findByUserId(String userId) {
        return null;
    }
}
