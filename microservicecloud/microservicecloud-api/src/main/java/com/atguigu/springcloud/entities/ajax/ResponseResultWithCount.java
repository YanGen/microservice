package com.atguigu.springcloud.entities.ajax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName ResponseResultWithCount
 * @Description TODO 附带分页结果集的Bean
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/8/6
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseResultWithCount extends ResponseResult {
    private long count;
}
