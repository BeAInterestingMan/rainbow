package com.rainbow.upload.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainbow.upload.entity.Attachment;
import com.rainbow.upload.exception.UploadException;
import com.rainbow.upload.fastdfs.FastDFSClient;
import com.rainbow.upload.fastdfs.FastDFSFile;
import com.rainbow.upload.mapper.AttachmentMapper;
import com.rainbow.upload.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *  @Description 上传实现类
 *  @author liuhu
 *  @Date 2020-06-05 13:48:25
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentMapper attachmentMapper;

    @Override
    public Attachment upload(MultipartFile file) {
        Attachment  attachment = null;
        if (file.isEmpty()) {
            throw new UploadException("上传文件不能为空");
        }
        try {
            attachment = uploadFile(file);
            attachmentMapper.insert(attachment);
        }catch (Exception e){
            e.printStackTrace();
            throw new  UploadException("上传文件失败");
        }
        return attachment;
    }

    @Override
    public void delete(String group, String remoteName) {
        try {
           FastDFSClient.deleteFile(group,remoteName);
           QueryWrapper<Attachment> queryWrapper = new QueryWrapper<>();
           attachmentMapper.delete(queryWrapper.eq("remote_name",remoteName));
        }catch (Exception e){
            e.printStackTrace();
            throw new  UploadException("删除文件失败");
        }
    }

    public Attachment uploadFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        long size = multipartFile.getSize();
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] fileBuff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            fileBuff = new byte[len1];
            inputStream.read(fileBuff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, fileBuff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);
        } catch (Exception e) {
            log.error("upload file Exception!",e);
        }
        String trackerUrl = FastDFSClient.getTrackerUrl();
        String group = fileAbsolutePath[0];
        String remoteName = fileAbsolutePath[1];
        String fullPath = trackerUrl+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return  Attachment.builder().fileSize(size).fileName(fileName)
                .id(1L)
                .createTime(new Date()).fullPath(fullPath)
                .domainName(trackerUrl)
                .groupName(group)
                .remoteName(remoteName)
                .build();
    }
}
