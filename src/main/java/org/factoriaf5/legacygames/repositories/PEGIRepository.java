package org.factoriaf5.legacygames.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class PEGIRepository {
    public PEGIRepository() {
    }

    public List<PEGI> findAll() {
        return List.of(
                new PEGI("3"),
                new PEGI("7"),
                new PEGI("12"),
                new PEGI("16"),
                new PEGI("18")

        );
    }
}
