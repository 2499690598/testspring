package com.yc.hellostarter_1;


import com.yc.hellostarter.services.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
class Hellostarter1Application {

    public static void main(String[] args) {
        SpringApplication.run(Hellostarter1Application.class, args);
    }


    private IHelloService helloService;

    @GetMapping("/hello")
    public String sayHello(){
        return helloService.say();
    }
}
