package com.calypzo.bookshop.resources;

import com.calypzo.bookshop.model.User;
import com.calypzo.bookshop.repository.UserRepository;
import com.calypzo.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/registration")
    public String signup(){

        return "registration";
    }
    @RequestMapping("/loginPage")
    public String login(){

        return "login";
    }
    @ResponseBody
    @PostMapping("/sign_in")
    public String signIn(@ModelAttribute("log")User user){
        String email = user.getEmail();
        String pswd = user.getPassword();
        if(userService.login(email,pswd))
           if(user.getRole()=="Seller")
               return "s_home";
           else
               return "b_home";
        else
            return "error";
    }

    @PostMapping("/save")
    public String register(@ModelAttribute("reg")User u){
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userService.addusers(u);
        return "login";
    }

}
