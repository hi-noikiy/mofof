package com.mofof.entity.common;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Created by ChenErliang on 17/6/9.
 */
@Entity
@SQLDelete(sql = "update news set del = 1 where id = ?")
@SQLDeleteAll(sql = "update news set del = 1 where id = ?")
@Where(clause = "del = 0")
public class News extends BaseEntity {

    private LocalDate publishDate;
    private String title;
    private String source;

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
