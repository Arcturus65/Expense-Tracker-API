package com.example.expensetrackerapi;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Expense {
    private double amount;
    private String recipient;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDate date;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Expense() {
    }

    public Expense(double amount, String recipient, Category category) {
        this.amount = amount;
        this.recipient = recipient;
        this.category = category;
        this.date = LocalDate.now();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
