package cn.attackme.myuploader.controller;

import cn.attackme.myuploader.common.ResponseVO;
import cn.attackme.myuploader.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 大文件上传
 */
@RestController
@RequestMapping("/BigFile")
@CrossOrigin
@Slf4j
public class BigFileUploadController {
    @Autowired
    private FileService fileService;

    @PostMapping("/")
    public ResponseVO<String> upload(String name, String md5, Long size, Integer chunks, Integer chunk, MultipartFile file) throws IOException {
        String url;
        if (chunks != null && chunks != 0) {
            log.debug("chunks###################name：{} ---md5：{}----size：{}---chunks：{}----chunk：{}----",name,md5,size,chunks,chunk);
             url=fileService.uploadWithBlock(name, md5,size,chunks,chunk,file);
        } else {
            url=fileService.upload(name, md5,file);
        }
        return ResponseVO.success(url);
    }
}
