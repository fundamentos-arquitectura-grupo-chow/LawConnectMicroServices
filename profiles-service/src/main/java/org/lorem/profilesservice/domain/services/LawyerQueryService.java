package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.aggregates.Lawyer;
import org.lorem.profilesservice.domain.model.queries.*;
import org.lorem.profilesservice.domain.model.valueobjects.LawyerType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LawyerQueryService {
    List<Lawyer> handle(GetAllLawyersQuery query);
    Optional<Lawyer> handle(GetLawyerByIdQuery query);
    Set<LawyerType> handle(GetLawyerTypeByIdQuery query);
    Optional<Long> handle(GetLawyerIdByEmailQuery query);
}
