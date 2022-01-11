package org.factoriaf5.legacygames.repositories;

import java.util.List;

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
