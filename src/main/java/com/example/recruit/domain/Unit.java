package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 单位表
 * @TableName unit
 */
@TableName(value ="unit")
@Data
public class Unit implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer unitid;

    /**
     * 名称
     */
    private String unitname;

    /**
     * 外键地区Id
     */
    private Integer uidRegionid;

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
        Unit other = (Unit) that;
        return (this.getUnitid() == null ? other.getUnitid() == null : this.getUnitid().equals(other.getUnitid()))
            && (this.getUnitname() == null ? other.getUnitname() == null : this.getUnitname().equals(other.getUnitname()))
            && (this.getUidRegionid() == null ? other.getUidRegionid() == null : this.getUidRegionid().equals(other.getUidRegionid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUnitid() == null) ? 0 : getUnitid().hashCode());
        result = prime * result + ((getUnitname() == null) ? 0 : getUnitname().hashCode());
        result = prime * result + ((getUidRegionid() == null) ? 0 : getUidRegionid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", unitid=").append(unitid);
        sb.append(", unitname=").append(unitname);
        sb.append(", uidRegionid=").append(uidRegionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}