package com.example.expensetrackerapi;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private double budget;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }
    public String addExpense(Expense expense) {
        expenseRepository.save(expense);
        return "Expense added successfully";
    }

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }
    public String deleteExpense(long id) {
        String deletePrompt = "Expense not found";
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            deletePrompt = "Expense deleted successfully";
            return deletePrompt;
        }
        return deletePrompt;
    }

    public String updateExpense(Expense updatedExpense, long id) {
        String updatePrompt = "Expense not found";
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            expense.setAmount(updatedExpense.getAmount());
            expense.setRecipient(updatedExpense.getRecipient());
            expense.setCategory(updatedExpense.getCategory());
            expense.setDate(updatedExpense.getDate());
            expenseRepository.save(expense);
            updatePrompt = "Expense updated successfully";
        }
        return updatePrompt;
    }

    public List<Expense> findByCategoryAndAmountGreaterThanEqual(Category category, double amount) {
        List<Expense> categoryList = expenseRepository.findByCategory(category);
        List<Expense> amountList = new ArrayList<>();
        for  (Expense expense : categoryList) {
            if(expense.getAmount() >= amount) {
                amountList.add(expense);
            }
        }
        return amountList;
    }

    public List<Expense> findByCategory(Category category) {
         return expenseRepository.findByCategory(category);
    }

    public List<Expense> findByAmountGreaterThanEqual(double amount) {
        return expenseRepository.findByAmountGreaterThanEqual(amount);
    }

    public String budgetCompare() {
        double amountSpent = 0;
        double budgetDifference;
        for (Expense expense : expenseRepository.findAll()) {
            amountSpent += expense.getAmount();
        }
        if (amountSpent > budget) {
            budgetDifference = amountSpent - budget;
            return "You have an expense total of " +  amountSpent  + "lei. " + "You exceeded your budget by " +  budgetDifference;
        } else {
            budgetDifference = budget - amountSpent;
            return "You have an expense total of " +  amountSpent  + "lei. " + "You remained within budget with " +  budgetDifference + " lei to spare.";
        }
    }

    public List<Expense> expensesReport(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = LocalDate.of(year, month, start.lengthOfMonth());
        return expenseRepository.findByDateBetween(start, end);
    }
}
