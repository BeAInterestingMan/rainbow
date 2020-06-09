package com.rainbow.generator.util;

import com.rainbow.generator.constant.GeneratorConstant;
import org.apache.commons.lang3.ArrayUtils;

public class GeneratorUtil {
    /**
     * 下划线转驼峰
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split(GeneratorConstant.UNDER_LINE);
        if(ArrayUtils.isNotEmpty(arr)){
            for (String s : arr) {
                result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
            }
        }
        return result.toString();
    }
}
