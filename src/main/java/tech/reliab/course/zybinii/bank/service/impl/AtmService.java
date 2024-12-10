package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.BankAtm;
import tech.reliab.course.zybinii.bank.service.AtmServiceInterface;


import java.util.ArrayList;
import java.util.List;

public class AtmService implements AtmServiceInterface {
    private final List<BankAtm> atmStorage = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public BankAtm create(BankAtm bankAtm) {
        bankAtm.setId(idCounter++);
        atmStorage.add(bankAtm);
        return bankAtm;
    }

    @Override
    public BankAtm read(Long id) {
        for (BankAtm atm : atmStorage) {
            if (atm.getId().equals(id)) {
                return atm;
            }
        }
        return null;  // Если банкомат с таким ID не найден
    }

    @Override
    public BankAtm update(BankAtm bankAtm) {
        for (int i = 0; i < atmStorage.size(); i++) {
            if (atmStorage.get(i).getId().equals(bankAtm.getId())) {
                atmStorage.set(i, bankAtm);
                return bankAtm;
            }
        }
        return null;  // Если банкомат с таким ID не найден
    }

    @Override
    public boolean delete(Long id) {
        return atmStorage.removeIf(atm -> atm.getId().equals(id));
    }

    @Override
    public List<BankAtm> getAllAtms() {
        return new ArrayList<>(atmStorage);
    }
}
