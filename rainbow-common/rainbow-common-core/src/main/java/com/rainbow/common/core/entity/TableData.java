package com.rainbow.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 *  @Description 分页对象
 *  @author liuhu
 *  @Date 2020/5/26 15:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableData {

    private List<?> rows;

    private long total;
}
