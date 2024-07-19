/*
 * Copyright (c) 2024, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.farao_community.farao.gridcapa.interruption_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
@Service
public class InterruptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterruptService.class);
    private final InterruptedRepository interruptRepository;

    public InterruptService(InterruptedRepository interruptRepository) {
        this.interruptRepository = interruptRepository;
    }

    public boolean addInterruptedRun(UUID runId, UUID taskId) {
        LOGGER.info("Trying to add interrupt run {} to task {}", runId, taskId);
        if (!interruptRepository.existsById(runId)) {
            InterruptedRun interruptedRun = new InterruptedRun(runId, taskId);
            interruptRepository.save(interruptedRun);
            LOGGER.info("Added interrupt run {} to task {}", runId, taskId);
            return true;
        } else {
            LOGGER.info("Interrupt run {} to task {} Already exists, will not be added", runId, taskId);
            return false;
        }
    }

    public boolean exitsByRunId(UUID runId) {
        return interruptRepository.existsById(runId);
    }
}
