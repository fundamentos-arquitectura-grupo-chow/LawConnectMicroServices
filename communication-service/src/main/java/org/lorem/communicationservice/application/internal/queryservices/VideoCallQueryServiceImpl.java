package org.lorem.communicationservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.domain.model.queries.GetAllVideoCallsByConsultationId;
import org.lorem.communicationservice.domain.services.VideoCallQueryService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.VideoCallRepository;

import java.util.List;

@Service
public class VideoCallQueryServiceImpl implements VideoCallQueryService {

    private final VideoCallRepository videoCallRepository;

    public VideoCallQueryServiceImpl(VideoCallRepository videoCallRepository) {
        this.videoCallRepository = videoCallRepository;
    }

    @Override
    public List<VideoCall> handle(GetAllVideoCallsByConsultationId query) {
        return videoCallRepository.findAllByConsultationId(query.consultationId());
    }
}
