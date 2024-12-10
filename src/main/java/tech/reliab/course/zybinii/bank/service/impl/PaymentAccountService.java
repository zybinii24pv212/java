package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.PaymentAccount;
import tech.reliab.course.zybinii.bank.service.PaymentAccountServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class PaymentAccountService implements PaymentAccountServiceInterface {
    private final List<PaymentAccount> paymentAccountStorage = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {
        paymentAccount.setId(idCounter++);
        paymentAccountStorage.add(paymentAccount);
        return paymentAccount;
    }

    @Override
    public PaymentAccount read(Long id) {
        for (PaymentAccount paymentAccount : paymentAccountStorage) {
            if (paymentAccount.getId().equals(id)) {
                return paymentAccount;
            }
        }
        return null;  // Если платежный счет с таким ID не найден
    }

    @Override
    public PaymentAccount update(PaymentAccount paymentAccount) {
        for (int i = 0; i < paymentAccountStorage.size(); i++) {
            if (paymentAccountStorage.get(i).getId().equals(paymentAccount.getId())) {
                paymentAccountStorage.set(i, paymentAccount);
                return paymentAccount;
            }
        }
        return null;  // Если платежный счет с таким ID не найден
    }

    @Override
    public boolean delete(Long id) {
        return paymentAccountStorage.removeIf(paymentAccount -> paymentAccount.getId().equals(id));
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<>(paymentAccountStorage);
    }

}
