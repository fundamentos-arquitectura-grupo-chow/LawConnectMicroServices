package org.lorem.profilesservice.interfaces.rest.resources;

public record AddLawyerPricesResource(
        Long lawyerId,
        double price
) {
}
