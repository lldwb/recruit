package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 申请记录表
 * @TableName applyfor
 */
@TableName(value ="applyfor")
@Data
public class Applyfor implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer applyforid;

    /**
     * 外键用户id
     */
    private Integer aidUserid;

    /**
     * 外键职位id
     */
    private Integer aidPositionid;

    /**
     * 是否通过1为是通过 2为未通过
     */
    private String state;

    /**
     * 未通过原因
     */
    private String cause;

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
        Applyfor other = (Applyfor) that;
        return (this.getApplyforid() == null ? other.getApplyforid() == null : this.getApplyforid().equals(other.getApplyforid()))
            && (this.getAidUserid() == null ? other.getAidUserid() == null : this.getAidUserid().equals(other.getAidUserid()))
            && (this.getAidPositionid() == null ? other.getAidPositionid() == null : this.getAidPositionid().equals(other.getAidPositionid()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCause() == null ? other.getCause() == null : this.getCause().equals(other.getCause()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getApplyforid() == null) ? 0 : getApplyforid().hashCode());
        result = prime * result + ((getAidUserid() == null) ? 0 : getAidUserid().hashCode());
        result = prime * result + ((getAidPositionid() == null) ? 0 : getAidPositionid().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCause() == null) ? 0 : getCause().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", applyforid=").append(applyforid);
        sb.append(", aidUserid=").append(aidUserid);
        sb.append(", aidPositionid=").append(aidPositionid);
        sb.append(", state=").append(state);
        sb.append(", cause=").append(cause);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}