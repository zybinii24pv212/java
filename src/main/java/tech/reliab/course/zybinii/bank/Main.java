package tech.reliab.course.zybinii.bank;

import tech.reliab.course.zybinii.bank.entity.*;
import tech.reliab.course.zybinii.bank.service.impl.*;

import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            BankService bankService = new BankService();
            BankOfficeService bankOfficeService = new BankOfficeService(bankService);
            AtmService atmService = new AtmService(bankService);
            EmployeeService employeeService = new EmployeeService(bankService);
            PaymentAccountService paymentAccountService = new PaymentAccountService();
            UserService userService = new UserService(bankService, paymentAccountService);
            CreditAccountService creditAccountService = new CreditAccountService();

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
                    double moneyInOffice = random.nextDouble(50000, 200000);
                    BankOffice bankOffice = new BankOffice(
                            "BankOffice#" + (i + 1),
                            "Adress#" + (i + 1),
                            true, // isWorking
                            true,
                            true,
                            true,
                            true,
                            moneyInOffice, // moneyInOffice
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
                            random.nextBoolean(), // canIssueCredits
                            random.nextDouble(500, 5000)
                    );
                    employeeService.create(employee);
                    employees.add(employee);
                    currentOfficeEmployees.add(employee);
                }
                for (int i = 0; i < 3; i++) {
                    Employee serviceEmployee = currentOfficeEmployees.get(random.nextInt(currentOfficeEmployees.size()));
                    double atmMoney = random.nextDouble(5000, Math.min(50000, bankOffice.getMoneyInOffice()));
                    BankAtm bankAtm = new BankAtm(
                            "BankAtm#" + (i + 1),
                            bankOffice.getBank(),
                            bankOffice,
                            serviceEmployee,
                            true,
                            true,
                            atmMoney,
                            random.nextDouble(100, 500)
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

            // Создание какого-нибудь клиента
            User user = new User(
                    "Иванов Иван Иванович",
                    LocalDate.parse("2000-01-01"),
                    "Инженер",
                    null,
                    null,
                    null
            );

            userService.create(user);
            user = userService.read(user.getId());

            double requestedAmount = random.nextDouble(1000, 9999);

            // Выбор банка
            Bank selectedBank = selectBestBank(banks);
            List<BankOffice> selectedBankOffices = new ArrayList<>();
            for (BankOffice office : bankOffices) {
                if (office.getBank().equals(selectedBank)) {
                    selectedBankOffices.add(office);
                }
            }
            BankOffice selectedOffice = selectBestOffice(selectedBankOffices, requestedAmount);
            if (selectedOffice == null) {
                throw new IllegalStateException("Нет подходящего офиса для запрашиваемой суммы.");
            }
            System.out.println(selectedOffice);
            List<Employee> selectedEmployees = new ArrayList<>();
            for (Employee employee : employees) {
                if (employee.getBankOffice().equals(selectedOffice)) {
                    selectedEmployees.add(employee);
                }
            }
            Employee selectedEmployee = selectBestEmployee(selectedEmployees);
            if (selectedEmployee == null) {
                throw new IllegalStateException("Нет доступных сотрудников для выдачи кредита.");
            }
            System.out.println(selectedEmployee);

            PaymentAccount paymentAccount = userService.getOrCreatePaymentAccount(user.getId(), selectedBank.getId());

            if (user.getCreditRating() < 500 && selectedBank.getRating() > 50) {
                throw new IllegalArgumentException("Невозможно выдать кредит пользователю с низким кредитным рейтингом: " + user.getCreditRating());
            }

            List<BankAtm> selectedBankAtms = new ArrayList<>();
            for (BankAtm atm : bankAtms) {
                if (atm.getBankOffice().equals(selectedOffice)) {
                    selectedBankAtms.add(atm);
                }
            }

            BankAtm selectedAtm = selectSuitableAtm(selectedBankAtms, requestedAmount);
            if (selectedAtm == null) {
                selectedAtm = findAtmWithMoney(bankAtms, requestedAmount);
                if (selectedAtm == null) {
                    throw new IllegalStateException("Нет банкоматов с достаточным количеством денег.");
                }
                System.out.println("Перенаправление в другой банкомат: " + selectedAtm);
            } else {
                System.out.println(selectedAtm);
            }

            if (selectedAtm.getMoneyInAtm() >= requestedAmount) {
                selectedAtm.setMoneyInAtm(selectedAtm.getMoneyInAtm() - requestedAmount);
                CreditAccount newCreditAccount = new CreditAccount(
                        user.getId(),
                        selectedBank.getName(),
                        LocalDate.now(),
                        random.nextInt(1, 24),
                        requestedAmount,
                        selectedBank.getInterestRate(),
                        selectedEmployee.getId(),
                        paymentAccount,
                        selectedBank
                );
                creditAccountService.create(newCreditAccount);
                System.out.println("Кредит успешно выдан: " + newCreditAccount);
            } else {
                throw new IllegalStateException("Недостаточно средств в выбранном банкомате для выдачи кредита.");
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static Bank selectBestBank(List<Bank> banks) {
        Bank bestBank = null;
        int maxScore = Integer.MIN_VALUE;
        double minInterestRate = Integer.MAX_VALUE;

        List<Bank> banksWithMinInterestRate = new ArrayList<>();

        for (Bank bank : banks) {
            if (bank.getInterestRate() < minInterestRate) {
                minInterestRate = bank.getInterestRate();
            }
        }

        for (Bank bank : banks) {
            if (bank.getInterestRate() == minInterestRate) {
                banksWithMinInterestRate.add(bank);
                int currentScore = bank.getAtmCount() + bank.getEmployeeCount() + bank.getClientCount() + bank.getOfficeCount();
                if (currentScore > maxScore) {
                    maxScore = currentScore;
                }
            }
        }

        for (Bank bank : banksWithMinInterestRate) {
            int currentScore = bank.getAtmCount() + bank.getEmployeeCount() + bank.getClientCount() + bank.getOfficeCount();
            if (currentScore == maxScore) {
                bestBank = bank;
                break;
            }
        }

        return bestBank;
    }

    private static BankOffice selectBestOffice(List<BankOffice> bankOffices, double requestedAmount) {
        for (BankOffice office : bankOffices) {
            if (office.isCanProvideLoans() && office.isWorking() && office.getMoneyInOffice() >= requestedAmount) {
                return office;
            }
        }
        return null;
    }

    private static Employee selectBestEmployee(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.isCanIssueCredits()) {
                return employee;
            }
        }
        return null;
    }

    private static BankAtm selectSuitableAtm(List<BankAtm> atms, double requestedAmount) {
        for (BankAtm atm : atms) {
            if (atm.getMoneyInAtm() >= requestedAmount) {
                return atm;
            }
        }
        return null;
    }

    private static BankAtm findAtmWithMoney(List<BankAtm> atms, double requestedAmount) {
        for (BankAtm atm : atms) {
            if (atm.getMoneyInAtm() >= requestedAmount) {
                return atm;
            }
        }
        return null;
    }
}
