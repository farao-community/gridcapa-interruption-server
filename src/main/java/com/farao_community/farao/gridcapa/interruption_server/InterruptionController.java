/*
 * Copyright (c) 2024, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.farao_community.farao.gridcapa.interruption_server;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @author Marc Schwitzguebel {@literal <marc.schwitzguebel at rte-france.com>}
 */
@Controller
@RequestMapping
public class InterruptionController {

    private final InterruptService interruptService;

    public InterruptionController(InterruptService interruptService) {
        this.interruptService = interruptService;
    }

    @PutMapping(value = "/interrupt/{taskId}")
    public ResponseEntity<Boolean> addNewRunInTaskHistory(@PathVariable String taskId, @RequestParam String runId) {
        return ResponseEntity.ok(interruptService.addInterruptedRun(UUID.fromString(runId), UUID.fromString(taskId)));
    }

    @GetMapping(value = "/interrupted/{runId}")
    public ResponseEntity<Boolean> isInterruptionAsked(@PathVariable String runId) {
        return ResponseEntity.ok(interruptService.exitsByRunId(UUID.fromString(runId)));
    }

}
