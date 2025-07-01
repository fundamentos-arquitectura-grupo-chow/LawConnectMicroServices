package org.lorem.followupservice.domain.model.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDto {
    private Long id;
    private Long clientId;
}