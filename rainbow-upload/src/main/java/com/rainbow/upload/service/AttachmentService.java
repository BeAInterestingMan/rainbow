package com.rainbow.upload.service;

import com.rainbow.upload.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;




/**
 *  @Description 
 *  @author liuhu
 *  @Date 2020-06-05 13:48:25
 */
public interface AttachmentService{
    /**
     * @Description 上传
     * @author liuhu
     * @createTime 2020-06-05 14:00:18
     * @param file
     * @return com.rainbow.upload.entity.UploadDto
     */
    Attachment upload(MultipartFile file);

    /**
     * @Description 删除文件
     * @author liuhu
     * @createTime 2020-06-05 14:48:38
     * @param group
     * @param remoteName
     * @return void
     */
    void delete(String group, String remoteName);
}
