package tn.sharing.asserterproject.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import tn.sharing.asserterproject.entities.AppUser;
import tn.sharing.asserterproject.events.RegistrationCompleteEvent;
import tn.sharing.asserterproject.services.userService.UserService;


import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // generate verification token for user
        AppUser user = event.getUser();

        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(user, token);


        //send verification link via mail
        String url = event.getApplicationUrl() + "/auth/verifyRegistration?token=" + token;

        log.info("click the url to verify your account {}", url);



    }
}
