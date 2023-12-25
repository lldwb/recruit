package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName record
 */
@TableName(value ="record")
@Data
public class Record implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer recordid;

    /**
     * 外键职位id
     */
    private Integer ridPositionid;

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
        Record other = (Record) that;
        return (this.getRecordid() == null ? other.getRecordid() == null : this.getRecordid().equals(other.getRecordid()))
            && (this.getRidPositionid() == null ? other.getRidPositionid() == null : this.getRidPositionid().equals(other.getRidPositionid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordid() == null) ? 0 : getRecordid().hashCode());
        result = prime * result + ((getRidPositionid() == null) ? 0 : getRidPositionid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordid=").append(recordid);
        sb.append(", ridPositionid=").append(ridPositionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}