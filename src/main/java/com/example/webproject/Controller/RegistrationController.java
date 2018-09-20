package com.example.webproject.Controller;


import com.example.webproject.domain.Role;
import com.example.webproject.domain.User;
import com.example.webproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration (){
        return "registration";
    }
    @PostMapping("/registration")
    public String AddUser(User user, Map<String,Object> model ){

        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            model.put ("message","User Exists !");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }


}
