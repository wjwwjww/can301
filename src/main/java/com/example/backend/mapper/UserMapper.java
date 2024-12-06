package com.example.backend.mapper;

import com.example.backend.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiawei
 * @since 2024-12-04
 */
public interface UserMapper extends BaseMapper<User> {


    @Update("UPDATE user SET image = #{filePath} WHERE id = #{id}")
    void updateimagebyid(Integer id, String filePath);

    @Select("SELECT image FROM user WHERE id = #{id}")
    String getImageById(Integer id);
}
