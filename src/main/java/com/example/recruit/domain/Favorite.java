package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName favorite
 */
@TableName(value ="favorite")
@Data
public class Favorite implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer favoriteid;

    /**
     * 外键职位Id
     */
    private Integer fidPositionid;

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
        Favorite other = (Favorite) that;
        return (this.getFavoriteid() == null ? other.getFavoriteid() == null : this.getFavoriteid().equals(other.getFavoriteid()))
            && (this.getFidPositionid() == null ? other.getFidPositionid() == null : this.getFidPositionid().equals(other.getFidPositionid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFavoriteid() == null) ? 0 : getFavoriteid().hashCode());
        result = prime * result + ((getFidPositionid() == null) ? 0 : getFidPositionid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", favoriteid=").append(favoriteid);
        sb.append(", fidPositionid=").append(fidPositionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}