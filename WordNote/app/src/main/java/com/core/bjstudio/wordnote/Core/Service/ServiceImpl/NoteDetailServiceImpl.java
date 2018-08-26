package com.core.bjstudio.wordnote.Core.Service.ServiceImpl;

import com.core.bjstudio.wordnote.Core.Model.NoteDetail;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Core.Service.NoteDetailService;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.Util.Exception.UpdateDataException;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-23.
 */

public class NoteDetailServiceImpl implements NoteDetailService {

    @Override
    public NoteDetail getEntity(int id) {
        NoteDetail noteDetail = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).equalTo("id", id).findFirst();
        return noteDetail;
    }

    @Override
    public NoteDetail getEntityAsync(int id) {
        NoteDetail noteDetail = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).equalTo("id", id).findFirstAsync();
        return noteDetail;
    }

    @Override
    public NoteDetail getEntity(String name) {
        NoteDetail noteDetail = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).equalTo("name", name).findFirst();
        return noteDetail;
    }

    @Override
    public NoteDetail getEntityAsync(String name) {
        NoteDetail noteDetail = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).equalTo("name", name).findFirstAsync();
        return noteDetail;
    }

    @Override
    public RealmResults<NoteDetail> getAllEntity() {
        RealmResults<NoteDetail> noteDetails = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).findAll();
        return noteDetails;
    }

    @Override
    public RealmResults<NoteDetail> getAllEntityAsync() {
        RealmResults<NoteDetail> noteDetails = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).findAllAsync();
        return noteDetails;
    }

    @Override
    public void addEntity(NoteDetail noteDetail) throws AddDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealm(noteDetail);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new AddDataException(e.getMessage());
        }
    }

    @Override
    public void updateEntity(NoteDetail noteDetail) throws UpdateDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealmOrUpdate(noteDetail);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new UpdateDataException(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) throws DeleteDataException, NoDataException {
        final RealmResults<NoteDetail> noteDetails = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).equalTo("id", id).findAll();
        if(noteDetails.isEmpty()) {
            throw new NoDataException("Data is not exist - id: "+id);
        } else {
            try {
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        noteDetails.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteEntity(String name) throws DeleteDataException, NoDataException {
        final RealmResults<NoteDetail> noteDetails = RealmSingleton.getInstance().getRealm().where(NoteDetail.class).equalTo("name", name).findAll();
        if(noteDetails.isEmpty()) {
            throw new NoDataException("Data is not exist - name: "+name);
        } else {
            try {
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        noteDetails.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }
}
