package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.communicationservice.interfaces.rest.resources.VideoCallResource;
import org.lorem.consultation.interfaces.rest.resources.ConsultationResource;

public class VideoCallResourceFromEntityAssembler {
    public static VideoCallResource toResourceFromEntity(VideoCall entity, ConsultationResource consultationResource){
        return new VideoCallResource(
                entity.getId(),
                consultationResource,
                entity.getDescription(),
                entity.getStatus().toString()
        );
    }
}
