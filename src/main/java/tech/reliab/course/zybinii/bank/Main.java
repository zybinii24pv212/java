package tech.reliab.course.zybinii.bank;

import tech.reliab.course.zybinii.bank.entity.*;
import tech.reliab.course.zybinii.bank.service.impl.*;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Создаем сервисы
        BankService bankService = new BankService();
        AtmService atmService = new AtmService();
        BankOfficeService bankOfficeService = new BankOfficeService();
        EmployeeService employeeService = new EmployeeService();
        UserService userService = new UserService();
        PaymentAccountService paymentAccountService = new PaymentAccountService();
        CreditAccountService creditAccountService = new CreditAccountService();

        Bank bank = new Bank("Sber");
        bankService.create(bank);

        BankOffice bankOffice = new BankOffice(1L, "SberOffice", "ул. Королёва, 2Г", true, true, true, true, true, 50000, 15000);
        bankOfficeService.create(bankOffice);

        BankAtm atm = new BankAtm(1L, "Sber ATM", bank, bankOffice, null, true, true, 10000, 200);
        atmService.create(atm);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFullName("John Smith");
        employee.setBirthDate(LocalDate.of(1985, 5, 20));
        employee.setPosition("Manager");
        employee.setBank(bank);
        employee.setWorksRemotely(false);
        employee.setBankOffice(bankOffice);
        employee.setCanIssueCredits(true);
        employee.setSalary(3000);
        employeeService.create(employee);


        User user = new User(1L, "Alice Johnson", LocalDate.of(1990, 2, 15), "TechCorp", Arrays.asList(bank), Arrays.asList(), Arrays.asList());
        userService.create(user);

        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setId(1L);
        paymentAccount.setUserId(user.getId());
        paymentAccount.setBankName(bank.getName());
        paymentAccount.setBalance(5000);
        paymentAccountService.create(paymentAccount);

        CreditAccount creditAccount = new CreditAccount(1L, user.getId(), bank.getName(), LocalDate.now(), 12, 10000, 5, employee.getId(), paymentAccount, bank);
        creditAccountService.create(creditAccount);

        System.out.println("Bank: " + bank);
        System.out.println("Bank office: " + bankOffice);
        System.out.println("ATM: " + atm);
        System.out.println("Employee: " + employee);
        System.out.println("User: " + user);
        System.out.println("Payment account: " + paymentAccount);
        System.out.println("Credit account: " + creditAccount);
    }
}
