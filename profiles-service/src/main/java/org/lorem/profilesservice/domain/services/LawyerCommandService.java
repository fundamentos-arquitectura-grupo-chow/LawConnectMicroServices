package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.aggregates.Lawyer;
import org.lorem.profilesservice.domain.model.commands.AddLawyerPricesCommand;
import org.lorem.profilesservice.domain.model.commands.AddLawyerTypeCommand;
import org.lorem.profilesservice.domain.model.commands.CreateLawyerCommand;

import java.util.Optional;

public interface LawyerCommandService {
    Optional<Lawyer> handle(CreateLawyerCommand command);
    void handle(AddLawyerPricesCommand command);
    void handle(AddLawyerTypeCommand command);
}
