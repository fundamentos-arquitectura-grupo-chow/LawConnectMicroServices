package org.lorem.communicationservice.application.internal.commandservices;

import org.lorem.communicationservice.application.internal.outboundServices.ExternalConsultationCommunicationService;
import org.lorem.communicationservice.application.internal.outboundServices.ExternalFollowUpCommunicationService;
import org.lorem.communicationservice.application.internal.outboundServices.ExternalPaymentCommunicationService;
import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.domain.model.commands.CreateVideoCallCommand;
import org.lorem.communicationservice.domain.services.VideoCallCommandService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.VideoCallRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoCallCommandServiceImpl implements VideoCallCommandService {

    private final VideoCallRepository videoCallRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;
    private final ExternalFollowUpCommunicationService externalFollowUpCommunicationService;
    private final ExternalPaymentCommunicationService externalPaymentCommunicationService;

    public VideoCallCommandServiceImpl(VideoCallRepository videoCallRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService, ExternalFollowUpCommunicationService externalFollowUpCommunicationService, ExternalPaymentCommunicationService externalPaymentCommunicationService) {
        this.videoCallRepository = videoCallRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
        this.externalFollowUpCommunicationService = externalFollowUpCommunicationService;
        this.externalPaymentCommunicationService = externalPaymentCommunicationService;
    }

    @Override
    public Optional<VideoCall> handle(CreateVideoCallCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }


        var VideoCall = new VideoCall(command, consultation.get());

        videoCallRepository.save(VideoCall);

        externalFollowUpCommunicationService.createNotification(
                "Video Call created",
                command.description(),
                consultation.get().getClientId(),
                consultation.get().getId()
        );

        return Optional.of(VideoCall);
    }
}
