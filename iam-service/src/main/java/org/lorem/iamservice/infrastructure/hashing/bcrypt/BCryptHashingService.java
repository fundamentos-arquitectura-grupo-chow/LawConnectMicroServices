package org.lorem.iamservice.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.lorem.iamservice.application.internal.outboundservices.hashing.HashingService;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
