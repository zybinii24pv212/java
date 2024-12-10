package tech.reliab.course.zybinii.bank.service;

import tech.reliab.course.zybinii.bank.entity.Bank;

import java.util.List;

public interface BankServiceInterface {
    Bank create(Bank bank);

    Bank read(Long id);

    Bank update(Bank bank);

    boolean delete(Long id);

    List<Bank> getAllBanks();
}
