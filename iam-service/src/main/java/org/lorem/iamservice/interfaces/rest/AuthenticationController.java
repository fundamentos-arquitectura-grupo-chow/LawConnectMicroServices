package org.lorem.iamservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.lorem.iamservice.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.lorem.iamservice.domain.services.UserCommandService;
import org.lorem.iamservice.interfaces.rest.resources.AuthenticatedUserResource;
import org.lorem.iamservice.interfaces.rest.resources.SignInResource;
import org.lorem.iamservice.interfaces.rest.resources.SignUpResource;
import org.lorem.iamservice.interfaces.rest.resources.UserResource;
import org.lorem.iamservice.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import org.lorem.iamservice.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import org.lorem.iamservice.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import org.lorem.iamservice.interfaces.rest.transform.UserResourceFromEntityAssembler;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final BearerTokenService bearerTokenService;

    public AuthenticationController(UserCommandService userCommandService, BearerTokenService bearerTokenService) {
        this.userCommandService = userCommandService;
        this.bearerTokenService = bearerTokenService;
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (token != null && token.startsWith("Bearer ") && bearerTokenService.validateToken(token.substring(7))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);

    }
}
