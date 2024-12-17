package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;

@Data
public class PaymentAccount {
    private Long id;
    private Long userId;
    private String bankName;
    private double balance = 0.0;

    public PaymentAccount(Long userId, String bankName, double balance) {
        this.userId = userId;
        this.bankName = bankName;
        this.balance = balance;
    }
}