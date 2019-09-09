package com.mofof.entity.project;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Created by hzh on 17/7/4.
 * 项目投资行为 （投资明细，父类）
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "update investment_behavior set del = 1 where id = ?")
@SQLDeleteAll(sql = "update investment_behavior set del = 1 where id = ?")
@Where(clause = "del = 0")
public class InvestmentBehavior extends BaseEntity {
    @ManyToOne
    private ProjectInvest projectInvest; //对应投资关系

    private LocalDate behaviorDate; //投资日期/退出日期


    public LocalDate getBehaviorDate() {
        return behaviorDate;
    }

    public void setBehaviorDate(LocalDate behaviorDate) {
        this.behaviorDate = behaviorDate;
    }

    public ProjectInvest getProjectInvest() {
        return projectInvest;
    }

    public void setProjectInvest(ProjectInvest projectInvest) {
        this.projectInvest = projectInvest;
    }
}
