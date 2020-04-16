package com.example.realmexample.config;

import android.app.Application;
import com.example.realmexample.model.Board;
import com.example.realmexample.model.Note;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import java.util.concurrent.atomic.AtomicInteger;

public class MyApplication extends Application {

  public static AtomicInteger BOARD_ID = new AtomicInteger();
  public static AtomicInteger NOTE_ID = new AtomicInteger();

  @Override
  public void onCreate() {
    super.onCreate();

    Realm.init(getApplicationContext());
    setUpRealmConfig();

    Realm realm = Realm.getDefaultInstance();
    BOARD_ID = getIdByTable(realm, Board.class);
    NOTE_ID = getIdByTable(realm, Note.class);
    realm.close();
  }

  private void setUpRealmConfig() {
    RealmConfiguration config = new RealmConfiguration
        .Builder()
        .deleteRealmIfMigrationNeeded()
        .build();
    Realm.setDefaultConfiguration(config);
  }

  private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {

    RealmResults<T> results = realm.where(anyClass).findAll();
    return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue())
        : new AtomicInteger();
  }
}
