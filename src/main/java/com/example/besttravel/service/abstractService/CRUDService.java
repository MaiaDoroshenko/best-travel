package com.example.besttravel.service.abstractService;

public interface CRUDService<RQ,RS,Id> {

    RS create (RQ request);
    RS read (Id id);
    RS update (RQ request,Id id);
    void delete(Id id);

}
