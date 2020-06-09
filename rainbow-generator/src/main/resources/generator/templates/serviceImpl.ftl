package ${basePackage}.${serviceImplPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${servicePackage}.${className}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
*  @Description ${tableComment!} 业务层实现类
*  @author ${author}
*  @Date ${.now}
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ${className}ServiceImpl  implements ${className}Service {

    private final ${className}Mapper ${className?uncap_first}Mapper;

}
