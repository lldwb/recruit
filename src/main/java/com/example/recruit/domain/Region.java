package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 地区表
 * @TableName region
 */
@TableName(value ="region")
@Data
public class Region implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer regionid;

    /**
     * 地区名称
     */
    private String regionname;

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
        Region other = (Region) that;
        return (this.getRegionid() == null ? other.getRegionid() == null : this.getRegionid().equals(other.getRegionid()))
            && (this.getRegionname() == null ? other.getRegionname() == null : this.getRegionname().equals(other.getRegionname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRegionid() == null) ? 0 : getRegionid().hashCode());
        result = prime * result + ((getRegionname() == null) ? 0 : getRegionname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", regionid=").append(regionid);
        sb.append(", regionname=").append(regionname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}