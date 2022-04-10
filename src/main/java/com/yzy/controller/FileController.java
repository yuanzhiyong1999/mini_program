package com.yzy.controller;


import com.yzy.common.QiniuCloudUtil;
import com.yzy.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by  lijiaxing on 2022/4/3 20:52
 *
 * @author lijiaxing
 * desc:用以上传和下载文件
 */
@RestController
@RequestMapping("/file")
@Slf4j
@Api(description = "文件相关")
public class FileController {

    @ApiOperation("上传文件")
    @PostMapping(path = "/upload", produces = "application/json")
    public Result uploadImg(@RequestParam MultipartFile image) {
        if (image.isEmpty()) {
            return Result.fail("上传失败");
        }

        try {
            byte[] bytes = image.getBytes();
            //文件名
            //String fileName = FileName.getFileName(image);
            String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            //上传工具类
            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                String key = qiniuUtil.put64image(bytes, fileName);
                return Result.succ(key,1);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail("上传异常");
            }
        } catch (IOException e) {
            return Result.fail("上传异常");
        }
    }


    @ApiOperation("下载文件")
    @GetMapping(path = "/download", produces = "application/json")
    public Result download(@RequestParam("objectKey") String objectKey) throws IOException {
        log.info(objectKey);
        String url = QiniuCloudUtil.download(objectKey);
        return Result.succ(url,1);
    }
}
