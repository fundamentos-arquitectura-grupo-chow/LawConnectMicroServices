package org.lorem.consultationservice.interfaces.acl;

import org.springframework.stereotype.Service;
import org.lorem.consultationservice.domain.model.queries.GetConsultationByIdQuery;
import org.lorem.consultationservice.domain.services.ConsultationQueryService;


@Service
public class ConsultationContextFacade {

    private final ConsultationQueryService consultationQueryService;

    public ConsultationContextFacade(ConsultationQueryService consultationQueryService) {
        this.consultationQueryService = consultationQueryService;
    }

    public Long getClientIdById(Long consultationId) {
        var consultation = consultationQueryService.handle(new GetConsultationByIdQuery(consultationId));
        return consultation.get().getClientId();
    }

    public boolean existConsultationById(Long consultationId) {
        return consultationQueryService.handle(new GetConsultationByIdQuery(consultationId)).isPresent();
    }
}
