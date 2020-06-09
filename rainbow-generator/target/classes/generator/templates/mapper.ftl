package ${basePackage}.${mapperPackage};

import ${basePackage}.${entityPackage}.${className};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
*  @Description ${tableComment!} 持久层接口
*  @author ${author}
*  @Date ${.now}
*/
@Repository
public interface ${className}Mapper extends BaseMapper<${className}> {

}
