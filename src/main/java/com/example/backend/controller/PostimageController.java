package com.example.backend.controller;

import com.example.backend.mapper.PostimageMapper;
import com.example.backend.util.ResultUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jiawei
 * @since 2024-12-05
 */
@Controller
@RequestMapping("/postimage")
public class PostimageController {
    @Resource
    private PostimageMapper postimageMapper;


    @Value("${images.path}")
    private String basePath;
    @PostMapping
        public Object postImage(@RequestParam MultipartFile image, Integer id) {
        String originalFilename = image.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = UUID.randomUUID().toString() + suffix;

        File dir = new File(basePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            image.transferTo(new File(dir.getAbsoluteFile()+"/" + fileName));
            String filePath = dir.getAbsolutePath()+"/" + fileName;
//            System.out.println(filePath);
//            System.out.println(id);
            postimageMapper.insert(id, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.isFail(500, "上传失败");
    }

    @GetMapping("/downloadimage")
    public void download(Integer id, HttpServletResponse response){
        try {
            List<String> fileNames=  postimageMapper.getImageById(id);
//            List<String> files = new ArrayList<>();
//
//            for(String fileName:fileNames){
//
//                fileNames.add(fileName);
//            }
            File dir = new File(basePath);
            for(String fileName:fileNames){
            FileInputStream fileInputStream=  new FileInputStream(new File(dir.getAbsoluteFile()+"/"+fileName));
            ServletOutputStream outputStream =response.getOutputStream();
            response.setContentType("image/jpeg");
            int len=0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
            }

//            System.out.println(fileNames);
//            FileInputStream fileInputStream=  new FileInputStream(new File(fileNames));
//            ServletOutputStream outputStream =response.getOutputStream();
//            response.setContentType("image/jpeg");
//            int len=0;
//            byte[] bytes = new byte[1024];
//            while ((len = fileInputStream.read(bytes))!=-1){
//                outputStream.write(bytes,0,len);
//                outputStream.flush();
//            }
//            outputStream.close();
//            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
