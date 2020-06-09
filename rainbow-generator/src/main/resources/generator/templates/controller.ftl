package ${basePackage}.${controllerPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.${className}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
*  @Description ${tableComment!} Controller
*  @author ${author}
*  @Date ${.now}
*/
@RestController
@RequestMapping("${className?uncap_first}")
@RequiredArgsConstructor
public class ${className}Controller {

    private final ${className}Service ${className?uncap_first}Service;

}
