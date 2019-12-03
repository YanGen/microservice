package com.atguigu.springcloud.entities.file;

import lombok.*;

/**
 * @ClassName FileListStandard
 * @Description TODO 文件列表抽象类
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/14
 * @Version 1.0.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class FileListStandard {
    private String fileName; // 文件名
    private String updateTime; // 文件最后修改日期
    private String fileType; // 文件类型：文件夹或.文件后缀或空
    private String fileSize; // 文件大小
}
