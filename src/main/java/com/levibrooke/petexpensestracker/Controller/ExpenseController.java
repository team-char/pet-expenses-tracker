package com.levibrooke.petexpensestracker.Controller;

import com.levibrooke.petexpensestracker.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.Table;
import java.security.Principal;
import java.text.ParseException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
public class ExpenseController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    // GET: route to expense form
    @GetMapping("/createExpense")
    public String getFormExpense(Model m){
        //get all categories to add to form this works when we MANUALLY add categories to DB
        List<Category> allCategories = (List<Category>) categoryRepository.findAll();
        m.addAttribute("categoryList", allCategories);
        return "/createExpense";
    }

    //POST: route to post  one expense
    @PostMapping("/createExpense")
    public RedirectView postExpense(@RequestParam String name, @RequestParam Date createdAt,
                                    @RequestParam String description,
                                    @RequestParam Double amount, @RequestParam Long categoryId, Principal p) throws ParseException {
        AppUser currentUser = (AppUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        //still need category


        Expense newExpense = new Expense(name, description, amount, createdAt, categoryId);
        newExpense.appUser = appUserRepository.findById(currentUser.getId()).get();
        expenseRepository.save(newExpense);

        return new RedirectView("/dashboard");
    }

}
