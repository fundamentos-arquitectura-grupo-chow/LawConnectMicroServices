package org.lorem.legalcaseservice.domain.model.commands;

public record CreateLegalCaseCommand(
    String title,
    String description,
    Long consultationId
) {
}
