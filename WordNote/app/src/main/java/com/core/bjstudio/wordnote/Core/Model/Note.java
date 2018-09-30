package com.core.bjstudio.wordnote.Core.Model;

import com.core.bjstudio.wordnote.Core.Annontation.CascadeDelete;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by new_e on 2018-08-19.
 */

public class Note extends RealmObject {
    @PrimaryKey
    private int id;

    @CascadeDelete
    private NoteDetail noteDetail;

    @Required
    private String version;

    public Note() {
    }

    public Note(int id, NoteDetail noteDetail) {
        this.id = id;
        this.noteDetail = noteDetail;
        this.version = "1";
    }

    public Note(int id, NoteDetail noteDetail, String version) {
        this.id = id;
        this.noteDetail = noteDetail;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NoteDetail getNoteDetail() {
        return noteDetail;
    }

    public void setNoteDetail(NoteDetail noteDetail) {
        this.noteDetail = noteDetail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (getId() != note.getId()) return false;
        return getVersion() != null ? getVersion().equals(note.getVersion()) : note.getVersion() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        return result;
    }
}
