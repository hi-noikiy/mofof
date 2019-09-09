package com.mofof.entity.relation;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.platform.Platform;
import com.mofof.entity.system.Department;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 投资平台和部门的关系表
 * @author hzh
 * @date 2019-06-15
 */
@Entity
@ApiModel(value="投资平台和部门的关系表")
@SQLDelete(sql = "update platform_department_relation set del = 1 where id = ?")
@SQLDeleteAll(sql = "update platform_department_relation set del = 1 where id = ?")
@Where(clause = "del = 0")
public class PlatformDepartmentRelation extends BaseEntity {

    /**
     * 备注
     */
    private String note;

    /**
     * 对应的投资平台
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    Platform platform;

    /**
     * 对应的部门
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    Department department;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
