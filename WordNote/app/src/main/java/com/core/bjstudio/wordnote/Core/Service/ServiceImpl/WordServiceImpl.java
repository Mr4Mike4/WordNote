package com.core.bjstudio.wordnote.Core.Service.ServiceImpl;

import com.core.bjstudio.wordnote.Core.Annontation.AnnotationImpl.CascadeDeleteHandler;
import com.core.bjstudio.wordnote.Core.Model.Word;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Core.Service.WordService;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.Util.Exception.UpdateDataException;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-23.
 */

public class WordServiceImpl implements WordService {
    @Override
    public RealmResults<Word> getAllEntity() {
        RealmResults<Word> words = RealmSingleton.getInstance().getRealm().where(Word.class).findAll();
        return words;
    }

    @Override
    public RealmResults<Word> getAllEntityAsync() {
        RealmResults<Word> words = RealmSingleton.getInstance().getRealm().where(Word.class).findAllAsync();
        return words;
    }

    @Override
    public Word getEntity(int id) {
        Word word = RealmSingleton.getInstance().getRealm().where(Word.class).equalTo("id", id).findFirst();
        return word;
    }

    @Override
    public Word getEntityAsync(int id) {
        Word word = RealmSingleton.getInstance().getRealm().where(Word.class).equalTo("id", id).findFirstAsync();
        return word;
    }

    @Override
    public void addEntity(Word word) throws AddDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealm(word);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new AddDataException(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Word word) throws UpdateDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealmOrUpdate(word);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new UpdateDataException(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) throws DeleteDataException, NoDataException {
        final RealmResults<Word> words = RealmSingleton.getInstance().getRealm().where(Word.class).equalTo("id", id).findAll();
        if(words.isEmpty()) {
            throw new NoDataException("Data is not exist - id: "+id);
        } else {
            try {
                RealmSingleton.getInstance().<Word>cascdeDelete(words);
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        words.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteAllEntity() throws DeleteDataException, NoDataException {
        final RealmResults<Word> words = RealmSingleton.getInstance().getRealm().where(Word.class).findAll();
        if(words.isEmpty()) {
            throw new NoDataException("Data is not exist");
        } else {
            try {
                RealmSingleton.getInstance().<Word>cascdeDelete(words);
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        words.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }
}
