package com.levibrooke.petexpensestracker.Controller;

import com.levibrooke.petexpensestracker.Model.AppUser;
import com.levibrooke.petexpensestracker.Model.AppUserRepository;
import com.levibrooke.petexpensestracker.Model.Category;
import com.levibrooke.petexpensestracker.Model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    // GET: sign up page
    @GetMapping("/sign-up")
    public String getSignUp() {
        return "sign-up";
    }

    // POST: send new user to database
    @PostMapping("/sign-up")
    public RedirectView createUser(String username, String password, String petName, String petType) {

        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password), petName, petType);
        appUserRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    // GET: login page
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
