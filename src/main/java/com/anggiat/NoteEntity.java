package com.anggiat;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "t_note")
public class NoteEntity extends PanacheEntity {
    private String title;
    private String content;
    private OffsetDateTime updateAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(OffsetDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public NoteEntity() {
    }

    public NoteEntity(String title, String content, OffsetDateTime updateAt) {
        this.title = title;
        this.content = content;
        this.updateAt = updateAt;
    }

}
