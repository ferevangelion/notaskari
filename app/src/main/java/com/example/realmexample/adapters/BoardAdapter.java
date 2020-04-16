package com.example.realmexample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.realmexample.R;
import com.example.realmexample.model.Board;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BoardAdapter extends BaseAdapter {

  private Context context;
  private List<Board> boards;
  private int layout;

  public BoardAdapter(@NonNull Context context, @NonNull List<Board> boards, int layout) {
    this.context = context;
    this.boards = boards;
    this.layout = layout;
  }

  @Override public int getCount() {
    return boards.size();
  }

  @Override
  public Board getItem(int position) {
    return boards.get(position);
  }

  @Override
  public long getItemId(int id) {
    return id;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup viewGroup) {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    ViewHolder vh;
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(layout, null);
      vh = new ViewHolder();
      vh.title = convertView.findViewById(R.id.textViewBoardTitle);
      vh.notes = convertView.findViewById(R.id.textViewBoardNotes);
      vh.createdAt = convertView.findViewById(R.id.textViewBoardDate);
      convertView.setTag(vh);


    } else {
      vh = (ViewHolder) convertView.getTag();

    }
    Board board = boards.get(position);
    vh.title.setText(board.getTitle());
    String notesStr = (board.getNotes().size() == 1) ? " Note" : " Notes";
    vh.notes.setText(String.valueOf(board.getNotes().size()) + notesStr);
    vh.createdAt.setText(df.format(board.getCreatedAt()));

    return convertView;
  }

  public class ViewHolder {
    public TextView title;
    public TextView notes;
    public TextView createdAt;
  }
}
