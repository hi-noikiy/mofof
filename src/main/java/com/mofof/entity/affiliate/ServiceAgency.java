package com.mofof.entity.affiliate;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Organization;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by ChenErliang on 17/6/9.
 * 外服机构
 */
@Entity
@SQLDelete(sql = "update service_agency set del = 1 where id = ?")
@SQLDeleteAll(sql = "update service_agency set del = 1 where id = ?")
@Where(clause = "del = 0")
public class ServiceAgency extends BaseEntity {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Organization organization;
    private String serviceType;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
