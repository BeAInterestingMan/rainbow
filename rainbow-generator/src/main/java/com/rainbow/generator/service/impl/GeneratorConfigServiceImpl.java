package com.rainbow.generator.service.impl;


import com.rainbow.generator.entity.GeneratorConfig;
import com.rainbow.generator.exception.GeneratorException;
import com.rainbow.generator.mapper.GeneratorConfigMapper;
import com.rainbow.generator.service.GeneratorConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *  @Description 业务层实现类
 *  @author liuhu
 *  @Date 2020/6/8 17:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class GeneratorConfigServiceImpl implements GeneratorConfigService {

    private final GeneratorConfigMapper generatorConfigMapper;

    @Override
    public GeneratorConfig findGeneratorConfig() {
        try {
            List<GeneratorConfig> generatorConfigs = generatorConfigMapper.selectList(null);
            return CollectionUtils.isEmpty(generatorConfigs)?null:generatorConfigs.get(0);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneratorException("查询配置失败");
        }

    }

    @Override
    public GeneratorConfig updateGeneratorConfig(GeneratorConfig generatorConfig) {
        try {
            generatorConfigMapper.updateById(generatorConfig);
            return generatorConfig;
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneratorException("修改配置失败");
        }
    }
}
