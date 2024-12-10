package tech.reliab.course.zybinii.bank.service;

import tech.reliab.course.zybinii.bank.entity.BankAtm;

import java.util.List;

public interface AtmServiceInterface {
    BankAtm create(BankAtm bankAtm);

    BankAtm read(Long id);

    BankAtm update(BankAtm bankAtm);

    boolean delete(Long id);

    List<BankAtm> getAllAtms();
}
