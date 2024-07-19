package com.farao_community.farao.gridcapa.interruption_server;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
class InterruptedRunTest {

    @Test
    void testGettersAndSetters() {
        OffsetDateTime offsetDateTimeNow = OffsetDateTime.now(ZoneId.of("UTC")).withNano(0);
        UUID taskId = UUID.randomUUID();
        UUID runId = UUID.randomUUID();
        InterruptedRun run = new InterruptedRun();
        run.setInterruptionDate(offsetDateTimeNow);
        run.setTaskId(taskId);
        run.setRunId(runId);
        assertEquals(taskId, run.getTaskId());
        assertEquals(runId, run.getRunId());
        assertEquals(offsetDateTimeNow, run.getInterruptionDate());
    }

    @Test
    void testConstructor() {
        UUID taskId = UUID.randomUUID();
        UUID runId = UUID.randomUUID();
        InterruptedRun run = new InterruptedRun(runId, taskId);
        assertEquals(taskId, run.getTaskId());
        assertEquals(runId, run.getRunId());
        assertNotNull(run.getInterruptionDate());
    }
}
