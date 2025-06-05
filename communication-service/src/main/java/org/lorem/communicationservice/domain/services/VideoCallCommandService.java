package org.lorem.communicationservice.domain.services;

import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.domain.model.commands.CreateVideoCallCommand;

import java.util.Optional;

public interface VideoCallCommandService {
    Optional<VideoCall> handle(CreateVideoCallCommand command);
}
