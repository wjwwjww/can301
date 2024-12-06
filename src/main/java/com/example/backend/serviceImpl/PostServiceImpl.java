package com.example.backend.serviceImpl;

import com.example.backend.entity.Post;
import com.example.backend.mapper.PostMapper;
import com.example.backend.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiawei
 * @since 2024-12-05
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
