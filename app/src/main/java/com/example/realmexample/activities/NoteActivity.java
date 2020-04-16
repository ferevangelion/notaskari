package com.example.realmexample.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.realmexample.R;
import com.example.realmexample.adapters.NoteAdapter;
import com.example.realmexample.model.Board;
import com.example.realmexample.model.Note;
import io.realm.Realm;
import io.realm.RealmList;

public class NoteActivity extends AppCompatActivity {

  private ListView listView;
  private FloatingActionButton fab;
  private NoteAdapter noteAdapter;
  private RealmList<Note> notes;
  private Realm realm;
  private int boardId;
  private Board board;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_note);
    realm = Realm.getDefaultInstance();

    boardId = getIntent().getExtras().getInt("id");
    board = realm.where(Board.class).equalTo("id", boardId).findFirst();
    notes = board.getNotes();

    this.setTitle(board.getTitle());
  }
}
