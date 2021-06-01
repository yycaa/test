package bootmain.controller;

import bootmain.module.Person;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Hello {
    @RequestMapping("/hello")
    public String helloCtrl(){
        return "Hello Spring Boot!";
    }
    @RequestMapping("/param/{id}")
    @ResponseBody
    public String paramHandler(@PathVariable("id") String id,
                               @RequestParam(value="name",required = false) String name,
                               @RequestHeader("User-Agent")String userAgent){
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        System.out.println("User-Agent:"+userAgent);
        return "paramHandler is running!";
    }
    @RequestMapping("/per")
    @ResponseBody
    public Person perCtrl(){
    Person person=new Person();
    person.setAge(18);
    person.setID("123");
    person.setTele("133333");
    person.setName("机器人");
        return person;
    }
    @RequestMapping("/thy")

    public String thymeleafCtrl(Model model){
        model.addAttribute("msg","Hello Thymeleaf 啊");
        model.addAttribute("link","https://www.baidu.com/");
        return "success";
    }

}