package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.BankOffice;
import tech.reliab.course.zybinii.bank.service.BankOfficeServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class BankOfficeService implements BankOfficeServiceInterface {
    private final BankService bankService;
    private final List<BankOffice> officeStorage = new ArrayList<>();
    private Long idCounter = 1L;

    public BankOfficeService(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public BankOffice create(BankOffice bankOffice) {
        bankOffice.setId(idCounter++);
        officeStorage.add(bankOffice);
        bankService.addOffice(bankOffice.getBank().getId());
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
        return null;
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
