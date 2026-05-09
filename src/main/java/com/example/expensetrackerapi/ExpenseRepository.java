package com.example.expensetrackerapi;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(Category category);
    List<Expense> findByDate(LocalDate date);
    List<Expense> findByCategoryAndAmountGreaterThanEqual(Category category, double amount);
    List<Expense> findByAmountGreaterThanEqual(double amount);
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
}
