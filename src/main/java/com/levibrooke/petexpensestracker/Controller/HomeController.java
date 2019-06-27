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
        m.addAttribute("monthList", getMonths());
        m.addAttribute("totalCategoryAmount", getTotalAmountsByCategory(user.getExpenses()));
        m.addAttribute("totalAmount", getTotalAmountExpenses(user.getExpenses()));
        m.addAttribute("userExpenses", user.getExpenses());
        return "dashboard";
    }

    @PostMapping("/dashboard/{month}")
    public String postDashboardPage(@RequestParam String month, Principal p, Model m){
        isUserLoggedIn(p, m);
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("totalAmountByMonth", getTotalAmountByMonthExpense(user.getExpenses(), month));
        m.addAttribute("currentMonth", getMonths().getOrDefault(month, ""));
        m.addAttribute("monthList", getMonths());
        m.addAttribute("totalCategoryAmount", getTotalAmountsByCategory(user.getExpenses()));
        m.addAttribute("totalAmount", getTotalAmountExpenses(user.getExpenses()));
        m.addAttribute("userExpenses", user.getExpenses());
        m.addAttribute("sortByMonthList", getSortedByMonthExpense(user.getExpenses(), month));
        return "dashboard";
    }

    //Helper Methods
    private List getTotalAmountExpenses(List<Expense> expenseList){
        List<Double> totalAmountList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        for(Expense e : expenseList){
                totalAmountList.add(e.getAmount());
        }
        return totalAmountList;
    }
    private Double getTotalAmountByMonthExpense(List<Expense> expenseList, String month) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        double sum = 0.0;
        for(Expense e : expenseList){
            String strDate = dateFormat.format(e.getExpenseDate());
            if(month.equals(strDate)){
                sum += e.getAmount();
            }
        }
        return sum;
    }

    private List getSortedByMonthExpense(List<Expense> expenseList, String month) {
        List<Expense> sortByMonthExpenseList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        for(Expense e : expenseList){
            String strDate = dateFormat.format(e.getExpenseDate());
            if(month.equals(strDate)){
                sortByMonthExpenseList.add(e);
            }
        }
        return sortByMonthExpenseList;
    }
    private HashMap<String, Double> getTotalAmountsByCategory(List<Expense> expenseList){
        HashMap<String, Double> hashMap = new HashMap<>();
        for(Expense e : expenseList){
            if(!hashMap.containsKey(e.getCategoryName())){
                hashMap.put(e.getCategoryName(),  e.getAmount());
            }
            else{
                hashMap.put(e.getCategoryName(), hashMap.get(e.getCategoryName()) + e.getAmount());
            }
        }
        return hashMap;
    }

    private HashMap<String, String> getMonths(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("01", "January");
        hashMap.put("02", "February");
        hashMap.put("03", "March");
        hashMap.put("04", "April");
        hashMap.put("05", "May");
        hashMap.put("06", "June");
        hashMap.put("07", "July");
        hashMap.put("08", "August");
        hashMap.put("09", "September");
        hashMap.put("10", "October");
        hashMap.put("11", "November");
        hashMap.put("12", "December");
        return hashMap;
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


