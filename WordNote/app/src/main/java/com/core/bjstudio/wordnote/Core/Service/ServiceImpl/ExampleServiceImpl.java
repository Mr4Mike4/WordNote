package com.core.bjstudio.wordnote.Core.Service.ServiceImpl;

import com.core.bjstudio.wordnote.Core.Model.Example;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Core.Service.ExampleService;
import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.Util.Exception.UpdateDataException;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-23.
 */

public class ExampleServiceImpl implements ExampleService {

    @Override
    public RealmResults<Example> getAllEntity() {
        RealmResults<Example> examples = RealmSingleton.getInstance().getRealm().where(Example.class).findAll();
        return examples;
    }

    @Override
    public Example getEntity(int id) {
        Example example = RealmSingleton.getInstance().getRealm().where(Example.class).equalTo("id", id).findFirst();
        return example;
    }

    @Override
    public Example getEntity(String s_example) {
        Example example = RealmSingleton.getInstance().getRealm().where(Example.class).equalTo("example", s_example).findFirst();
        return example;
    }

    @Override
    public Example getEntityAsync(String s_example) {
        Example example = RealmSingleton.getInstance().getRealm().where(Example.class).equalTo("example", s_example).findFirstAsync();
        return example;
    }

    @Override
    public void addEntity(Example example) throws AddDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealm(example);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new AddDataException(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Example example) throws UpdateDataException {
        RealmSingleton.getInstance().getRealm().beginTransaction();
        try {
            RealmSingleton.getInstance().getRealm().copyToRealmOrUpdate(example);
            RealmSingleton.getInstance().getRealm().commitTransaction();
        } catch (Exception e) {
            RealmSingleton.getInstance().getRealm().cancelTransaction();
            throw new UpdateDataException(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) throws DeleteDataException,NoDataException {
        final RealmResults<Example> examples = RealmSingleton.getInstance().getRealm().where(Example.class).equalTo("id", id).findAll();
        if(examples.isEmpty()) {
            throw new NoDataException("Data is not exist - id: "+id);
        } else {
            try {
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        examples.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteExample(String example) throws DeleteDataException,NoDataException {
        final RealmResults<Example> examples = RealmSingleton.getInstance().getRealm().where(Example.class).equalTo("example", example).findAll();
        if(examples.isEmpty()) {
            throw new NoDataException("Data is not exist - example: "+example);
        } else {
            try {
                RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        examples.deleteAllFromRealm();
                    }
                });
            } catch (Exception e) {
                throw new DeleteDataException(e.getMessage());
            }
        }
    }

    @Override
    public RealmResults<Example> getAllEntityAsync() {
        RealmResults<Example> examples = RealmSingleton.getInstance().getRealm().where(Example.class).findAllAsync();
        return examples;
    }

    @Override
    public Example getEntityAsync(int id)    {
        Example example = RealmSingleton.getInstance().getRealm().where(Example.class).equalTo("id", id).findFirstAsync();
        return example;
    }
}
