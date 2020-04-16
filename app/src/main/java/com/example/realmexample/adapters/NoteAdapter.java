package com.example.realmexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.realmexample.R;
import com.example.realmexample.model.Note;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class NoteAdapter extends BaseAdapter {

  private Context context;
  private List<Note> notes;
  private int layout;

  public NoteAdapter(Context context, List<Note> notes, int layout) {
    this.context = context;
    this.notes = notes;
    this.layout = layout;
  }

  @Override
  public int getCount() {
    return notes.size();
  }

  @Override
  public Note getItem(int position) {
    return notes.get(position);
  }

  @Override
  public long getItemId(int id) {
    return id;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder vh;
    if (convertView == null) {
      vh = new ViewHolder();
      convertView = LayoutInflater.from(context).inflate(R.layout.list_note_item, null);
      vh.title = convertView.findViewById(R.id.textViewNoteTitle);
      vh.createdAt = convertView.findViewById(R.id.textViewNoteCreatedAt);
      convertView.setTag(vh);
    } else {
      vh = (ViewHolder) convertView.getTag();
    }

    Note note = notes.get(position);
    vh.title.setText(note.getTitle());
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    vh.createdAt.setText(df.format(note.getCreatedAt()));
    return convertView;
  }

  public class ViewHolder {
    public TextView title;
    public TextView createdAt;
  }
}
