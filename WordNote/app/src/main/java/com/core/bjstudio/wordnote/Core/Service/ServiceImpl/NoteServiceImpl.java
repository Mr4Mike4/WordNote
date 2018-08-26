package com.core.bjstudio.wordnote.Core.Service.ServiceImpl;

import com.core.bjstudio.wordnote.Core.Model.Note;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Core.Service.NoteService;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.Util.Exception.UpdateDataException;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-23.
 */

public class NoteServiceImpl implements NoteService {
    @Override
    public RealmResults<Note> getAllEntity() {
        RealmResults<Note> notes = RealmSingleton.getInstance().getRealm().where(Note.class).findAll();
        return notes;
    }

    @Override
    public RealmResults<Note> getAllEntityAsync() {
        RealmResults<Note> notes = RealmSingleton.getInstance().getRealm().where(Note.class).findAllAsync();
        return notes;
    }

    @Override
    public Note getEntity(int id) {
        Note note = RealmSingleton.getInstance().getRealm().where(Note.class).equalTo("id", id).findFirst();
        return note;
    }

    @Override
    public Note getEntityAsync(int id) {
        Note note = RealmSingleton.getInstance().getRealm().where(Note.class).equalTo("id", id).findFirstAsync();
        return note;
    }

    @Override
    public void addEntity(Note note) throws AddDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealm(note);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new AddDataException(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Note note) throws UpdateDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealmOrUpdate(note);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new UpdateDataException(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) throws DeleteDataException, NoDataException {
        final RealmResults<Note> notes = RealmSingleton.getInstance().getRealm().where(Note.class).equalTo("id", id).findAll();
        if(notes.isEmpty()) {
            throw new NoDataException("Data is not exist - id: "+id);
        } else {
            try {
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        notes.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }
}
