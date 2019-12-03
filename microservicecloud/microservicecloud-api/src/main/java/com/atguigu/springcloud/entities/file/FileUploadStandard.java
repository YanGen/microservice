package com.atguigu.springcloud.entities.file;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileUploadStandard
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/14
 * @Version 1.0.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class FileUploadStandard {
    private MultipartFile file; // 文件域
    private String fileName; // 文件名（xxx.txt）
    private FileSuffixConstant fileSuffix; // 文件后缀名:txt,zip,jpeg,png
    private String netUrl; // 网络访问地址
    private String localPath; // 本地保存路径
    private Long fileSize; // 文件实际大小
    private Long limited; // 文件大小限制,单位kb（前端也应该做文件大小限制）
}
