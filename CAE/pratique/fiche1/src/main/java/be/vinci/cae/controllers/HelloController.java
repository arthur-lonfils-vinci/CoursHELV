package be.vinci.cae.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public Map<String,String> hello(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            name = "World";
        }
        Map<String,String> data = new HashMap<>();
        data.put("message", "Hello " + name);
        return data;
    }
}