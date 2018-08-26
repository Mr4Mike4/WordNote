package com.core.bjstudio.wordnote.Core.Service;

import com.core.bjstudio.wordnote.Core.Model.Example;
import com.core.bjstudio.wordnote.Core.Service.EntityService;
import com.core.bjstudio.wordnote.Util.Exception.DeleteDataException;
import com.core.bjstudio.wordnote.Util.Exception.NoDataException;

/**
 * This interface has some specific method for Example Model.
 *
 * Created by new_e on 2018-08-23.
 */

public interface ExampleService extends EntityService<Example> {

    public Example getEntity(String example);
    public Example getEntityAsync(String example);
    public void deleteExample(String example) throws DeleteDataException,NoDataException;

}
