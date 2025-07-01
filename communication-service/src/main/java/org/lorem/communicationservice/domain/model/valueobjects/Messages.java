package org.lorem.communicationservice.domain.model.valueobjects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import org.lorem.communicationservice.domain.model.entities.MessageItem;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
public class Messages {

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageItem> messages;

    public Messages() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(MessageItem messageItem) {
        this.messages.add(messageItem);
    }

    public void removeMessage(MessageItem messageItem) {
        this.messages.remove(messageItem);
    }

    public void markAsRead(MessageItem messageItem) {
        messageItem.markAsRead();
    }
}
