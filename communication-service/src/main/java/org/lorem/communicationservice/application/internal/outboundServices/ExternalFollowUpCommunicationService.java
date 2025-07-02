package org.lorem.communicationservice.application.internal.outboundServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.followup.interfaces.acl.FollowUpContextFacade;

@Service
public class ExternalFollowUpCommunicationService {

    private final FollowUpContextFacade followUpContextFacade;

    public ExternalFollowUpCommunicationService(FollowUpContextFacade followUpContextFacade) {
        this.followUpContextFacade = followUpContextFacade;
    }

    public void createNotification(
            String title,
            String description,
            Long consultationId
    ) {
        followUpContextFacade.createNotification(
                        title,
                        description,
                        consultationId
        );
    }
}
