/*
 * Copyright (c) 2024, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.farao_community.farao.gridcapa.interruption_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
@EnableScheduling
@Service
public class DatabasePurgeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabasePurgeService.class);

    @Value("${interruption-server.purge-task-events.nb-days}")
    private String nbDays;

    private final InterruptedRepository interruptionRepository;

    public DatabasePurgeService(InterruptedRepository interruptionRepository) {
        this.interruptionRepository = interruptionRepository;
    }

    @Scheduled(cron = "${interruption-server.purge-task-events.cron}")
    @Transactional
    public void scheduledDatabasePurge() {
        OffsetDateTime dateTimeNow = OffsetDateTime.now();
        OffsetDateTime dateTimeReference = dateTimeNow.minusDays(Long.parseLong(nbDays));
        interruptionRepository.deleteOlderThanNbDays(dateTimeReference);
        LOGGER.debug("Interruption events that are more than {} days old have been deleted from database ", nbDays);
    }
}
