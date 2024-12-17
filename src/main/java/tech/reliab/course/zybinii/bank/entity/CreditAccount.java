package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditAccount {
    private Long id;
    private Long userId;
    private String bankName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int creditMonths;
    private double creditAmount;
    private double monthlyPayment;
    private double interestRate;
    private Long issuedByEmployeeId;
    private PaymentAccount paymentAccount;

    public CreditAccount(Long userId, String bankName, LocalDate startDate, int creditMonths,
                         double creditAmount, double interestRate, Long issuedByEmployeeId, PaymentAccount paymentAccount,
                         Bank bank) {
        this.userId = userId;
        this.bankName = bankName;
        this.startDate = startDate;
        this.creditMonths = creditMonths;
        this.creditAmount = creditAmount;
        this.issuedByEmployeeId = issuedByEmployeeId;
        this.paymentAccount = paymentAccount;
        this.endDate = startDate.plusMonths(creditMonths);
        this.interestRate = Math.min(interestRate, bank.getInterestRate());
        this.monthlyPayment = calculateMonthlyPayment(creditAmount, this.interestRate, creditMonths);
    }

    private double calculateMonthlyPayment(double amount, double rate, int months) {
        double monthlyRate = rate / 12 / 100;
        return (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }
}
