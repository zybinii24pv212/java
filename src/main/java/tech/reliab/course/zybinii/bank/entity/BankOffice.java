package tech.reliab.course.zybinii.bank.entity;

import lombok.Data;


@Data
public class BankOffice {
    private Long id;
    private String name;
    private String address;
    private boolean isWorking;
    private boolean canPlaceAtm;
    private int atmCount;
    private boolean canProvideLoans;
    private boolean canDispenseCash;
    private boolean canAcceptCash;
    private double moneyInOffice;
    private double rentCost;

    public BankOffice(Long id, String name, String address, boolean isWorking, boolean canPlaceAtm,
                      boolean canProvideLoans, boolean canDispenseCash, boolean canAcceptCash,
                      double moneyInOffice, double rentCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.isWorking = isWorking;
        this.canPlaceAtm = canPlaceAtm;
        this.canProvideLoans = canProvideLoans;
        this.canDispenseCash = canDispenseCash;
        this.canAcceptCash = canAcceptCash;
        this.moneyInOffice = moneyInOffice;
        this.rentCost = rentCost;
    }
}

