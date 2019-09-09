package com.mofof.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@SQLDelete(sql = "update role_menu set del = 1 where id = ?")
@SQLDeleteAll(sql = "update role_menu set del = 1 where id = ?")
@Where(clause = "del = 0")
public class RoleMenu extends BaseEntity {

}