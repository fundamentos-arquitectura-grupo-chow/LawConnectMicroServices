
package org.lorem.profilesservice.application.internal.outboundServices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
// import upc.LoremIpsum.lawconnectplatform.iam.interfaces.acl.IamContextFacade;

@Service
public class ExternalIAMProfileService {

   // private final IamContextFacade iamContextFacade;

    public ExternalIAMProfileService(
           // @Lazy IamContextFacade iamContextFacade
    ) {
        // this.iamContextFacade = iamContextFacade;
    }

    public Long getUserIdByUsername(String username) {
        // return iamContextFacade.fetchUserIdByUsername(username);
    }

}

