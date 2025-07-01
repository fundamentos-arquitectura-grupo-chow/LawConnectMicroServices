package org.lorem.legalcaseservice.interfaces.rest.transform;

import org.lorem.legalcaseservice.domain.model.entities.DocumentsItem;
import org.lorem.legalcaseservice.interfaces.rest.resources.DocumentsItemResource;

public class DocumentsItemResourceFromEntityAssembler {
    public static DocumentsItemResource toEntityFromResource(DocumentsItem entity){
        return new DocumentsItemResource(
            entity.getId(),
            entity.getTitle(),
            entity.getType().name(),
            entity.getDescription(),
            entity.getLegalCase().getId(),
            entity.getStatus().name()
        );
    }
}
