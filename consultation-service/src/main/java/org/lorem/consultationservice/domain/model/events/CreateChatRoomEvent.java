package org.lorem.consultationservice.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class CreateChatRoomEvent extends ApplicationEvent {

    private final Long consultationId;

    public CreateChatRoomEvent(Object source, Long consultationId) {
        super(source);
        this.consultationId = consultationId;
    }
}
