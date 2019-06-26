package com.levibrooke.petexpensestracker.Controller;
import com.levibrooke.petexpensestracker.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    // GET: route to expense form
    @GetMapping("/create-expense")
    public String getFormExpense(Principal p, Model m){
        HomeController.isUserLoggedIn(p, m);
        return "/create-expense";
    }

    // POST: route to post one expense
    @PostMapping("/create-expense")
    public RedirectView postExpense(@RequestParam String expenseDate, @RequestParam String description, @RequestParam Double amount, @RequestParam String categoryName, Principal p, Model m) throws ParseException {
        AppUser currentUser = (AppUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        Expense newExpense = new Expense(description, amount, expenseDate, categoryName);
        newExpense.appUser = appUserRepository.findById(currentUser.getId()).get();
        expenseRepository.save(newExpense);

        HomeController.isUserLoggedIn(p, m);

        return new RedirectView("/dashboard");
    }

    @GetMapping("/all-expense")
    public String getAllExpensePage(Principal p, Model m){
        List<Expense> expenseList = (List<Expense>) expenseRepository.findAll();
        m.addAttribute(expenseList);
        HomeController.isUserLoggedIn(p, m);
        return "/all-expense";
    }



}
