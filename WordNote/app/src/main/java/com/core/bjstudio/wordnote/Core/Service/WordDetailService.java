package com.core.bjstudio.wordnote.Core.Service;

import com.core.bjstudio.wordnote.Core.Model.WordDetail;
import com.core.bjstudio.wordnote.Core.Service.EntityService;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;

/**
 * This interface has some specific method for WordDetail Model.
 * Created by new_e on 2018-08-23.
 */

public interface WordDetailService extends EntityService<WordDetail> {

    public WordDetail getEntity(String word);
    public WordDetail getEntityAsync(String word);
    public void deleteEntity(String word) throws DeleteDataException,NoDataException;

}
