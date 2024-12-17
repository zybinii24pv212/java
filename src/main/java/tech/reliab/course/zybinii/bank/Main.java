package tech.reliab.course.zybinii.bank;

import tech.reliab.course.zybinii.bank.entity.*;
import tech.reliab.course.zybinii.bank.service.impl.*;

import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        BankService bankService = new BankService();
        BankOfficeService bankOfficeService = new BankOfficeService(bankService);
        AtmService atmService = new AtmService(bankService);
        EmployeeService employeeService = new EmployeeService(bankService);
        UserService userService = new UserService(bankService);
        CreditAccountService creditAccountService = new CreditAccountService();
        PaymentAccountService paymentAccountService = new PaymentAccountService();

        Random random = new Random();
        // Инициализация 5 банков
        List<Bank> banks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Bank bank = new Bank("Bank#" + (i + 1));
            bankService.create(bank);
            banks.add(bank);
        }

        // Инициализация по 3 банковских офиса для каждого банка
        List<BankOffice> bankOffices = new ArrayList<>();
        for (Bank bank : banks) {
            for (int i = 0; i < 3; i++) {
                BankOffice bankOffice = new BankOffice(
                        "BankOffice#" + (i + 1),
                        "Adress#" + (i + 1),
                        true,
                        true,
                        true,
                        true,
                        true,
                        (i + 1) * 100000,
                        (i + 1) * 1000,
                        bank
                );
                bankOfficeService.create(bankOffice);
                bankOffices.add(bankOffice);
            }
        }

        List<Employee> employees = new ArrayList<>();
        List<BankAtm> bankAtms = new ArrayList<>();
        for (BankOffice bankOffice : bankOffices) {
            List<Employee> currentOfficeEmployees = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Employee employee = new Employee(
                        "Employee#" + (i + 1),
                        LocalDate.now(),
                        "Position#" + (i + 1),
                        bankOffice.getBank(),
                        false,
                        bankOffice,
                        true,
                        (i + 1) * 1000
                );
                employeeService.create(employee);
                employees.add(employee);
                currentOfficeEmployees.add(employee);
            }
            for (int i = 0; i < 3; i++) {
                Employee serviceEmployee = currentOfficeEmployees.get(random.nextInt(currentOfficeEmployees.size()));
                BankAtm bankAtm = new BankAtm(
                        "BankAtm#" + (i + 1),
                        bankOffice.getBank(),
                        bankOffice,
                        serviceEmployee, // serviceEmployee
                        true,
                        true,
                        (i + 1) * 1000,
                        (i + 1) * 100
                );
                atmService.create(bankAtm);
                bankAtms.add(bankAtm);
            }
        }

        List<User> users = new ArrayList<>();
        List<PaymentAccount> paymentAccounts = new ArrayList<>();
        List<CreditAccount> creditAccounts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Bank randomBank = banks.get(random.nextInt(banks.size()));
            User user = new User(
                    "User#" + (i + 1),
                    LocalDate.now(),
                    "Position#" + (i + 1),
                    Collections.singletonList(randomBank),

                    null,
                    null
            );
            userService.create(user);
            users.add(user);
            for (int j = 0; j < 2; j++) {
                PaymentAccount paymentAccount = new PaymentAccount(
                        user.getId(),
                        randomBank.getName(),
                        (j + 1) * 1000
                );
                paymentAccountService.create(paymentAccount);
                paymentAccounts.add(paymentAccount);

                CreditAccount creditAccount = new CreditAccount(
                        user.getId(),
                        randomBank.getName(),
                        LocalDate.now(),
                        random.nextInt(1, 24),
                        random.nextInt(1000, 1000000),
                        randomBank.getInterestRate(),
                        null,
                        paymentAccount,
                        randomBank
                );
                creditAccountService.create(creditAccount);
                creditAccounts.add(creditAccount);
            }
        }


        for (Bank bank : banks) {
            System.out.println("Данные по банку " + bank.getName());
            System.out.println("Банковские офисы:");
            for (BankOffice office : bankOffices) {
                if (office.getBank().equals(bank)) {
                    System.out.println(office);
                }
            }
            System.out.println("Банкоматы:");
            for (BankAtm atm : bankAtms) {
                if (atm.getBank().equals(bank)) {
                    System.out.println(atm);
                }
            }
            System.out.println("Сотрудники:");
            for (Employee employee : employees) {
                if (employee.getBank().equals(bank)) {
                    System.out.println(employee);
                }
            }
            System.out.println("Клиенты:");
            for (User user : users) {
                if (user.getBanks().contains(bank)) {
                    System.out.println(user);
                    System.out.println("Счета клиента:");
                    for (PaymentAccount account : paymentAccounts) {
                        if (account.getUserId().equals(user.getId())) {
                            System.out.println(account);
                        }
                    }
                    for (CreditAccount account : creditAccounts) {
                        if (account.getUserId().equals(user.getId())) {
                            System.out.println(account);
                        }
                    }
                }
            }
        }
    }
}
