package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Data
public class User {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String jobPlace;
    private double monthlyIncome;
    private List<Bank> banks;
    private List<CreditAccount> creditAccounts;
    private List<PaymentAccount> paymentAccounts;
    private int creditRating;

    public User(String fullName, LocalDate birthDate, String jobPlace,
                List<Bank> banks, List<CreditAccount> creditAccounts, List<PaymentAccount> paymentAccounts) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.jobPlace = jobPlace;
        this.banks = banks;
        this.creditAccounts = creditAccounts;
        this.paymentAccounts = paymentAccounts;
        generateRandomIncomeAndRating();
    }

    public void generateRandomIncomeAndRating() {
        Random random = new Random();
        this.monthlyIncome = random.nextInt(1000, 10001);

        this.creditRating = (int) Math.floor(this.monthlyIncome / 1000) * 100 + 100;
    }
}