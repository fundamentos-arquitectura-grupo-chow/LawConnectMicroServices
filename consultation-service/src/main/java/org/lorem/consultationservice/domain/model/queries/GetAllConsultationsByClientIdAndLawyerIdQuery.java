package org.lorem.consultationservice.domain.model.queries;

public record GetAllConsultationsByClientIdAndLawyerIdQuery(
        Long clientId,
        Long lawyerId
) {
}
