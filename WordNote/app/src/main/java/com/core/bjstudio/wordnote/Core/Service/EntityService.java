package com.core.bjstudio.wordnote.Core.Service;

import com.core.bjstudio.wordnote.Util.Exception.AddDataException;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;
import com.core.bjstudio.wordnote.Util.Exception.UpdateDataException;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * This interface has only basic CRUD for Model.
 * Created by new_e on 2018-08-25.
 */

/**
 * Todo List
 * 1. Support Sorted function.
 *
 *
 */

public interface EntityService<T extends RealmObject> {
    public RealmResults<T> getAllEntity();
    public RealmResults<T> getAllEntityAsync();
    public T getEntity(int id);
    public T getEntityAsync(int id);
    public void addEntity(T t) throws AddDataException;
    public void updateEntity(T t) throws UpdateDataException;
    public void deleteEntity(int id) throws DeleteDataException,NoDataException;
}
