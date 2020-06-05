package com.rainbow.upload.controller;



import com.rainbow.upload.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 *  @Description 上传服务接口
 *  @author liuhu
 *  @Date 2020-06-05 13:48:25
 */
@RestController
@RequestMapping("attachment")
@Api(tags = {"上传服务接口"})
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    /**
     * @Description 删除图片
     * @author liuhu
     * @createTime 2020-06-05 13:35:28
     * @param file
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping
    @ApiOperation("上传文件")
    @ApiImplicitParam(name = "file",value = "文件")
    public ResponseEntity Upload(@RequestParam("file") MultipartFile file) {
        return   ResponseEntity.ok(attachmentService.upload(file));
    }

    /**
     * @Description 删除图片
     * @author liuhu
     * @createTime 2020-06-05 14:51:29
     * @param group
     * @param remoteName
     * @return org.springframework.http.ResponseEntity
     */
    @DeleteMapping("/delete/{group}/{remoteName}")
    @ApiOperation("删除图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName",value = "文件组"),
            @ApiImplicitParam(name = "remoteName",value = "远程文件名")
    })
    public ResponseEntity delete(@PathVariable("group") String group,@PathVariable("remoteName") String remoteName){
        attachmentService.delete(group,remoteName);
        return   ResponseEntity.ok().build();
    }

}
