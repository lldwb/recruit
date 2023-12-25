package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName inform
 */
@TableName(value ="inform")
@Data
public class Inform implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer informid;

    /**
     * 外键用户id
     */
    private Integer iidUserid;

    /**
     * 外键职位id
     */
    private Integer iidPositionid;

    /**
     * 具体信息
     */
    private String message;

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
        Inform other = (Inform) that;
        return (this.getInformid() == null ? other.getInformid() == null : this.getInformid().equals(other.getInformid()))
            && (this.getIidUserid() == null ? other.getIidUserid() == null : this.getIidUserid().equals(other.getIidUserid()))
            && (this.getIidPositionid() == null ? other.getIidPositionid() == null : this.getIidPositionid().equals(other.getIidPositionid()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInformid() == null) ? 0 : getInformid().hashCode());
        result = prime * result + ((getIidUserid() == null) ? 0 : getIidUserid().hashCode());
        result = prime * result + ((getIidPositionid() == null) ? 0 : getIidPositionid().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", informid=").append(informid);
        sb.append(", iidUserid=").append(iidUserid);
        sb.append(", iidPositionid=").append(iidPositionid);
        sb.append(", message=").append(message);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}