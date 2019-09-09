package com.mofof.entity.common;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * 财务报表科目
 * Created by chenerliang on 2017/7/27.
 */
@Embeddable
public class FinanceItem {

    private String name;  //科目名称
    private String description; //描述
    private boolean titleItem; //是否标题
    private int align; //对齐 0-左对齐 1-居中 2-右对齐
    private int indent; //缩进次数
    private boolean bold; //是否加粗
    private boolean italic; //是否斜体
    private boolean underscore; //是否加下划线
    private String fontColor; //字体颜色
    private String backgroundColor; //背景颜色
    private String innerRelation; //表内钩稽关系
    private String outerRelation; //表间钩稽关系
    @Transient
    private String multiRelation; //跨表钩稽关系

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTitleItem() {
        return titleItem;
    }

    public void setTitleItem(boolean titleItem) {
        this.titleItem = titleItem;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderscore() {
        return underscore;
    }

    public void setUnderscore(boolean underscore) {
        this.underscore = underscore;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getInnerRelation() {
        return innerRelation;
    }

    public void setInnerRelation(String innerRelation) {
        this.innerRelation = innerRelation;
    }

    public String getOuterRelation() {
        return outerRelation;
    }

    public void setOuterRelation(String outerRelation) {
        this.outerRelation = outerRelation;
    }

    public String getMultiRelation() {
        return multiRelation;
    }

    public void setMultiRelation(String multiRelation) {
        this.multiRelation = multiRelation;
    }
}
