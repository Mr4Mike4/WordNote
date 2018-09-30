package com.core.bjstudio.wordnote.Core.Controller;

import com.core.bjstudio.wordnote.Core.Model.Note;
import com.core.bjstudio.wordnote.Core.Model.NoteDetail;
import com.core.bjstudio.wordnote.Core.Model.Word;
import com.core.bjstudio.wordnote.Core.Model.WordDetail;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Core.Service.NoteDetailService;
import com.core.bjstudio.wordnote.Core.Service.NoteService;
import com.core.bjstudio.wordnote.Core.Service.ServiceImpl.NoteDetailServiceImpl;
import com.core.bjstudio.wordnote.Core.Service.ServiceImpl.NoteServiceImpl;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-20.
 */

public class NoteController {

    private NoteService noteService;
    private NoteDetailService noteDetailService;

    private NoteController() {
        this.noteService = new NoteServiceImpl();
        this.noteDetailService = new NoteDetailServiceImpl();
    }

    public static NoteController getInstance(){
        return SingletonLazyHolder.INSTANCE;
    }

    private static class SingletonLazyHolder {
        private static final NoteController INSTANCE = new NoteController();
    }

    public RealmResults<Note> getAllNotes() {
        RealmResults<Note> notes = noteService.getAllEntity();
        return notes;
    }

    public void addNewNote(String name) throws AddDataException {
        int noteIndex = RealmSingleton.getInstance().getAutoIncrementIndex(Note.class);
        int noteDetailIndex = RealmSingleton.getInstance().getAutoIncrementIndex(NoteDetail.class);

        NoteDetail noteDetail = new NoteDetail(noteDetailIndex, name);
        Note note = new Note(noteIndex, noteDetail);

        this.noteService.addEntity(note);

    }

    private void addNewWordForTesting(NoteDetail noteDetail) {
        RealmList<Word> wordRealmList = new RealmList<>();
        for(int i = 0; i < 10; i++) {
            int index = (i+1);
            wordRealmList.add(new Word(index, new WordDetail(index, ("test"+i), null)));
        }

        noteDetail.setWords(wordRealmList);
    }

    public void deleteAllNotes() throws NoDataException, DeleteDataException {
        this.noteService.deleteAllEntity();
        //this.noteDetailService.deleteAllEntity();
    }
}
