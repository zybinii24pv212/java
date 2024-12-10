package tech.reliab.course.zybinii.bank.service;

import tech.reliab.course.zybinii.bank.entity.PaymentAccount;

import java.util.List;

public interface PaymentAccountServiceInterface {
    PaymentAccount create(PaymentAccount paymentAccount);
    PaymentAccount read(Long id);
    PaymentAccount update(PaymentAccount paymentAccount);
    boolean delete(Long id);
    List<PaymentAccount> getAllPaymentAccounts();
}
