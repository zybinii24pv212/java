package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.CreditAccount;
import tech.reliab.course.zybinii.bank.service.CreditAccountServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class CreditAccountService  implements CreditAccountServiceInterface {
    private final List<CreditAccount> creditAccountStorage = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public CreditAccount create(CreditAccount creditAccount) {
        creditAccount.setId(idCounter++);
        creditAccountStorage.add(creditAccount);
        return creditAccount;
    }

    @Override
    public CreditAccount read(Long id) {
        for (CreditAccount account : creditAccountStorage) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public CreditAccount update(CreditAccount creditAccount) {
        for (int i = 0; i < creditAccountStorage.size(); i++) {
            if (creditAccountStorage.get(i).getId().equals(creditAccount.getId())) {
                creditAccountStorage.set(i, creditAccount);
                return creditAccount;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return creditAccountStorage.removeIf(account -> account.getId().equals(id));
    }

    @Override
    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<>(creditAccountStorage);
    }
}
