/*
 * Copyright (c) 2024, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.farao_community.farao.gridcapa.interruption_server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
@SpringBootTest
class DatabasePurgeServiceTest {

    @Autowired
    private InterruptedRepository interruptedRepository;

    @Autowired
    private DatabasePurgeService databasePurgeService;

    @Test
    void scheduledDatabasePurgeTest() {
        interruptedRepository.deleteAll();

        OffsetDateTime offsetDateTimeNow = OffsetDateTime.now(ZoneId.of("UTC")).withNano(0);
        OffsetDateTime offsetDateTimeEvent11 = offsetDateTimeNow.minusDays(10);
        OffsetDateTime offsetDateTimeEvent12 = offsetDateTimeNow.minusDays(9);
        OffsetDateTime offsetDateTimeEvent21 = offsetDateTimeNow.minusDays(8);
        OffsetDateTime offsetDateTimeEvent22 = offsetDateTimeNow.minusDays(6);

        InterruptedRun run11 = new InterruptedRun(UUID.randomUUID(), UUID.randomUUID());
        run11.setInterruptionDate(offsetDateTimeEvent11);
        interruptedRepository.save(run11);
        InterruptedRun run12 = new InterruptedRun(UUID.randomUUID(), UUID.randomUUID());
        run12.setInterruptionDate(offsetDateTimeEvent12);
        interruptedRepository.save(run12);
        InterruptedRun run21 = new InterruptedRun(UUID.randomUUID(), UUID.randomUUID());
        run21.setInterruptionDate(offsetDateTimeEvent21);
        interruptedRepository.save(run21);
        InterruptedRun run22 = new InterruptedRun(UUID.randomUUID(), UUID.randomUUID());
        run22.setInterruptionDate(offsetDateTimeEvent22);
        interruptedRepository.save(run22);

        assertEquals(4, interruptedRepository.count());
        assertTrue(interruptedRepository.existsById(run11.getRunId()));
        assertTrue(interruptedRepository.existsById(run12.getRunId()));
        assertTrue(interruptedRepository.existsById(run21.getRunId()));
        assertTrue(interruptedRepository.existsById(run22.getRunId()));
        databasePurgeService.scheduledDatabasePurge();
        assertEquals(1, interruptedRepository.count());
        assertFalse(interruptedRepository.existsById(run11.getRunId()));
        assertFalse(interruptedRepository.existsById(run12.getRunId()));
        assertFalse(interruptedRepository.existsById(run21.getRunId()));
        assertTrue(interruptedRepository.existsById(run22.getRunId()));
    }
}
