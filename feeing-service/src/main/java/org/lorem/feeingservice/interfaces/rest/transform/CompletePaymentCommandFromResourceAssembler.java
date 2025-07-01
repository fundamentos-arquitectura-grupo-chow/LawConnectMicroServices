package org.lorem.feeingservice.interfaces.rest.transform;

import org.lorem.feeingservice.domain.model.commands.CompletePaymentCommand;
import org.lorem.feeingservice.interfaces.rest.resources.CompletePaymentResource;

import java.time.LocalDate;

public class CompletePaymentCommandFromResourceAssembler {
    public static CompletePaymentCommand toCommandFromResource(CompletePaymentResource resource, Long paymentId){

        String[] dateParts = resource.expirationDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        System.out.println(year);
        int month = Integer.parseInt(dateParts[1]);
        System.out.println(month);

        LocalDate expirationDateYYYYMM = LocalDate.of(year, month, 1);

        System.out.println(expirationDateYYYYMM);

        return new CompletePaymentCommand(
                paymentId,
                resource.cardNumber(),
                expirationDateYYYYMM,
                resource.cvv()
        );
    }
}
