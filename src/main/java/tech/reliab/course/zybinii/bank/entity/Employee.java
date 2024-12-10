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
}