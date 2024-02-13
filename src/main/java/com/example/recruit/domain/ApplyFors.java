package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 申请记录表
 *
 * @TableName apply_for
 */
@TableName(value = "apply_for")
@Data
public class
ApplyFors implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer applyForId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 职位id
     */
    private Integer positionId;

    /**
     * 是否通过1为是通过 2为未通过
     */
    private String applyForState;

    /**
     * 未通过原因
     */
    private String applyForCause;
    private Position position;
    private User user;

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
        ApplyFors other = (ApplyFors) that;
        return (this.getApplyForId() == null ? other.getApplyForId() == null : this.getApplyForId().equals(other.getApplyForId())) && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId())) && (this.getPositionId() == null ? other.getPositionId() == null : this.getPositionId().equals(other.getPositionId())) && (this.getApplyForState() == null ? other.getApplyForState() == null : this.getApplyForState().equals(other.getApplyForState())) && (this.getApplyForCause() == null ? other.getApplyForCause() == null : this.getApplyForCause().equals(other.getApplyForCause()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getApplyForId() == null) ? 0 : getApplyForId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPositionId() == null) ? 0 : getPositionId().hashCode());
        result = prime * result + ((getApplyForState() == null) ? 0 : getApplyForState().hashCode());
        result = prime * result + ((getApplyForCause() == null) ? 0 : getApplyForCause().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", applyForId=").append(applyForId);
        sb.append(", userId=").append(userId);
        sb.append(", positionId=").append(positionId);
        sb.append(", applyForState=").append(applyForState);
        sb.append(", applyForCause=").append(applyForCause);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}