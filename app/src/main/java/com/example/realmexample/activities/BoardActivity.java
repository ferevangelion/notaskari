package com.example.realmexample.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.example.realmexample.model.Board;
import com.example.realmexample.adapters.BoardAdapter;
import com.example.realmexample.R;
import io.realm.Realm;
import io.realm.RealmResults;

public class BoardActivity extends AppCompatActivity {

  private FloatingActionButton floatingActionButton;
  private Realm realm;

  private ListView listView;
  private BoardAdapter boardAdapter;
  private RealmResults<Board> boards;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_board);

    realm = Realm.getDefaultInstance();

    floatingActionButton = findViewById(R.id.fabAddBoard);

    floatingActionButton.setOnClickListener(v -> {
      showAlertForCreatingBoard("Add New Board", "Type a name for your new board");
    });

    boards = realm.where(Board.class).findAll();
    boards.addChangeListener(bs -> {
      boardAdapter.notifyDataSetChanged();
      listView.smoothScrollToPosition(boards.size());
    });
    boardAdapter = new BoardAdapter(this, boards, R.layout.list_board_item);
    listView = findViewById(R.id.listViewBoard);
    listView.setAdapter(boardAdapter);
    listView.setOnItemClickListener((parent, view, position, id) -> {
      Intent intent = new Intent(BoardActivity.this, NoteActivity.class);
      intent.putExtra("id", boards.get(position).getId());
      startActivity(intent);
    });
  }

  private void createNewBoard(String boardName) {
    realm.executeTransaction(r -> {
          Board board = new Board(boardName);
          r.copyToRealm(board);
        }
    );
  }

  private void showAlertForCreatingBoard(String title, String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(title);
    builder.setMessage(message);
    View view = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
    builder.setView(view);

    EditText input = view.findViewById(R.id.editTextBoardTitle);

    builder.setPositiveButton("Add", (di, i) -> {
      String boardName = input.getText().toString();
      createNewBoard(boardName);
    });

    builder.create().show();
  }
}
