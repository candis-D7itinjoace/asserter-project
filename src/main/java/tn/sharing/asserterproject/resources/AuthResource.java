package tn.sharing.asserterproject.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tn.sharing.asserterproject.config.utilities.JwtUtils;
import tn.sharing.asserterproject.dto.AddOwnerModel;
import tn.sharing.asserterproject.dto.AuthenticationModel;
import tn.sharing.asserterproject.dto.AuthenticationResponse;
import tn.sharing.asserterproject.entities.AppUser;
import tn.sharing.asserterproject.entities.VerificationToken;
import tn.sharing.asserterproject.events.RegistrationCompleteEvent;
import tn.sharing.asserterproject.services.enterpriseService.EnterpriseService;
import tn.sharing.asserterproject.services.securityServices.UserDetailsServiceImpl;
import tn.sharing.asserterproject.services.userService.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthResource {



    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/hello")
    public String hello(){
        return "hello world!";
    }

    @PostMapping("/signup")
    public String addOwner(@RequestBody @Valid AddOwnerModel ownerModel, final HttpServletRequest httpServletRequest) {

        AppUser owner = userService.addOwner(ownerModel);

        if(owner != null) {
            publisher.publishEvent(new RegistrationCompleteEvent(owner, applicationUrl(httpServletRequest)));
            return "success";
        }

        return "invalid request";
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationModel authenticationModel) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationModel.getEmail(), authenticationModel.getPassword()));
        }catch (BadCredentialsException exception){
            throw new Exception("bad credentials !", exception);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationModel.getEmail());
        final String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));

    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {

        String result = userService.validateVerificationTokenForUser(token);

        return result;
    }

    @GetMapping("/resendVerification")
    public String resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request) {
        VerificationToken token = userService.generateNewTokenForUser(oldToken);
        AppUser user = token.getUser();
        resendVerificationEmail(applicationUrl(request), token, user);


        return "verification link sent !!";
    }

    private void resendVerificationEmail(String applicationUrl, VerificationToken token, AppUser user) {
        String url = applicationUrl + "/verifyRegistration?token=" + token.getToken();
        log.info("click the link to verify your account {}", url);
    }

    private String applicationUrl(HttpServletRequest httpServletRequest) {
        return "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath();
    }


    @GetMapping("/getuserdetails/{email}")
    public AppUser getUserDetails(@PathVariable("email") String email){
        try {
            return userService.findUserByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/userExists/{userId}")
    public boolean checkIfUserExists(@PathVariable("userId") Long userId) {
        return userService.checkIfUserExists(userId);
    }

    @GetMapping("/enterpriseExists/{enterpriseId}")
    public boolean checkIfEnterpriseExists(@PathVariable("enterpriseId") Long enterpriseId) {
        return enterpriseService.checkIfEnterpriseExists(enterpriseId);
    }



}