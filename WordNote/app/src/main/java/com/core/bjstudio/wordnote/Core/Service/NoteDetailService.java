package com.core.bjstudio.wordnote.Core.Service;

import com.core.bjstudio.wordnote.Core.Model.NoteDetail;
import com.core.bjstudio.wordnote.Core.Service.EntityService;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;

/**
 * This interface has some specific method for NoteDetail Model.
 * Created by new_e on 2018-08-23.
 */

public interface NoteDetailService extends EntityService<NoteDetail> {

    public NoteDetail getEntity(String name);
    public NoteDetail getEntityAsync(String name);
    public void deleteEntity(String name) throws DeleteDataException,NoDataException;
}
