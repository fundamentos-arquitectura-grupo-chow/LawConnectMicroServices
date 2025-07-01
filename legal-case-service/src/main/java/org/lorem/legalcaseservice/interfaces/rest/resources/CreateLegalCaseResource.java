package org.lorem.legalcaseservice.interfaces.rest.resources;

public record CreateLegalCaseResource (
        String title,
        String description,
        Long consultationId
){
}
