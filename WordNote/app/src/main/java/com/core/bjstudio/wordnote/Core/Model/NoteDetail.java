package com.core.bjstudio.wordnote.Core.Model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by new_e on 2018-08-19.
 */

public class NoteDetail extends RealmObject {
    @PrimaryKey
    @Required
    private int id;
    @Required
    private String name;
    private Date createDate;

    private RealmList<Word> words;

    @Ignore
    private final static String DEFAULT_NAME = "Default";

    public NoteDetail(int id) {
        this.id = id;
        this.name = this.DEFAULT_NAME;
        this.createDate = new Date();
    }

    public NoteDetail(int id, String name) {
        this.id = id;
        this.name = name;
        this.createDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public RealmList<Word> getWords() {
        return words;
    }

    public void setWords(RealmList<Word> words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteDetail that = (NoteDetail) o;

        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
