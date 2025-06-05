package org.lorem.communicationservice.application.internal.queryservices;

import org.lorem.communicationservice.application.internal.outboundServices.ExternalConsultationCommunicationService;
import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.domain.model.queries.GetAllVideoCallsByConsultationId;
import org.lorem.communicationservice.domain.services.VideoCallQueryService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.VideoCallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoCallQueryServiceImpl implements VideoCallQueryService {

    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;
    private final VideoCallRepository videoCallRepository;

    public VideoCallQueryServiceImpl(ExternalConsultationCommunicationService externalConsultationCommunicationService, VideoCallRepository videoCallRepository) {
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
        this.videoCallRepository = videoCallRepository;
    }

    @Override
    public List<VideoCall> handle(GetAllVideoCallsByConsultationId query) {
        var consultation = externalConsultationCommunicationService.getConsultationById(query.consultationId());
        return videoCallRepository.findAllByConsultation(consultation.get());
    }
}
