package com.mofof.entity.dict;

import javax.persistence.Embeddable;

/**
 * 字典选项
 * Created by ChenErliang on 17/5/3.
 */
@Embeddable
public class DictOption {

    private String name; 	 		// 显示选项
    private String content; 		// 内容
    private String keyName; 		// key
    private String tagText; 		// 文本标签
    private int rank; 				// 排序号
    private int tagNum; 			// 数字标签
    private boolean defaultValue; 	// 是否默认
    private String cascadeOption; 	// 级联值
    private String description; 	// 描述
    private Integer orderNum;		// ?
    private Integer numLabel;		// ?
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTagNum() {
        return tagNum;
    }

    public void setTagNum(int tagNum) {
        this.tagNum = tagNum;
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCascadeOption() {
        return cascadeOption;
    }

    public void setCascadeOption(String cascadeOption) {
        this.cascadeOption = cascadeOption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getNumLabel() {
		return numLabel;
	}

	public void setNumLabel(Integer numLabel) {
		this.numLabel = numLabel;
	}	
}
