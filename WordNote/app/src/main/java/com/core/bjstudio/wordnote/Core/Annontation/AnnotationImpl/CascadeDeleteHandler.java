package com.core.bjstudio.wordnote.Core.Annontation.AnnotationImpl;

import com.core.bjstudio.wordnote.Core.Annontation.CascadeDelete;
import com.core.bjstudio.wordnote.Core.Service.DBHelper.RealmSingleton;
import com.core.bjstudio.wordnote.Util.Exception.CustomLog;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.SimpleTypeSafeMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Stack;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

public class CascadeDeleteHandler {

    private Stack<Object> deleteList;

    public CascadeDeleteHandler() {
        this.deleteList = new Stack<>();
    }

    public <T extends RealmObject> boolean cascadeDelete(T realmObject) {
        boolean isSuccess = Boolean.TRUE;
        try {
            this.<T>classifyInstance(realmObject);
            this.deleteAll(deleteList);
        } catch (Exception e) {
            CustomLog.error("Fail to cascade delete");
            isSuccess = Boolean.FALSE;
        }

        return isSuccess;
    }

    private void deleteAll(Stack<Object> deleteList) {
        while(!deleteList.empty()) {
            Object object = deleteList.pop();
            try {
                if(object instanceof RealmObject) {
                    RealmObject realmObject = (RealmObject) object;
                    this.deleteRealmObject(realmObject);
                } else if(object instanceof RealmList) {
                    RealmList realmList = (RealmList) object;
                    this.deleteRealmList(realmList);
                } else {
                    CustomLog.error("Object is not RealmModel.");
                }
            } catch (DeleteDataException e) {
                CustomLog.error("Fail to delete data. - "+e.getMessage());
            }
        }
    }

    private void deleteRealmList(final RealmList realmList) throws DeleteDataException {
        try {
            RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmList.deleteAllFromRealm();
                }
            });
        } catch (Exception e) {
            throw new DeleteDataException(e.getMessage());
        }
    }

    private void deleteRealmObject(final RealmObject realmObject) throws DeleteDataException {
        try {
            RealmSingleton.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmObject.deleteFromRealm();
                }
            });
        } catch (Exception e) {
            throw new DeleteDataException(e.getMessage());
        }
    }

    private <T extends RealmObject> void classifyInstance(T realmObject) {
        String fieldName = "";
        Type type = null;
        Field[] fields = realmObject.getClass().getSuperclass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(CascadeDelete.class)) {
                fieldName = field.getName();
                type = field.getGenericType();
                if (type instanceof ParameterizedType) {
                    RealmList childs = this.getChildObjects(realmObject, fieldName);
                    this.deleteList.push(childs);
                    this.classifyDeeper(childs);
                } else {
                    fieldName = field.getName();
                    RealmObject child = this.getChildObject(realmObject, fieldName);
                    this.deleteList.push(child);
                }
            }
        }
    }

    private void classifyDeeper(RealmList childs) {
        if(childs.iterator().hasNext() && this.hasCascadeDeleteElement((RealmObject) childs.first())) {
            while(childs.iterator().hasNext()) {
                RealmObject object = (RealmObject) childs.iterator().next();
                this.classifyInstance(object);
            }
        }
    }

    private boolean hasCascadeDeleteElement(RealmObject object) {
        boolean isExist = false;

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CascadeDelete.class)) {
                isExist = true;
                break;
            }
        }

        return isExist;
    }

    private RealmObject getChildObject(RealmObject realmObject, String fieldName) {
        RealmObject child = null;
        String methodName = "get"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        try {
            Method m = realmObject.getClass().getMethod(methodName);
            child = (RealmObject) m.invoke(realmObject);
        } catch (NoSuchMethodException e) {
            CustomLog.info("There is no method. Method name - "+methodName);
        } catch (IllegalAccessException e) {
            CustomLog.info("The error was occurred from getChildObject(RealmObject realmObject, String fieldName). - "+e.getMessage());
        } catch (InvocationTargetException e) {
            CustomLog.info("The error was occurred from getChildObject(RealmObject realmObject, String fieldName). - "+e.getMessage());
        }

        return child;
    }

    private RealmList getChildObjects(RealmObject realmObject, String fieldName) {
        RealmList childList = null;
        String methodName = "get"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        try {
            Method m = realmObject.getClass().getMethod(methodName);
            childList = (RealmList) m.invoke(realmObject);
        } catch (NoSuchMethodException e) {
            CustomLog.info("There is no method. Method name - "+methodName);
        } catch (IllegalAccessException e) {
            CustomLog.info("The error was occurred from getChildObject(RealmObject realmObject, String fieldName). - "+e.getMessage());
        } catch (InvocationTargetException e) {
            CustomLog.info("The error was occurred from getChildObject(RealmObject realmObject, String fieldName). - "+e.getMessage());
        }

        return childList;
    }
}
