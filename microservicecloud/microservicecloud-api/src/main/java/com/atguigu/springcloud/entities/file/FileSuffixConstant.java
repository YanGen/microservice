package com.atguigu.springcloud.entities.file;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName FileSuffixConstant
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/14
 * @Version 1.0.0
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum FileSuffixConstant {
    ZIP(".zip"),
    PNG(".png"),
    JPG(".jpg"),
    GIF(".gif"),
    JPEG(".jpeg"),
    PDF(".pdf");
    private final String fileSuffix;
}
