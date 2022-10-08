package tn.sharing.asserterproject.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateResource {


    @GetMapping("/hello")

    public String hello(){
        return "hello";
    }

}
