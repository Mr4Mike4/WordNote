package com.core.bjstudio.wordnote.Core.Service.DBHelper;

import com.core.bjstudio.wordnote.Core.Annontation.AnnotationImpl.CascadeDeleteHandler;
import com.core.bjstudio.wordnote.Core.Annontation.AnnotationImpl.CascadeDeleteHandler2;
import com.core.bjstudio.wordnote.Util.Exception.CustomLog;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by new_e on 2018-08-25.
 */

public class RealmSingleton {
    private final Realm realm;

    private RealmSingleton(){
        realm = Realm.getDefaultInstance();
    }

    public static RealmSingleton getInstance(){
        return SingletonLazyHolder.INSTANCE;
    }

    private static class SingletonLazyHolder {
        private static final RealmSingleton INSTANCE = new RealmSingleton();
    }

    public Realm getRealm() {
        return realm;
    }

    public <T extends RealmObject> int getAutoIncrementIndex(Class<T> t) {
        int nextIndex = 1;
        Number maxIndex = RealmSingleton.getInstance().getRealm().where(t).max("id");
        if(maxIndex != null) {
            nextIndex = maxIndex.intValue()+1;
        }
        return nextIndex;
    }

    public <T extends RealmObject> boolean cascdeDelete(RealmResults<T> realmResults) {
        boolean isSuccess = Boolean.TRUE;

        CascadeDeleteHandler deleteHandler = new CascadeDeleteHandler();
        Iterator<T> iterator  = realmResults.iterator();

        while(iterator.hasNext()) {
            if(!deleteHandler.<T>cascadeDelete(iterator.next())) {
                CustomLog.error("Fail to cascade delete");
                isSuccess = Boolean.FALSE;
                break;
            }
        }

        return isSuccess;
    }
}
