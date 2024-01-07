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
     * 职位编号
     */
    @TableId(type = IdType.AUTO)
    private Integer positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 职位工资
     */
    private BigDecimal positionSalary;

    /**
     * 岗位要求
     */
    private String positionJobRequirements;

    /**
     * 岗位职责
     */
    private String positionRtatement;

    /**
     * 食宿安排
     */
    private String positionArrangement;

    /**
     * 其他说明
     */
    private String positionOtherDescription;

    /**
     * 单位Id
     */
    private Integer unitId;

    /**
     * 工作周期
     */
    private String positionPeriod;

    /**
     * 用工时期
     */
    private String positionPeriodYong;

    /**
     * 用工类型(招聘开始时间)
     */
    private String positionStartTime;

    /**
     * 用工类型(招聘结束时间)
     */
    private String positionEndTime;

    /**
     * 热度
     */
    private String positionHeat;

    /**
     *  
     */
    private Integer positionNumber;

    /**
     * 所属单位
     */
    private String positionAffiliatedUnit;

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
        return (this.getPositionId() == null ? other.getPositionId() == null : this.getPositionId().equals(other.getPositionId()))
            && (this.getPositionName() == null ? other.getPositionName() == null : this.getPositionName().equals(other.getPositionName()))
            && (this.getPositionSalary() == null ? other.getPositionSalary() == null : this.getPositionSalary().equals(other.getPositionSalary()))
            && (this.getPositionJobRequirements() == null ? other.getPositionJobRequirements() == null : this.getPositionJobRequirements().equals(other.getPositionJobRequirements()))
            && (this.getPositionRtatement() == null ? other.getPositionRtatement() == null : this.getPositionRtatement().equals(other.getPositionRtatement()))
            && (this.getPositionArrangement() == null ? other.getPositionArrangement() == null : this.getPositionArrangement().equals(other.getPositionArrangement()))
            && (this.getPositionOtherDescription() == null ? other.getPositionOtherDescription() == null : this.getPositionOtherDescription().equals(other.getPositionOtherDescription()))
            && (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId()))
            && (this.getPositionPeriod() == null ? other.getPositionPeriod() == null : this.getPositionPeriod().equals(other.getPositionPeriod()))
            && (this.getPositionPeriodYong() == null ? other.getPositionPeriodYong() == null : this.getPositionPeriodYong().equals(other.getPositionPeriodYong()))
            && (this.getPositionStartTime() == null ? other.getPositionStartTime() == null : this.getPositionStartTime().equals(other.getPositionStartTime()))
            && (this.getPositionEndTime() == null ? other.getPositionEndTime() == null : this.getPositionEndTime().equals(other.getPositionEndTime()))
            && (this.getPositionHeat() == null ? other.getPositionHeat() == null : this.getPositionHeat().equals(other.getPositionHeat()))
            && (this.getPositionNumber() == null ? other.getPositionNumber() == null : this.getPositionNumber().equals(other.getPositionNumber()))
            && (this.getPositionAffiliatedUnit() == null ? other.getPositionAffiliatedUnit() == null : this.getPositionAffiliatedUnit().equals(other.getPositionAffiliatedUnit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPositionId() == null) ? 0 : getPositionId().hashCode());
        result = prime * result + ((getPositionName() == null) ? 0 : getPositionName().hashCode());
        result = prime * result + ((getPositionSalary() == null) ? 0 : getPositionSalary().hashCode());
        result = prime * result + ((getPositionJobRequirements() == null) ? 0 : getPositionJobRequirements().hashCode());
        result = prime * result + ((getPositionRtatement() == null) ? 0 : getPositionRtatement().hashCode());
        result = prime * result + ((getPositionArrangement() == null) ? 0 : getPositionArrangement().hashCode());
        result = prime * result + ((getPositionOtherDescription() == null) ? 0 : getPositionOtherDescription().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getPositionPeriod() == null) ? 0 : getPositionPeriod().hashCode());
        result = prime * result + ((getPositionPeriodYong() == null) ? 0 : getPositionPeriodYong().hashCode());
        result = prime * result + ((getPositionStartTime() == null) ? 0 : getPositionStartTime().hashCode());
        result = prime * result + ((getPositionEndTime() == null) ? 0 : getPositionEndTime().hashCode());
        result = prime * result + ((getPositionHeat() == null) ? 0 : getPositionHeat().hashCode());
        result = prime * result + ((getPositionNumber() == null) ? 0 : getPositionNumber().hashCode());
        result = prime * result + ((getPositionAffiliatedUnit() == null) ? 0 : getPositionAffiliatedUnit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", positionId=").append(positionId);
        sb.append(", positionName=").append(positionName);
        sb.append(", positionSalary=").append(positionSalary);
        sb.append(", positionJobRequirements=").append(positionJobRequirements);
        sb.append(", positionRtatement=").append(positionRtatement);
        sb.append(", positionArrangement=").append(positionArrangement);
        sb.append(", positionOtherDescription=").append(positionOtherDescription);
        sb.append(", unitId=").append(unitId);
        sb.append(", positionPeriod=").append(positionPeriod);
        sb.append(", positionPeriodYong=").append(positionPeriodYong);
        sb.append(", positionStartTime=").append(positionStartTime);
        sb.append(", positionEndTime=").append(positionEndTime);
        sb.append(", positionHeat=").append(positionHeat);
        sb.append(", positionNumber=").append(positionNumber);
        sb.append(", positionAffiliatedUnit=").append(positionAffiliatedUnit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}