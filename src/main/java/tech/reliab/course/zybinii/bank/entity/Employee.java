package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String position;
    private Bank bank;
    private boolean worksRemotely;
    private BankOffice bankOffice;
    private boolean canIssueCredits;
    private double salary;

    // Конструктор
    public Employee(String fullName, LocalDate birthDate, String position, Bank bank,
                    boolean worksRemotely, BankOffice bankOffice, boolean canIssueCredits, double salary) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.position = position;
        this.bank = bank;
        this.worksRemotely = worksRemotely;
        this.bankOffice = bankOffice;
        this.canIssueCredits = canIssueCredits;
        this.salary = salary;
    }
}
