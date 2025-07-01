package org.lorem.legalcaseservice.domain.model.commands;

public record ChangeDocumentStatusCommand(Long documentId, Integer status) {
}
