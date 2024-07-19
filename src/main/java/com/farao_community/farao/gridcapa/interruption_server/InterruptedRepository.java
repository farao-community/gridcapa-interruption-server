/*
 * Copyright (c) 2024, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.farao_community.farao.gridcapa.interruption_server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
@Repository
public interface InterruptedRepository extends JpaRepository<InterruptedRun, UUID> {

    @Modifying
    @Query("DELETE FROM InterruptedRun run WHERE run.interruptionDate < :dateTimeReference")
    void deleteOlderThanNbDays(@Param("dateTimeReference")OffsetDateTime dateTimeReference);
}
