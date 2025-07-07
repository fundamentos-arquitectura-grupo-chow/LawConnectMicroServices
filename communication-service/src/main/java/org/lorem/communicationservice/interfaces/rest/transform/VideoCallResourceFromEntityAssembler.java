package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.interfaces.rest.resources.VideoCallResource;

public class VideoCallResourceFromEntityAssembler {
    public static VideoCallResource toResourceFromEntity(VideoCall entity){
        return new VideoCallResource(
                entity.getId(),
                entity.getConsultationId(),
                entity.getDescription(),
                entity.getStatus().toString()
        );
    }
}
