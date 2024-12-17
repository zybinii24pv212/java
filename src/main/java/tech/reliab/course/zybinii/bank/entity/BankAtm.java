package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class BankAtm {
    private Long id;
    private String name;
    private String address;
    private AtmStatus status;
    private Bank bank;
    private BankOffice bankOffice;
    private Employee serviceEmployee;
    private boolean canDispenseCash;
    private boolean canAcceptCash;
    private double moneyInAtm;
    private double maintenanceCost;

    public BankAtm(String name, Bank bank, BankOffice bankOffice,
                   Employee serviceEmployee, boolean canDispenseCash, boolean canAcceptCash,
                   double moneyInAtm, double maintenanceCost) {
        this.name = name;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.address = bankOffice.getAddress();
        this.serviceEmployee = serviceEmployee;
        this.canDispenseCash = canDispenseCash;
        this.canAcceptCash = canAcceptCash;
        this.moneyInAtm = moneyInAtm;
        this.maintenanceCost = maintenanceCost;

        this.status = determineStatus(moneyInAtm, canDispenseCash);
    }

    private AtmStatus determineStatus(double moneyInAtm, boolean canDispenseCash) {
        if (moneyInAtm <= 0) {
            return AtmStatus.NO_MONEY;
        } else if (!canDispenseCash) {
            return AtmStatus.NOT_WORKING;
        } else {
            return AtmStatus.WORKING;
        }
    }

    public void updateMoneyInAtm(double amount) {
        this.moneyInAtm = amount;
        this.status = determineStatus(amount, canDispenseCash);
    }
}