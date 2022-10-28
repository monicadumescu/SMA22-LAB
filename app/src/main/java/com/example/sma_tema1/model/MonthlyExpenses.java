package com.example.sma_tema1.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class MonthlyExpenses {
    public  String month;
    private  float income;
    private  float expenses;

    public  MonthlyExpenses()
    {

    }

    public MonthlyExpenses(String month, float income, float expenses)
    {
        this.month = month;
        this.income = income;
        this.expenses = expenses;
    }

    public String getMonth() {
        return month;
    }

    public float getIncome() {
        return income;
    }

    public float getExpenses() {
        return expenses;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("month", month);
        result.put("income", income);
        result.put("expenses", expenses);

        return result;
    }
}
