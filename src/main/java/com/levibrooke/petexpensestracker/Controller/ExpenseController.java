package com.levibrooke.petexpensestracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseController {
    @GetMapping("/create-expense")
    public String getExpenseForm() {
        return "expense-form";
    }
}


