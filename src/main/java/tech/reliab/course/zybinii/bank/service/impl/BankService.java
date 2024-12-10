package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.Bank;
import tech.reliab.course.zybinii.bank.service.BankServiceInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService implements BankServiceInterface {
    private final Map<Long, Bank> bankStorage = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public Bank create(Bank bank) {
        
        bank.setId(idCounter++);
        bankStorage.put(bank.getId(), bank);
        return bank;
    }

    @Override
    public Bank read(Long id) {
        return bankStorage.get(id);
    }

    @Override
    public Bank update(Bank bank) {
        if (bankStorage.containsKey(bank.getId())) {
            bankStorage.put(bank.getId(), bank);
            return bank;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return bankStorage.remove(id) != null;
    }

    @Override
    public List<Bank> getAllBanks() {
        return new ArrayList<>(bankStorage.values());
    }

}
