package com.levibrooke.petexpensestracker.Controller;

import com.levibrooke.petexpensestracker.Model.AppUser;
import com.levibrooke.petexpensestracker.Model.AppUserRepository;
import com.levibrooke.petexpensestracker.Model.Expense;
import com.levibrooke.petexpensestracker.Model.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model m) {
        isUserLoggedIn(p, m);
        return "home";
    }

    @GetMapping("/about-us")
    public String getAboutUs(Principal p, Model m) {
        isUserLoggedIn(p, m);
        return "about-us";
    }

    @GetMapping("/dashboard")
    public String getDashboardPage(Principal p, Model m){
        isUserLoggedIn(p, m);
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("userExpenses", user.getExpenses());
        return "dashboard";
    }

    @PostMapping("/dashboard/{month}")
    public String postDashboardPage(@RequestParam String month, Principal p, Model m){
        isUserLoggedIn(p, m);

        AppUser user = appUserRepository.findByUsername(p.getName());
        List<Expense> sortByMonthExpenseList = new ArrayList<>();
        List<Double> amountList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        for(Expense e : user.getExpenses()){
            String strDate = dateFormat.format(e.getExpenseDate());
            if(month.equals(strDate)){
                sortByMonthExpenseList.add(e);
                amountList.add(e.getAmount());
            }
        }

        HashMap<String, Double> hashMap = new HashMap<>();
        for(Expense e : user.getExpenses()){
            if(!hashMap.containsKey(e.getCategoryName())){
                hashMap.put(e.getCategoryName(),  e.getAmount());
            } else{
                hashMap.put(e.getCategoryName(), hashMap.get(e.getCategoryName()) + e.getAmount());
            }
        }

        for(String e : hashMap.keySet()){
            System.out.println(hashMap.get(e));
        }

        m.addAttribute("totalCategoryAmount", hashMap);
        m.addAttribute("totalAmount", amountList);
        m.addAttribute("userExpenses", user.getExpenses());
        m.addAttribute("sortByMonthList", sortByMonthExpenseList);

        return "dashboard";
    }

    public static void isUserLoggedIn(Principal p, Model m){
        if(p != null){
            m.addAttribute("loggedInName", p.getName());
        }
        else {
            m.addAttribute("loggedInName", false);
        }
    }
}


