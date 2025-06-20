package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.AddLawyerPricesCommand;
import org.lorem.profilesservice.interfaces.rest.resources.AddLawyerPricesResource;

public class AddLawyerPricesCommandFromResourceAssembler {
    public static AddLawyerPricesCommand ToCommandFromResource(AddLawyerPricesResource resource) {
        return new AddLawyerPricesCommand(
                resource.lawyerId(),
                resource.price()
        );
    }
}
