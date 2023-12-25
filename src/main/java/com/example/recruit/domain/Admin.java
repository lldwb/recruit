package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 管理员表
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer adminid;

    /**
     * 管理员名称
     */
    private String adminname;

    /**
     * 密码
     */
    private String adminpwd;

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
        Admin other = (Admin) that;
        return (this.getAdminid() == null ? other.getAdminid() == null : this.getAdminid().equals(other.getAdminid()))
            && (this.getAdminname() == null ? other.getAdminname() == null : this.getAdminname().equals(other.getAdminname()))
            && (this.getAdminpwd() == null ? other.getAdminpwd() == null : this.getAdminpwd().equals(other.getAdminpwd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdminid() == null) ? 0 : getAdminid().hashCode());
        result = prime * result + ((getAdminname() == null) ? 0 : getAdminname().hashCode());
        result = prime * result + ((getAdminpwd() == null) ? 0 : getAdminpwd().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminid=").append(adminid);
        sb.append(", adminname=").append(adminname);
        sb.append(", adminpwd=").append(adminpwd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}