package org.lorem.profilesservice.domain.model.commands;

public record AddLawyerPricesCommand(
        Long lawyerId,
        double price
) {
}
