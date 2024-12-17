package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.Bank;
import tech.reliab.course.zybinii.bank.service.BankServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class BankService implements BankServiceInterface {
    private final List<Bank> bankStorage = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public Bank create(Bank bank) {
        bank.setId(idCounter++);
        bankStorage.add(bank);
        return bank;
    }


    @Override
    public Bank read(Long id) {
        for (Bank bank : bankStorage) {
            if (bank.getId().equals(id)) {
                return bank;
            }
        }
        return null;
    }

    @Override
    public Bank update(Bank bank) {
        for (int i = 0; i < bankStorage.size(); i++) {
            if (bankStorage.get(i).getId().equals(bank.getId())) {
                bankStorage.set(i, bank);
                return bank;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return bankStorage.removeIf(bank -> bank.getId().equals(id));
    }

    @Override
    public List<Bank> getAllBanks() {
        return new ArrayList<>(bankStorage);
    }

    @Override
    public void addOffice(Long id) {
        Bank bank = this.read(id);
        bank.addOffice();
    }

    @Override
    public void addAtm(Long id) {
        Bank bank = this.read(id);
        bank.addAtm();
    }

    @Override
    public void addEmployee(Long id) {
        Bank bank = this.read(id);
        bank.addEmployee();
    }

    @Override
    public void addClient(Long id) {
        Bank bank = this.read(id);
        bank.addClient();
    }
}
