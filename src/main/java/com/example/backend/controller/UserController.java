package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.util.ResultUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jiawei
 * @since 2024-11-19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;
    @RequestMapping("/getAllUser")
    public Object getAllUser(){
        List<User> users = userMapper.selectList(null);
        return ResultUtil.isSuccess(users);
    }
    @GetMapping("/getUser")
    @ResponseBody
    public Object getUser(@RequestParam Integer id){
        User user=userMapper.selectById(id);
        if(user!=null){
            return ResultUtil.isSuccess(user);
        }else{
            return ResultUtil.isFail(404, "User not found");
        }
    }

    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
        int i =userMapper.insert(user);
        if(i==1){
            return ResultUtil.isSuccess("添加成功",null);
        }else{
            return ResultUtil.isFail(500,"添加失败");
        }
    }
    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user){
        int i =userMapper.updateById(user);
        if(i==1){
            return ResultUtil.isSuccess("更新成功",null);
        }else{
            return ResultUtil.isFail(500,"更新失败");
        }
    }
    @Value("${avatar.path}")
    private String basePath;
    @PostMapping("/updateavatar")
    public Object updateUserAvatar(Integer id, MultipartFile avatar) {
            String originalFilename = avatar.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            String fileName = UUID.randomUUID().toString() + suffix;

            File dir = new File(basePath);

            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                avatar.transferTo(new File(basePath + fileName));
                String filePath = basePath + fileName;
                System.out.println(filePath);
                System.out.println(id);
                userMapper.updateimagebyid(id, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResultUtil.isFail(500, "上传失败");
        }

        //非常重要：下载的servlet一定不能导入用javax，该用jakarta,不然会报错
    @GetMapping("/downloadavatar")
    public void download(Integer id, HttpServletResponse response){
        try {
            String fileName=  userMapper.getImageById(id);
            File file = new File(fileName);

            System.out.println(fileName);
            FileInputStream fileInputStream=  new FileInputStream(new File(fileName));
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/delUser")
    public Object delUser(Integer userId){
        int i =userMapper.deleteById(userId);
        if(i==1){
            return ResultUtil.isSuccess("删除成功",null);
        }else{
            return ResultUtil.isFail(500,"删除失败");
        }
    }
    @PostMapping("/setstatus")
    public Object setStatus(Integer userId){
        User user =userMapper.selectById(userId);
        user.setStatus(true);
        int i =userMapper.updateById(user);
        if(i==1){
            return ResultUtil.isSuccess("认证成功",null);
        }else {
            return ResultUtil.isFail(500,"认证失败");
        }
    }



}
