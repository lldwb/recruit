package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 手机号码
     */
    private Long userPhone;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private String userGender;

    /**
     * 年龄
     */
    private Integer userAge;

    /**
     * 民族
     */
    private String userNation;

    /**
     * 身高
     */
    private Double userStature;

    /**
     * 体重
     */
    private Double userWeight;

    /**
     * 是否服从分配 0未是 1为否
     */
    private Integer userObey;

    /**
     * 是否住宿 0未是 1为否
     */
    private Integer userPutUp;

    /**
     * 状态 0为删除
     */
    private Integer userState;

    /**
     * 身份证
     */
    private String userIdentityCard;

    /**
     * 头像地址
     */
    private String userHeadPortrait;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserGender() == null ? other.getUserGender() == null : this.getUserGender().equals(other.getUserGender()))
            && (this.getUserAge() == null ? other.getUserAge() == null : this.getUserAge().equals(other.getUserAge()))
            && (this.getUserNation() == null ? other.getUserNation() == null : this.getUserNation().equals(other.getUserNation()))
            && (this.getUserStature() == null ? other.getUserStature() == null : this.getUserStature().equals(other.getUserStature()))
            && (this.getUserWeight() == null ? other.getUserWeight() == null : this.getUserWeight().equals(other.getUserWeight()))
            && (this.getUserObey() == null ? other.getUserObey() == null : this.getUserObey().equals(other.getUserObey()))
            && (this.getUserPutUp() == null ? other.getUserPutUp() == null : this.getUserPutUp().equals(other.getUserPutUp()))
            && (this.getUserState() == null ? other.getUserState() == null : this.getUserState().equals(other.getUserState()))
            && (this.getUserIdentityCard() == null ? other.getUserIdentityCard() == null : this.getUserIdentityCard().equals(other.getUserIdentityCard()))
            && (this.getUserHeadPortrait() == null ? other.getUserHeadPortrait() == null : this.getUserHeadPortrait().equals(other.getUserHeadPortrait()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserGender() == null) ? 0 : getUserGender().hashCode());
        result = prime * result + ((getUserAge() == null) ? 0 : getUserAge().hashCode());
        result = prime * result + ((getUserNation() == null) ? 0 : getUserNation().hashCode());
        result = prime * result + ((getUserStature() == null) ? 0 : getUserStature().hashCode());
        result = prime * result + ((getUserWeight() == null) ? 0 : getUserWeight().hashCode());
        result = prime * result + ((getUserObey() == null) ? 0 : getUserObey().hashCode());
        result = prime * result + ((getUserPutUp() == null) ? 0 : getUserPutUp().hashCode());
        result = prime * result + ((getUserState() == null) ? 0 : getUserState().hashCode());
        result = prime * result + ((getUserIdentityCard() == null) ? 0 : getUserIdentityCard().hashCode());
        result = prime * result + ((getUserHeadPortrait() == null) ? 0 : getUserHeadPortrait().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userName=").append(userName);
        sb.append(", userGender=").append(userGender);
        sb.append(", userAge=").append(userAge);
        sb.append(", userNation=").append(userNation);
        sb.append(", userStature=").append(userStature);
        sb.append(", userWeight=").append(userWeight);
        sb.append(", userObey=").append(userObey);
        sb.append(", userPutUp=").append(userPutUp);
        sb.append(", userState=").append(userState);
        sb.append(", userIdentityCard=").append(userIdentityCard);
        sb.append(", userHeadPortrait=").append(userHeadPortrait);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}