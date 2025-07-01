package org.lorem.communicationservice.domain.services;

import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.domain.model.queries.GetAllVideoCallsByConsultationId;

import java.util.List;

public interface VideoCallQueryService {
    List<VideoCall> handle(GetAllVideoCallsByConsultationId query);
}
