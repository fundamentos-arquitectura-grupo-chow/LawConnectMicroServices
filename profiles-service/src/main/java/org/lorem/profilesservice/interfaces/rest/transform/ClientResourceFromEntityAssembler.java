package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.aggregates.Client;
import org.lorem.profilesservice.interfaces.rest.resources.ClientResource;

public class ClientResourceFromEntityAssembler {
    public static ClientResource ToResourceFromEntity(Client entity){
        return new ClientResource(
                entity.getId(),
                entity.getProfile(),
                entity.getConsultationCount(),
                entity.getPaidServices()
        );
    }
}
