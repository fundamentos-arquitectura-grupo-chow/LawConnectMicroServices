package org.lorem.communicationservice.application.internal.commandservices;

import org.lorem.communicationservice.infrastructure.grpc.ConsultationGrpcClient;
import org.lorem.communicationservice.infrastructure.grpc.FollowUpGrpcClient;
import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.domain.model.commands.CreateVideoCallCommand;
import org.lorem.communicationservice.domain.services.VideoCallCommandService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.VideoCallRepository;

import java.util.Optional;

@Service
public class VideoCallCommandServiceImpl implements VideoCallCommandService {

    private final VideoCallRepository videoCallRepository;
    private final FollowUpGrpcClient followUpGrpcClient;
    private final ConsultationGrpcClient consultationGrpcClient;

    public VideoCallCommandServiceImpl(VideoCallRepository videoCallRepository, FollowUpGrpcClient followUpGrpcClient, ConsultationGrpcClient consultationGrpcClient) {
        this.videoCallRepository = videoCallRepository;
        this.followUpGrpcClient = followUpGrpcClient;
        this.consultationGrpcClient = consultationGrpcClient;
    }

    @Override
    public Optional<VideoCall> handle(CreateVideoCallCommand command) {

        var VideoCall = new VideoCall(command);

        videoCallRepository.save(VideoCall);

        followUpGrpcClient.createNotification(
                "Video Call created",
                command.description(),
                consultationGrpcClient.getClientIdByConsultationId(command.consultationId()),
                command.consultationId()
        );

        return Optional.of(VideoCall);
    }
}
