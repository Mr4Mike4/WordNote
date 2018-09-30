package com.core.bjstudio.wordnote.Core.Model;

import com.core.bjstudio.wordnote.Core.Annontation.CascadeDelete;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by new_e on 2018-08-19.
 */

public class Word extends RealmObject {
    @PrimaryKey
    private int id;

    @CascadeDelete
    private WordDetail wordDetail;

    public Word() {
    }

    public Word(int id, WordDetail wordDetail) {
        this.id = id;
        this.wordDetail = wordDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WordDetail getWordDetail() {
        return wordDetail;
    }

    public void setWordDetail(WordDetail wordDetail) {
        this.wordDetail = wordDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return getId() == word.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }
}
