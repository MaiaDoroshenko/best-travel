package com.example.besttravel.service.abstractService;

public interface SimpleCRUDService<RQ, RS, ID> {
    RS create(RQ request);

    RS read(ID id);

    void delete(ID id);
}
