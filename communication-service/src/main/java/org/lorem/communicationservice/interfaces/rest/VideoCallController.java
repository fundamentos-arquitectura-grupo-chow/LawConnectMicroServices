package org.lorem.communicationservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.lorem.communicationservice.domain.model.queries.GetAllVideoCallsByConsultationId;
import org.lorem.communicationservice.domain.services.VideoCallCommandService;
import org.lorem.communicationservice.domain.services.VideoCallQueryService;
import org.lorem.communicationservice.interfaces.rest.resources.CreateVideoCallResource;
import org.lorem.communicationservice.interfaces.rest.resources.VideoCallResource;
import org.lorem.communicationservice.interfaces.rest.transform.CreateVideoCallCommandFromResourceAssembler;
import org.lorem.communicationservice.interfaces.rest.transform.VideoCallResourceFromEntityAssembler;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/video_call", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Video Call", description = "Video Call Endpoints")
public class VideoCallController {

    private final VideoCallCommandService videoCallCommandService;
    private final VideoCallQueryService videoCallQueryService;

    public VideoCallController(VideoCallCommandService videoCallCommandService, VideoCallQueryService videoCallQueryService) {
        this.videoCallCommandService = videoCallCommandService;
        this.videoCallQueryService = videoCallQueryService;
    }


    @PostMapping
    public ResponseEntity<VideoCallResource> createVideoCall(@RequestBody CreateVideoCallResource resource) {
        var createVideoCallCommand = CreateVideoCallCommandFromResourceAssembler.toCommandFromResource(resource);
        var videoCall = videoCallCommandService.handle(createVideoCallCommand);
        if (videoCall.isEmpty()) return ResponseEntity.badRequest().build();
        var videoCallResource = VideoCallResourceFromEntityAssembler.toResourceFromEntity(videoCall.get());
        return new ResponseEntity<>(videoCallResource, HttpStatus.CREATED);
    }

    @GetMapping("/{consultationId}")
    public ResponseEntity<List<VideoCallResource>> getVideoCallByConsultationId(@PathVariable Long consultationId) {
        var videoCall = videoCallQueryService.handle(new GetAllVideoCallsByConsultationId(consultationId));
        if (videoCall.isEmpty()) return ResponseEntity.notFound().build();
        var videoCallResource = videoCall.stream()
                .map(VideoCallResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(videoCallResource);
    }

}
