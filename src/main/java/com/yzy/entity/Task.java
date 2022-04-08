package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 任务
 * </p>
 *
 * @author yuanzhiyong
 * @since 2022-04-05
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    private String des;

    private String imageUrl;

    private String title;

    private BigDecimal score;

    private String state;

    private String tags;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Task{" +
        "id=" + id +
        ", des=" + des +
        ", imageUrl=" + imageUrl +
        ", title=" + title +
        ", score=" + score +
        ", state=" + state +
        ", tags=" + tags +
        "}";
    }
}
