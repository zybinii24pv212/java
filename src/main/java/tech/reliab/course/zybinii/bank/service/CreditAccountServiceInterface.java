package tech.reliab.course.zybinii.bank.service;

import tech.reliab.course.zybinii.bank.entity.CreditAccount;

import java.util.List;

public interface CreditAccountServiceInterface {
    CreditAccount create(CreditAccount creditAccount);

    CreditAccount read(Long id);

    CreditAccount update(CreditAccount creditAccount);

    boolean delete(Long id);

    List<CreditAccount> getAllCreditAccounts();
}
