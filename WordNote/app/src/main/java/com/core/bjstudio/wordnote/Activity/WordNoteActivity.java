package com.core.bjstudio.wordnote.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.core.bjstudio.wordnote.Core.Controller.NoteController;
import com.core.bjstudio.wordnote.Core.Model.Note;
import com.core.bjstudio.wordnote.Core.Model.NoteDetail;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.R;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.CustomLog;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.View.RecyclerAdapter;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class WordNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_note);

        try {
            CustomLog.info("deleteStart");
            long start = System.currentTimeMillis();
            NoteController.getInstance().deleteAllNotes();
            CustomLog.info("deleteEnd");
            long end = System.currentTimeMillis();
            CustomLog.info( "실행 시간 : " + ( end - start )/1000.0 );
        } catch (NoDataException e) {
            e.printStackTrace();
        } catch (DeleteDataException e) {
            e.printStackTrace();
        }

        try {
            long start = System.currentTimeMillis();
            for(int i = 1; i < 11; i++) {
                NoteController.getInstance().addNewNote(("test"+i));
            }
            long end = System.currentTimeMillis();
            CustomLog.info( "실행 시간 : " + ( end - start )/1000.0 );
        } catch (AddDataException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        RealmResults<Note> notes = NoteController.getInstance().getAllNotes();

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), notes, R.layout.activity_word_note));
    }
}
