package com.snehasingh.aicareernavigator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController       //Spring, ye class ek API hai — jab koi browser se request aaye, isko handle karna." Bina isko likhe, Spring is class ko normal class samjhega, API nahi.
public class HelloController {   // class matlab container jisme related code rakhte h

    @GetMapping("/hello")     //Jab koi browser mein localhost:8080/hello type kare, toh niche wala method chalao. /hello ek path hai — tum isko /greet, /test, kuch bhi rakh sakti thi, jo bhi URL mein aana ho.
    public String sayHello() {   // Ye ek method (function) hai jiska naam sayHello hai. Jab bhi /hello pe request aayegi, ye method run hoga aur jo bhi return mein likha hai wahi text browser mein dikh jayega.
        return "Hello Sneha! Your API is working.";
    }
}