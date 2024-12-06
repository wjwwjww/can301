package com.example.backend.mapper;

import com.example.backend.entity.Postimage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiawei
 * @since 2024-12-05
 */
public interface PostimageMapper extends BaseMapper<Postimage> {


    @Insert("INSERT INTO postimage (id, content_image) VALUES (#{id}, #{filePath})")
    void insert(Integer id, String filePath);

    @Select("SELECT content_image FROM postimage WHERE id = #{id}")
    List<String> getImageById(Integer id);
}
