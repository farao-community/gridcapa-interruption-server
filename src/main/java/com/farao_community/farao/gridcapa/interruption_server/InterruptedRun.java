/*
 * Copyright (c) 2024, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.farao_community.farao.gridcapa.interruption_server;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
@Entity
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NaturalIdCache
public class InterruptedRun {
    @Id
    @Column(name = "run_id")
    private UUID runId;

    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "interruption_date")
    private OffsetDateTime interruptionDate;

    public InterruptedRun(UUID runId, UUID taskId) {
        this.runId = runId;
        this.taskId = taskId;
        this.interruptionDate = OffsetDateTime.now();
    }

    public InterruptedRun() {

    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getRunId() {
        return runId;
    }

    public void setRunId(UUID runId) {
        this.runId = runId;
    }

    public OffsetDateTime getInterruptionDate() {
        return interruptionDate;
    }

    public void setInterruptionDate(OffsetDateTime interruptionDate) {
        this.interruptionDate = interruptionDate;
    }
}
