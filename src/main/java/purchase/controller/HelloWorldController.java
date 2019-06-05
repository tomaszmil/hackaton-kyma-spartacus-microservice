package purchase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class HelloWorldController {

    @GetMapping
    public String helloWorld() {
        return "Hello world!";
    }
}
