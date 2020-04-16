package com.example.realmexample.model;

import com.example.realmexample.config.MyApplication;
import com.example.realmexample.model.Note;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import java.util.Date;

public class Board extends RealmObject {

  @PrimaryKey
  private int id;

  @Required
  private String title;

  @Required
  private Date createdAt;

  private RealmList<Note> notes;

  public Board() {
  }

  public Board(String title) {
    this.id = MyApplication.BOARD_ID.incrementAndGet();
    this.title = title;
    this.createdAt = new Date();
    this.notes = new RealmList<Note>();
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

  public Date getCreatedAt() {
    return createdAt;
  }

  public RealmList<Note> getNotes() {
    return notes;
  }
}
