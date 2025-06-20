package org.lorem.profilesservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.lorem.profilesservice.domain.model.commands.IncrementConsultationsMadeCommand;
import org.lorem.profilesservice.domain.model.commands.IncrementPaidServicesCommand;
import org.lorem.profilesservice.domain.model.queries.GetAllClientsQuery;
import org.lorem.profilesservice.domain.model.queries.GetClientByIdQuery;
import org.lorem.profilesservice.domain.model.queries.GetClientIdByEmailQuery;
import org.lorem.profilesservice.domain.services.ClientCommandService;
import org.lorem.profilesservice.domain.services.ClientQueryService;
import org.lorem.profilesservice.interfaces.rest.resources.ClientResource;
import org.lorem.profilesservice.interfaces.rest.resources.CreateClientResource;
import org.lorem.profilesservice.interfaces.rest.transform.ClientResourceFromEntityAssembler;
import org.lorem.profilesservice.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Clients", description = "Clients Endpoints")
public class ClientController {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public ClientController(ClientCommandService clientCommandService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }

    @PostMapping
    public ResponseEntity<ClientResource> createClient(@RequestBody CreateClientResource resource){
        var createClientCommand = CreateClientCommandFromResourceAssembler.ToCommandFromResource(resource);
        var client = clientCommandService.handle(createClientCommand);
        if(client.isEmpty()) return ResponseEntity.badRequest().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @GetMapping("/Id/{clientId}")
    public ResponseEntity<ClientResource> getClientById(@PathVariable Long clientId){
        var getClientByIdQuery = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(getClientByIdQuery);
        if(client.isEmpty()) return ResponseEntity.notFound().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @GetMapping
    public ResponseEntity<List<ClientResource>> getAllClients(){
        var getAllClientsQuery = new GetAllClientsQuery();
        var clients = clientQueryService.handle(getAllClientsQuery);
        var clientResources = clients.stream()
                .map(ClientResourceFromEntityAssembler::ToResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientResources);
    }

    @PatchMapping("/IncrementConsultation")
    public ResponseEntity<ClientResource> incrementConsultation(@RequestParam Long clientId){
        clientCommandService.handle(new IncrementConsultationsMadeCommand(clientId));
        var getClientByIdQuery = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(getClientByIdQuery);
        if(client.isEmpty()) return ResponseEntity.notFound().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @PatchMapping("/IncrementPaid")
    public ResponseEntity<ClientResource> incrementPaid(@RequestParam Long clientId){
        clientCommandService.handle(new IncrementPaidServicesCommand(clientId));
        var getClientByIdQuery = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(getClientByIdQuery);
        if(client.isEmpty()) return ResponseEntity.notFound().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Long> getClientByEmail(@PathVariable String email){
        var client = clientQueryService.handle(new GetClientIdByEmailQuery(email));
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
