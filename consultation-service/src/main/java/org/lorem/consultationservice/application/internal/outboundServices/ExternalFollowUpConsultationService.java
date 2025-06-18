package org.lorem.consultationservice.application.internal.outboundServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.followup.interfaces.acl.FollowUpContextFacade;

@Service
public class ExternalFollowUpConsultationService {

    private final FollowUpContextFacade followUpContextFacade;

    public ExternalFollowUpConsultationService(FollowUpContextFacade followUpContextFacade) {
        this.followUpContextFacade = followUpContextFacade;
    }

    public void createNotification(
            String title,
            String description,
            Long clientId,
            Long consultationId
    ){
        followUpContextFacade.createNotification(
                title,
                description,
                clientId,
                consultationId
        );
    }
}
