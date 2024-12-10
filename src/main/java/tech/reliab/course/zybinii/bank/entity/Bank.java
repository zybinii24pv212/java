package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;


import java.util.concurrent.ThreadLocalRandom;

@Data
public class Bank {
    private Long id;
    private String name;

    private int officeCount = 0;
    private int atmCount = 0;
    private int employeeCount = 0;
    private int clientCount = 0;

    private int rating;
    private double totalMoney;
    private double interestRate;

    public Bank(String name) {
        this.name = name;
        this.rating = generateRandomRating();
        this.totalMoney = generateRandomMoney();
        this.interestRate = calculateInterestRate(this.rating);
    }

    private int generateRandomRating() {
        return ThreadLocalRandom.current().nextInt(1, 101);
    }

    private double generateRandomMoney() {
        return ThreadLocalRandom.current().nextDouble(0, 1_000_001);
    }

    private double calculateInterestRate(int rating) {
        return ThreadLocalRandom.current().nextDouble(0, 21 / (rating / 20.0));
    }

    public void addOffice() {
        this.officeCount++;
    }

    public void addAtm() {
        this.atmCount++;
    }

    public void addEmployee() {
        this.employeeCount++;
    }

    public void addClient() {
        this.clientCount++;
    }
}
