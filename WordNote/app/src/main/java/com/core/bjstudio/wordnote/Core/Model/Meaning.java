package com.core.bjstudio.wordnote.Core.Model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by new_e on 2018-08-19.
 */

public class Meaning extends RealmObject {
    @PrimaryKey
    private int id;

    @Required
    private String meaning;
    private Date createDate;

    public Meaning() {
    }

    public Meaning(int id, String meaning) {
        this.id = id;
        this.meaning = meaning;
        this.createDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meaning meaning1 = (Meaning) o;

        return getMeaning() != null ? getMeaning().equals(meaning1.getMeaning()) : meaning1.getMeaning() == null;

    }

    @Override
    public int hashCode() {
        return getMeaning() != null ? getMeaning().hashCode() : 0;
    }
}
