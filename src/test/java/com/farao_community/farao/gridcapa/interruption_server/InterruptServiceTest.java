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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
@SpringBootTest
class InterruptServiceTest {

    @Autowired
    InterruptService interruptService;

    @Test
    void addInterruptedRun() {
        UUID runId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        assertFalse(interruptService.exitsByRunId(runId));
        assertTrue(interruptService.addInterruptedRun(runId, taskId));
        assertTrue(interruptService.exitsByRunId(runId));
        assertFalse(interruptService.addInterruptedRun(runId, taskId));
    }

}
