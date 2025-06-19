package org.lorem.consultationservice.domain.model.commands;

public record CreatePaymentByConsultationIdCommand(
        Long consultationId,
        Double amount,
        Integer currency
)
{
}
