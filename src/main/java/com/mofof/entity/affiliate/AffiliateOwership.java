package com.mofof.entity.affiliate;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 17/6/9.
 * 关联结构股权变更
 */
@Entity
@SQLDelete(sql = "update affiliate_owership set del = 1 where id = ?")
@SQLDeleteAll(sql = "update affiliate_owership set del = 1 where id = ?")
@Where(clause = "del = 0")
public class AffiliateOwership extends BaseEntity {

    @ManyToOne
    private Affiliate affiliate; //相关关联机构
    private LocalDate changeDate;//变更日期
    @ElementCollection
    private List<AffiliateOwershipDetail> affiliateOwershipDetails; //详细变更明细（变更后的所有投资人信息）

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public List<AffiliateOwershipDetail> getAffiliateOwershipDetails() {
        return affiliateOwershipDetails;
    }

    public void setAffiliateOwershipDetails(List<AffiliateOwershipDetail> affiliateOwershipDetails) {
        this.affiliateOwershipDetails = affiliateOwershipDetails;
    }
}
