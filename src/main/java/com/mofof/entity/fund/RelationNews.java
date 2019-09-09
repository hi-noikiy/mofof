package com.mofof.entity.fund;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.News;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by ChenErliang on 17/6/9.
 * 相关新闻
 */
@Entity
@SQLDelete(sql = "update relation_news set del = 1 where id = ?")
@SQLDeleteAll(sql = "update relation_news set del = 1 where id = ?")
@Where(clause = "del = 0")
public class RelationNews extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    private Fund fund;
    @ManyToOne
    private News news;
    private String relationNote;
    private boolean save;
    private int importance;

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getRelationNote() {
        return relationNote;
    }

    public void setRelationNote(String relationNote) {
        this.relationNote = relationNote;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
