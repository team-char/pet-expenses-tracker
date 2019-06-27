package com.levibrooke.petexpensestracker.Controller;
import com.levibrooke.petexpensestracker.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        return "create-expense";
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

    // GET: get list of expenses
    @GetMapping("/all-expenses")
    public String getAllExpensePage(Principal p, Model m){
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("expenses", user.getExpenses());
        m.addAttribute("hasExpenses", !user.expenses.isEmpty());
        HomeController.isUserLoggedIn(p, m);
        return "all-expenses";
    }

    @GetMapping("/edit-expense/{id}")
    public String editExpenseForm(@PathVariable Long id, Principal p, Model m) {
        Expense expense = expenseRepository.findById(id).get();
        m.addAttribute("expense", expense);
        HomeController.isUserLoggedIn(p, m);

        List<String> categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Supplies/Toy");
        categories.add("Grooming");
        categories.add("Daycare");
        categories.add("Pet Date");
        categories.add("Miscellaneous");
        m.addAttribute("categories", categories);

        return "edit-expense";
    }

    // PUT: update an expense
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public RedirectView editExpense(@PathVariable Long id, @RequestParam String expenseDate, @RequestParam String description, @RequestParam Double amount, @RequestParam String categoryName, Principal p, Model m) {

        Expense expense = expenseRepository.findById(id).get();
        expense.setDescription(description);
        expense.setCategoryName(categoryName);
        expense.setAmount(amount);
        expense.setUpdatedAt(new Date());

        try {
            expense.setExpenseDate(new SimpleDateFormat("MM/dd/yyyy").parse(expenseDate));
        } catch (ParseException e) {
            System.out.println(e);
        }

        expenseRepository.save(expense);

        return new RedirectView("/all-expenses");
    }

    // DELETE: delete an expense
    @PostMapping("/expenses/{id}")
    public String deleteExpense(@PathVariable Long id) {
        System.out.println(id);
        expenseRepository.deleteById(id);
        return "redirect:/success";
    }

    // GET: success page
    @GetMapping("/success")
    public String getSuccess(Principal p, Model m) {
        HomeController.isUserLoggedIn(p, m);
        return "edit-success";
    }
}
