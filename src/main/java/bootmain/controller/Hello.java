package bootmain.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.*;

@RestController
public class Hello {
    @RequestMapping("/hello")
    public String helloCtrl(){
        return "Hello Spring Boot!";
    }
    @RequestMapping("/param/{id}")
    public String paramHandler(@PathVariable("id") String id,
                               @RequestParam(value="name",required = false) String name,
                               @RequestHeader("User-Agent")String userAgent){
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        System.out.println("User-Agent:"+userAgent);
        return "paramHandler is running!";
    }

}