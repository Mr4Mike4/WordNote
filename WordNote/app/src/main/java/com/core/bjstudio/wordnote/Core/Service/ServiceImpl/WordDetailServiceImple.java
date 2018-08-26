package com.core.bjstudio.wordnote.Core.Service.ServiceImpl;

import com.core.bjstudio.wordnote.Core.Model.WordDetail;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Core.Service.WordDetailService;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.Util.Exception.UpdateDataException;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-23.
 */

public class WordDetailServiceImple implements WordDetailService {

    @Override
    public WordDetail getEntity(int id) {
        WordDetail wordDetail = RealmSingleton.getInstance().getRealm().where(WordDetail.class).equalTo("id", id).findFirst();
        return wordDetail;
    }

    @Override
    public WordDetail getEntityAsync(int id) {
        WordDetail wordDetail = RealmSingleton.getInstance().getRealm().where(WordDetail.class).equalTo("id", id).findFirstAsync();
        return wordDetail;
    }

    @Override
    public WordDetail getEntity(String word) {
        WordDetail wordDetail = RealmSingleton.getInstance().getRealm().where(WordDetail.class).equalTo("word", word).findFirst();
        return wordDetail;
    }

    @Override
    public WordDetail getEntityAsync(String word) {
        WordDetail wordDetail = RealmSingleton.getInstance().getRealm().where(WordDetail.class).equalTo("word", word).findFirstAsync();
        return wordDetail;
    }

    @Override
    public RealmResults<WordDetail> getAllEntity() {
        RealmResults<WordDetail> wordDetails = RealmSingleton.getInstance().getRealm().where(WordDetail.class).findAll();
        return wordDetails;
    }

    @Override
    public RealmResults<WordDetail> getAllEntityAsync() {
        RealmResults<WordDetail> wordDetails = RealmSingleton.getInstance().getRealm().where(WordDetail.class).findAllAsync();
        return wordDetails;
    }

    @Override
    public void addEntity(WordDetail wordDetail) throws AddDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealm(wordDetail);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new AddDataException(e.getMessage());
        }
    }

    @Override
    public void updateEntity(WordDetail wordDetail) throws UpdateDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealmOrUpdate(wordDetail);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new UpdateDataException(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) throws DeleteDataException, NoDataException {
        final RealmResults<WordDetail> wordDetails = RealmSingleton.getInstance().getRealm().where(WordDetail.class).equalTo("id", id).findAll();
        if(wordDetails.isEmpty()) {
            throw new NoDataException("Data is not exist - id: "+id);
        } else {
            try {
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        wordDetails.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteEntity(String word) throws DeleteDataException, NoDataException {
        final RealmResults<WordDetail> wordDetails = RealmSingleton.getInstance().getRealm().where(WordDetail.class).equalTo("word", word).findAll();
        if(wordDetails.isEmpty()) {
            throw new NoDataException("Data is not exist - word: "+word);
        } else {
            try {
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        wordDetails.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }
}
