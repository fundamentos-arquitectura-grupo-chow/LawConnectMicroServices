package org.lorem.communicationservice.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.commands.AddMessageByChatRoomIdCommand;
import org.lorem.communicationservice.domain.model.valueobjects.SenderType;
import org.lorem.shared.domain.model.entities.AuditableModel;

@Setter
@Getter
@Entity
public class MessageItem extends AuditableModel {

    @Column(nullable = false)
    private String content;

    private boolean isRead;

    private SenderType senderType;

    @ManyToOne
    @JoinColumn(name = "chatRoom_id")
    @JsonIgnore
    private ChatRoom chatRoom;

    public MessageItem(AddMessageByChatRoomIdCommand command, ChatRoom chatRoom) {
        this.content = command.message();
        this.isRead = false;
        this.chatRoom = chatRoom;
        this.senderType = SenderType.fromId(command.sender());
    }

    public MessageItem() {

    }

    public void markAsRead() {
        this.isRead = true;
    }

}
