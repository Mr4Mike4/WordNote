package com.core.bjstudio.wordnote.Core.Service.ServiceImpl;

import com.core.bjstudio.wordnote.Core.Annontation.AnnotationImpl.CascadeDeleteHandler;
import com.core.bjstudio.wordnote.Core.Model.Meaning;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Core.Service.MeaningService;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.Util.Exception.UpdateDataException;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-23.
 */

public class MeaningServiceImpl implements MeaningService {

    @Override
    public RealmResults<Meaning> getAllEntityAsync() {
        RealmResults<Meaning> meanings = RealmSingleton.getInstance().getRealm().where(Meaning.class).findAllAsync();
        return meanings;
    }

    @Override
    public RealmResults<Meaning> getAllEntity() {
        RealmResults<Meaning> meanings = RealmSingleton.getInstance().getRealm().where(Meaning.class).findAll();
        return meanings;
    }

    @Override
    public Meaning getEntity(int id) {
        Meaning meaning = RealmSingleton.getInstance().getRealm().where(Meaning.class).equalTo("id", id).findFirst();
        return meaning;
    }

    @Override
    public Meaning getEntityAsync(int id) {
        Meaning meaning = RealmSingleton.getInstance().getRealm().where(Meaning.class).equalTo("id", id).findFirstAsync();
        return meaning;
    }

    @Override
    public Meaning getEntity(String s_meaning) {
        Meaning meaning = RealmSingleton.getInstance().getRealm().where(Meaning.class).equalTo("meaning", s_meaning).findFirst();
        return meaning;
    }

    @Override
    public Meaning getEntityAsync(String s_meaning) {
        Meaning meaning = RealmSingleton.getInstance().getRealm().where(Meaning.class).equalTo("meaning", s_meaning).findFirstAsync();
        return meaning;
    }

    @Override
    public void addEntity(Meaning meaning) throws AddDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealm(meaning);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new AddDataException(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Meaning meaning) throws UpdateDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealmOrUpdate(meaning);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new UpdateDataException(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) throws DeleteDataException, NoDataException {
        final RealmResults<Meaning> meanings = RealmSingleton.getInstance().getRealm().where(Meaning.class).equalTo("id", id).findAll();
        if(meanings.isEmpty()) {
            throw new NoDataException("Data is not exist - id: "+id);
        } else {
            try {
                RealmSingleton.getInstance().<Meaning>cascdeDelete(meanings);
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        meanings.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteAllEntity() throws DeleteDataException, NoDataException {
        final RealmResults<Meaning> meanings = RealmSingleton.getInstance().getRealm().where(Meaning.class).findAll();
        if(meanings.isEmpty()) {
            throw new NoDataException("Data is not exist");
        } else {
            try {
                RealmSingleton.getInstance().<Meaning>cascdeDelete(meanings);
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        meanings.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteEntity(String meaning) throws DeleteDataException, NoDataException {
        final RealmResults<Meaning> meanings = RealmSingleton.getInstance().getRealm().where(Meaning.class).equalTo("meaning", meaning).findAll();
        if(meanings.isEmpty()) {
            throw new NoDataException("Data is not exist - meaning: "+meaning);
        } else {
            try {
                RealmSingleton.getInstance().<Meaning>cascdeDelete(meanings);
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        meanings.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }
}
