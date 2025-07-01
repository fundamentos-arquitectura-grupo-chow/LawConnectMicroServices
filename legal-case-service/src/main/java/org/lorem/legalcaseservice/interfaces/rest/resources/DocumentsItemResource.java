package org.lorem.legalcaseservice.interfaces.rest.resources;

public record DocumentsItemResource (
        Long id,
        String title,
        String type,
        String description,
        Long legalCaseId,
        String status
) {
}
