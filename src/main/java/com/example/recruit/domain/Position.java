package com.example.recruit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 职位工作表
 * @TableName position
 */
@TableName(value ="position")
@Data
public class Position implements Serializable {
    /**
     * 单位编号
     */
    @TableId(type = IdType.AUTO)
    private Integer positionid;

    /**
     * 单位名称
     */
    private String positionname;

    /**
     * 单位工资
     */
    private BigDecimal salary;

    /**
     * 岗位要求
     */
    private String jobrequirements;

    /**
     * 岗位职责
     */
    private String positionrtatement;

    /**
     * 食宿安排
     */
    private String arrangement;

    /**
     * 其他说明
     */
    private String otherdescription;

    /**
     * 外键单位Id
     */
    private Integer pidUnitid;

    /**
     * 工作周期
     */
    private String period;

    /**
     * 用工时期
     */
    private String periodyong;

    /**
     * 用工类型(招聘开始时间)
     */
    private String starttime;

    /**
     * 用工类型(招聘结束时间)
     */
    private String endtime;

    /**
     * 热度
     */
    private String heat;

    /**
     * 所需人数 未招满状态为1(招聘中)，满2000状态为2已招满)
     */
    private String positionstate;

    /**
     * 所属单位
     */
    private String affiliatedunit;

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
        Position other = (Position) that;
        return (this.getPositionid() == null ? other.getPositionid() == null : this.getPositionid().equals(other.getPositionid()))
            && (this.getPositionname() == null ? other.getPositionname() == null : this.getPositionname().equals(other.getPositionname()))
            && (this.getSalary() == null ? other.getSalary() == null : this.getSalary().equals(other.getSalary()))
            && (this.getJobrequirements() == null ? other.getJobrequirements() == null : this.getJobrequirements().equals(other.getJobrequirements()))
            && (this.getPositionrtatement() == null ? other.getPositionrtatement() == null : this.getPositionrtatement().equals(other.getPositionrtatement()))
            && (this.getArrangement() == null ? other.getArrangement() == null : this.getArrangement().equals(other.getArrangement()))
            && (this.getOtherdescription() == null ? other.getOtherdescription() == null : this.getOtherdescription().equals(other.getOtherdescription()))
            && (this.getPidUnitid() == null ? other.getPidUnitid() == null : this.getPidUnitid().equals(other.getPidUnitid()))
            && (this.getPeriod() == null ? other.getPeriod() == null : this.getPeriod().equals(other.getPeriod()))
            && (this.getPeriodyong() == null ? other.getPeriodyong() == null : this.getPeriodyong().equals(other.getPeriodyong()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getHeat() == null ? other.getHeat() == null : this.getHeat().equals(other.getHeat()))
            && (this.getPositionstate() == null ? other.getPositionstate() == null : this.getPositionstate().equals(other.getPositionstate()))
            && (this.getAffiliatedunit() == null ? other.getAffiliatedunit() == null : this.getAffiliatedunit().equals(other.getAffiliatedunit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPositionid() == null) ? 0 : getPositionid().hashCode());
        result = prime * result + ((getPositionname() == null) ? 0 : getPositionname().hashCode());
        result = prime * result + ((getSalary() == null) ? 0 : getSalary().hashCode());
        result = prime * result + ((getJobrequirements() == null) ? 0 : getJobrequirements().hashCode());
        result = prime * result + ((getPositionrtatement() == null) ? 0 : getPositionrtatement().hashCode());
        result = prime * result + ((getArrangement() == null) ? 0 : getArrangement().hashCode());
        result = prime * result + ((getOtherdescription() == null) ? 0 : getOtherdescription().hashCode());
        result = prime * result + ((getPidUnitid() == null) ? 0 : getPidUnitid().hashCode());
        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());
        result = prime * result + ((getPeriodyong() == null) ? 0 : getPeriodyong().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getHeat() == null) ? 0 : getHeat().hashCode());
        result = prime * result + ((getPositionstate() == null) ? 0 : getPositionstate().hashCode());
        result = prime * result + ((getAffiliatedunit() == null) ? 0 : getAffiliatedunit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", positionid=").append(positionid);
        sb.append(", positionname=").append(positionname);
        sb.append(", salary=").append(salary);
        sb.append(", jobrequirements=").append(jobrequirements);
        sb.append(", positionrtatement=").append(positionrtatement);
        sb.append(", arrangement=").append(arrangement);
        sb.append(", otherdescription=").append(otherdescription);
        sb.append(", pidUnitid=").append(pidUnitid);
        sb.append(", period=").append(period);
        sb.append(", periodyong=").append(periodyong);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", heat=").append(heat);
        sb.append(", positionstate=").append(positionstate);
        sb.append(", affiliatedunit=").append(affiliatedunit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}