package com.example.backend.controller;

import com.example.backend.entity.Post;
import com.example.backend.mapper.PostMapper;
import com.example.backend.util.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jiawei
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostMapper postMapper;

    @RequestMapping("/getAllPost")
    public Object getAllPost(){
        List<Post> posts = postMapper.selectList(null);
        return ResultUtil.isSuccess(posts);
    }
    @PostMapping("/addPost")
    public Object addPost(@RequestBody Post post){
        int i =postMapper.insert(post);
        if(i==1){
            return ResultUtil.isSuccess("添加成功",null);
        }else{
            return ResultUtil.isFail(500,"添加失败");
        }
    }
    @PostMapping("/updatePost")
    public Object updatePost(@RequestBody Post post){
        int i =postMapper.updateById(post);
        if(i==1){
            return ResultUtil.isSuccess("更新成功",null);
        }else{
            return ResultUtil.isFail(500,"更新失败");
        }
    }
    @PostMapping("/delPost")
    public Object delPost(Integer postid){
        int i =postMapper.deleteById(postid);
        if(i==1){
            return ResultUtil.isSuccess("删除成功",null);
        }else{
            return ResultUtil.isFail(500,"删除失败");
        }
    }


}
