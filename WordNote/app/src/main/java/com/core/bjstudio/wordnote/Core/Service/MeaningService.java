package com.core.bjstudio.wordnote.Core.Service;

import com.core.bjstudio.wordnote.Core.Model.Meaning;
import com.core.bjstudio.wordnote.Core.Service.EntityService;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;

/**
 * This interface has some specific method for Meaning Model.
 *
 * Created by new_e on 2018-08-23.
 */

public interface MeaningService extends EntityService<Meaning> {

    public Meaning getEntity(String meaning);
    public Meaning getEntityAsync(String meaning);
    public void deleteEntity(String meaning) throws DeleteDataException,NoDataException;

}
