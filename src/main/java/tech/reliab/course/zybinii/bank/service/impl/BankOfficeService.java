package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.BankOffice;
import tech.reliab.course.zybinii.bank.service.BankOfficeServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class BankOfficeService implements BankOfficeServiceInterface {
    private final List<BankOffice> officeStorage = new ArrayList<BankOffice>();
    private Long idCounter = 1L;

    @Override
    public BankOffice create(BankOffice bankOffice) {
        bankOffice.setId(idCounter++);
        officeStorage.add(bankOffice);
        return bankOffice;
    }

    @Override
    public BankOffice read(Long id) {
        for (BankOffice office : officeStorage) {
            if (office.getId().equals(id)) {
                return office;
            }
        }
        return null;
    }

    @Override
    public BankOffice update(BankOffice bankOffice) {
        for (int i = 0; i < officeStorage.size(); i++) {
            if (officeStorage.get(i).getId().equals(bankOffice.getId())) {
                officeStorage.set(i, bankOffice);
                return bankOffice;
            }
        }
        return null;  // Если офис с таким ID не найден
    }

    @Override
    public boolean delete(Long id) {
        return officeStorage.removeIf(office -> office.getId().equals(id));
    }

    @Override
    public List<BankOffice> getAllOffices() {
        return new ArrayList<>(officeStorage);
    }

}
