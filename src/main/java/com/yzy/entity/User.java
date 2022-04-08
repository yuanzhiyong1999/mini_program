package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author yuanzhiyong
 * @since 2022-04-05
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    private String _openid;

    private Date changeTime;

    private BigDecimal score;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return _openid;
    }

    public void setOpenid(String openid) {
        this._openid = openid;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", openid=" + _openid +
        ", changeTime=" + changeTime +
        ", score=" + score +
        "}";
    }
}
