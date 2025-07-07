package org.lorem.legalcaseservice.domain.model.valueobjects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import org.lorem.legalcaseservice.domain.model.entities.DocumentsItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
public class Documents {

    @OneToMany(mappedBy = "legalCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentsItem> documentsItems;

    public Documents() {
        this.documentsItems = new ArrayList<>();
    }

    public Documents(List<DocumentsItem> documentsItems) {
        this.documentsItems = documentsItems;
    }

    public void addDocumentItem(DocumentsItem item) {
        this.documentsItems.add(item);
    }

    public void removeDocumentItem(DocumentsItem item) {
        this.documentsItems.remove(item);
    }
}
