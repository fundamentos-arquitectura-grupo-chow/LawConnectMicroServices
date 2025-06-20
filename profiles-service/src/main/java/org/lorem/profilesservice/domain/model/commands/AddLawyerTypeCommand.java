package org.lorem.profilesservice.domain.model.commands;

public record AddLawyerTypeCommand (
        Long lawyerId,
        Integer lawyerTypeId
) {
}
