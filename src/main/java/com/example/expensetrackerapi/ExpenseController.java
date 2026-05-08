package com.example.expensetrackerapi;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @GetMapping("/expenses")
    public List<Expense> getExpenses(@RequestParam(required = false) Double amount, @RequestParam(required = false) Category category) {
        if (amount != null && category != null) {
            return expenseService.findByCategoryAndAmountGreaterThanEqual(category, amount);
        } else if (amount != null && category == null) {
            return expenseService.findByAmountGreaterThanEqual(amount);
        } else if (amount == null && category != null) {
            return expenseService.findByCategory(category);
        } else {
            return expenseService.getExpenses();
        }
    }

    @PostMapping("/expenses")
    public Expense addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return expense;
    }

    @DeleteMapping("/expenses/{id}")
    public String deleteExpense(@PathVariable long id) {
        String delete = expenseService.deleteExpense(id);
        return delete;
    }

    @PutMapping("/expenses/{id}")
    public String updateExpense(@PathVariable long id, @RequestBody Expense updatedExpense) {
        String returnPrompt = expenseService.updateExpense(updatedExpense, id);
        return returnPrompt;
    }

}
