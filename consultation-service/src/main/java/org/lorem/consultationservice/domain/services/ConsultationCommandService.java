package org.lorem.consultationservice.domain.services;

import org.lorem.consultationservice.domain.model.commands.*;

public interface ConsultationCommandService {
    Long handle(CreateConsultationCommand command);
    void handle(DeleteConsultationCommand command);
    void handle(CompletePaymentByIdCommand command);
    void handle(ApproveConsultationCommand command);
    void handle(RejectConsultationCommand command);
    void handle(CreatePaymentByConsultationIdCommand command);
}
