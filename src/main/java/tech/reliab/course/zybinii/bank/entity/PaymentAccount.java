package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;

@Data
public class PaymentAccount {
    private Long id;
    private Long userId;
    private String bankName;
    private double balance = 0.0;
}