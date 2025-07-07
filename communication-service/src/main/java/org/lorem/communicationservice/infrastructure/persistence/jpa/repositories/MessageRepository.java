package org.lorem.communicationservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.entities.MessageItem;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageItem, Long> {
    List<MessageItem> findAllByChatRoom(ChatRoom chatRoom);
}
