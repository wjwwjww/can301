package com.example.backend.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiawei
 * @since 2024-12-05
 */
public class Postimage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String contentImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    @Override
    public String toString() {
        return "Postimage{" +
            "id = " + id +
            ", contentImage = " + contentImage +
        "}";
    }
}
