package org.lorem.consultationservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.lorem.consultationservice.domain.model.queries.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.lorem.consultationservice.domain.model.commands.ApproveConsultationCommand;
import org.lorem.consultationservice.domain.model.commands.CreatePaymentByConsultationIdCommand;
import org.lorem.consultationservice.domain.model.commands.DeleteConsultationCommand;
import org.lorem.consultationservice.domain.model.commands.RejectConsultationCommand;
import org.lorem.consultationservice.domain.services.ConsultationCommandService;
import org.lorem.consultationservice.domain.services.ConsultationQueryService;
import org.lorem.consultationservice.interfaces.rest.resources.AddPaymentResource;
import org.lorem.consultationservice.interfaces.rest.resources.ConsultationResource;
import org.lorem.consultationservice.interfaces.rest.resources.CreateConsultationResource;
import org.lorem.consultationservice.interfaces.rest.transform.ConsultationResourceFromEntityAssembler;
import org.lorem.consultationservice.interfaces.rest.transform.CreateConsultationCommandFromResourceAssembler;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/consultations")
@Tag(name = "Consultations", description = "Consultation Management Endpoints")
public class ConsultationController {

    private final ConsultationCommandService consultationCommandService;
    private final ConsultationQueryService consultationQueryService;

    public ConsultationController(
            ConsultationCommandService consultationCommandService,
            ConsultationQueryService consultationQueryService
    ) {
        this.consultationCommandService = consultationCommandService;
        this.consultationQueryService = consultationQueryService;
    }

    @PostMapping
    public ResponseEntity<ConsultationResource> createConsultation(@RequestBody CreateConsultationResource resource) {
        var createConsultationCommand = CreateConsultationCommandFromResourceAssembler.toCommandFromResource(resource);
        var consultationId = consultationCommandService.handle(createConsultationCommand);

        if (consultationId == 0L) return ResponseEntity.badRequest().build();
        var getConsultationByIdQuery = new GetConsultationByIdQuery(consultationId);
        var consultation = consultationQueryService.handle(getConsultationByIdQuery);

        if (consultation.isEmpty()) return ResponseEntity.badRequest().build();

        var consultationResource = ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation.get()
        );
        return new ResponseEntity<>(consultationResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConsultationResource>> getAllConsultations() {
        var consultations = consultationQueryService.handle(new GetAllConsultationsQuery());

        var consultationResources = consultations.stream()
                .map(ConsultationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(consultationResources);
    }

    @GetMapping("/{consultationId}")
    public ResponseEntity<ConsultationResource> getConsultation(@PathVariable Long consultationId) {
        var getConsultationByIdQuery = new GetConsultationByIdQuery(consultationId);
        var consultation = consultationQueryService.handle(getConsultationByIdQuery);

        if (consultation.isEmpty()) return ResponseEntity.notFound().build();

        var consultationResource = ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation.get()
        );
        return ResponseEntity.ok(consultationResource);
    }

    @GetMapping("/lawyer/{lawyerId}")
    public ResponseEntity<List<ConsultationResource>> getAllConsultationsByLawyerId(@PathVariable Long lawyerId) {
        var consultations = consultationQueryService.handle(new GetAllConsultationsByLawyerIdQuery(lawyerId));

        var consultationResources = consultations.stream()
                .map(ConsultationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(consultationResources);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ConsultationResource>> getAllConsultationsByClientId(@PathVariable Long clientId) {
        var consultations = consultationQueryService.handle(new GetAllConsultationsByClientIdQuery(clientId));

        var consultationResources = consultations.stream()
                .map(ConsultationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(consultationResources);
    }

    @GetMapping("/client/{clientId}/lawyer/{lawyerId}")
    public ResponseEntity<List<ConsultationResource>> getAllConsultationsByLawyerIdAndClientId(@PathVariable Long clientId, @PathVariable Long lawyerId) {
        var consultations = consultationQueryService.handle(new GetAllConsultationsByClientIdAndLawyerIdQuery(clientId, lawyerId));

        var consultationResources = consultations.stream()
                .map(ConsultationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(consultationResources);
    }

    @DeleteMapping("/{consultationId}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long consultationId) {
        consultationCommandService.handle(new DeleteConsultationCommand(consultationId));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{consultationId}/payments")
    public ResponseEntity<Void> addPaymentToConsultation(@PathVariable Long consultationId, @RequestBody AddPaymentResource resource) {
        consultationCommandService.handle(new CreatePaymentByConsultationIdCommand(consultationId, resource.amount(), resource.currency()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{consultationId}/approve")
    public ResponseEntity<Void> approveConsultation(@PathVariable Long consultationId) {
        consultationCommandService.handle(new ApproveConsultationCommand(consultationId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{consultationId}/decline")
    public ResponseEntity<Void> declineConsultation(@PathVariable Long consultationId) {
        consultationCommandService.handle(new RejectConsultationCommand(consultationId));
        return ResponseEntity.ok().build();
    }
}