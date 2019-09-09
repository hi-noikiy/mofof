package com.mofof.entity.dict;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update dict_term set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_term set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictTerm extends BaseEntity{

	private int rank;              // 排序号
	private String term;           // 条款
	private String name;
	@Column(unique=true)
	private String keyName;        // key
	private String content;        // 内容
	private boolean defaultValue;  // 默认值
	private String tagText;        // 文本标签
	private int tagNum;            // 数字标签
	private String description;    // 描述
	private String cascadeOption;  // 级联值
	
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getTagText() {
		return tagText;
	}
	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
	public int getTagNum() {
		return tagNum;
	}
	public void setTagNum(int tagNum) {
		this.tagNum = tagNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getCascadeOption() {
		return cascadeOption;
	}
	public void setCascadeOption(String cascadeOption) {
		this.cascadeOption = cascadeOption;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}		
}
