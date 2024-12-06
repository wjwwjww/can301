package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiawei
 * @since 2024-12-05
 */
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userid;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer catid;

    private String location;

    private String content;

    private String adress;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Post{" +
            "userid = " + userid +
            ", id = " + id +
            ", catid = " + catid +
            ", location = " + location +
            ", content = " + content +
            ", adress = " + adress +
        "}";
    }
}
