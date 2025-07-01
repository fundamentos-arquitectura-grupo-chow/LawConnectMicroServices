package org.lorem.feeingservice.domain.model.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDto {
    private Long id;
    private Long clientId;

    public static ConsultationDto fromConsultation(ConsultationDto consultation) {
        return new ConsultationDto(
                consultation.getId(),
                consultation.getClientId()
        );
    }
}
