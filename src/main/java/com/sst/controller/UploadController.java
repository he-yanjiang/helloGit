package com.sst.controller;

import com.sst.error.BusinessException;
import com.sst.error.EmBusinessError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaesController{
    @RequestMapping(value = "/fileUploads")
    public String fileUploads(@RequestParam(value = "file")List<MultipartFile> files ) throws BusinessException {
        System.out.println("file:"+files);
        for (MultipartFile file:files) {
            if (file.isEmpty()) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = "D://temp-rainy//"; // 上传后的路径
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String filename = "/temp-rainy/" + fileName;
            System.out.println(" filename:"+filename);
        }
        return "你就是个垃圾";
    }


}
