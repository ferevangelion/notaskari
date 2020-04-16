package com.example.realmexample.model;

import com.example.realmexample.config.MyApplication;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import java.util.Date;

public class Note extends RealmObject {

  @PrimaryKey
  private int id;

  @Required
  private String title;

  @Required
  private String content;

  @Required
  private Date createdAt;

  public Note(String title, String content, Date createdAt) {
    this.id = MyApplication.NOTE_ID.incrementAndGet();
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
  }

  public Note() {
  }

  public int getId() {
    return id;
  }

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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
