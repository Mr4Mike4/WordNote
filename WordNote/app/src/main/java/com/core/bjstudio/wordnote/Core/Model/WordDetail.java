package com.core.bjstudio.wordnote.Core.Model;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by new_e on 2018-08-19.
 */

public class WordDetail extends RealmObject {
    @PrimaryKey
    @Required
    private int id;

    @Required
    private String word;
    private Date createDate;

    @Required
    private RealmList<Meaning> meanings;
    private RealmList<Example> examples;

    public WordDetail(int id, String word, RealmList<Meaning> meanings) {
        this.id = id;
        this.word = word;
        this.meanings = meanings;
        this.examples = new RealmList<Example>();
        this.createDate = new Date();
    }

    public WordDetail(int id, String word, RealmList<Meaning> meanings, RealmList<Example> examples) {
        this.id = id;
        this.word = word;
        this.meanings = meanings;
        this.examples = examples;
        this.createDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public RealmList<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(RealmList<Meaning> meanings) {
        this.meanings = meanings;
    }

    public RealmList<Example> getExamples() {
        return examples;
    }

    public void setExamples(RealmList<Example> examples) {
        this.examples = examples;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordDetail that = (WordDetail) o;

        return getWord() != null ? getWord().equals(that.getWord()) : that.getWord() == null;

    }

    @Override
    public int hashCode() {
        return getWord() != null ? getWord().hashCode() : 0;
    }
}
