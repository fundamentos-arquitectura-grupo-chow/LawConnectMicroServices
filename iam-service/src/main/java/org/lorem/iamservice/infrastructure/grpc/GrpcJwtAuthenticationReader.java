package org.lorem.iamservice.infrastructure.grpc;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.lorem.iamservice.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.annotation.Nullable;
import java.util.Optional;

@Component
public class GrpcJwtAuthenticationReader implements GrpcAuthenticationReader {

    private static final Metadata.Key<String> AUTHORIZATION_HEADER =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    private final BearerTokenService tokenService;
    private final UserDetailsService userDetailsService;

    public GrpcJwtAuthenticationReader(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    public Optional<Authentication> readAuthentication(Metadata metadata) {
        String header = metadata.get(AUTHORIZATION_HEADER);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (tokenService.validateToken(token)) {
                String username = tokenService.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                return Optional.of(
                        org.lorem.iamservice.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder
                                .build(userDetails, null)
                );
            }
        }
        return Optional.empty();
    }

    @Nullable
    @Override
    public Authentication readAuthentication(ServerCall<?, ?> call, Metadata headers) throws AuthenticationException {
        return readAuthentication(headers).orElse(null);
    }

}
