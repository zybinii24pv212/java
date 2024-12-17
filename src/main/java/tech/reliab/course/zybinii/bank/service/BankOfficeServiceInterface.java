package tech.reliab.course.zybinii.bank.service;

import tech.reliab.course.zybinii.bank.entity.BankOffice;
import tech.reliab.course.zybinii.bank.service.impl.BankOfficeService;
import tech.reliab.course.zybinii.bank.service.impl.BankService;

import java.util.List;

public interface BankOfficeServiceInterface {
    BankOffice create(BankOffice bankOffice);

    BankOffice read(Long id);

    BankOffice update(BankOffice bankOffice);

    boolean delete(Long id);

    List<BankOffice> getAllOffices();
}
