package com.mofof.entity.dict;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 字典项 
 * Created by ChenErliang on 17/5/3.
 */
@Entity
@SQLDelete(sql = "update dict_item set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_item set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictItem extends BaseEntity {
	public static final int SYSTEM_DICT_TYPE = 0;
	public static final int USER_DICT_TYPE = 1;
	public static final int ADVANCED_DICT_TYPE = 2;

	private String name;
	@Column(unique = true)
	private String keyName;
	@ElementCollection
	private List<DictOption> options;
	private String cascadeKey;    // 上级联动项的key null或空-非联动
	private int dictType;    	  // 0-系统 1-用户
	private Boolean allowInput;   // 允许输入框
	private Boolean logic; 		  // ?
	private Boolean multiple; 	  // 允许多选

	public Boolean getAllowInput() {
		return allowInput;
	}

	public void setAllowInput(Boolean allowInput) {
		this.allowInput = allowInput;
	}

	public Boolean getLogic() {
		return logic;
	}

	public void setLogic(Boolean logic) {
		this.logic = logic;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public List<DictOption> getOptions() {
		return options;
	}

	public void setOptions(List<DictOption> options) {
		this.options = options;
	}

	public String getCascadeKey() {
		return cascadeKey;
	}

	public void setCascadeKey(String cascadeKey) {
		this.cascadeKey = cascadeKey;
	}

	public int getDictType() {
		return dictType;
	}

	public void setDictType(int dictType) {
		this.dictType = dictType;
	}
}
