package com.core.bjstudio.wordnote.Core.Model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by new_e on 2018-08-19.
 */

public class Example extends RealmObject {
    @PrimaryKey
    private int id;

    private String example;
    private Date createDate;

    public Example() {}

    public Example(int id) {
        this.id = id;
        this.example = "";
        this.createDate = new Date();
    }

    public Example(int id, String example) {
        this.id = id;
        this.example = example;
        this.createDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
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

        Example example1 = (Example) o;

        return getExample() != null ? getExample().equals(example1.getExample()) : example1.getExample() == null;

    }

    @Override
    public int hashCode() {
        return getExample() != null ? getExample().hashCode() : 0;
    }
}
