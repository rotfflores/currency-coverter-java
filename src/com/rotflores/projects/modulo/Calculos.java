package com.rotflores.projects.modulo;

public class Calculos {
    private Currency currency;

    public Calculos(Currency currency) {
        this.currency = currency;
    }

    public double usdToArg() {
        return currency.getCuantity() * currency.getArg();
    }

    public double usdToBrl() {
        return currency.getCuantity() * currency.getBrl();
    }

    public double usdToClp() {
        return currency.getCuantity() * currency.getClp();
    }

    public double convert(String from, String to) {
        double fromRate = 0;
        double toRate = 0;

        switch (from.toLowerCase()) {
            case "usd": fromRate = currency.getUsd(); break;
            case "arg": fromRate = currency.getArg(); break;
            case "brl": fromRate = currency.getBrl(); break;
            case "clp": fromRate = currency.getClp(); break;
        }

        switch (to.toLowerCase()) {
            case "usd": toRate = currency.getUsd(); break;
            case "arg": toRate = currency.getArg(); break;
            case "brl": toRate = currency.getBrl(); break;
            case "clp": toRate = currency.getClp(); break;
        }

        return currency.getCuantity() * (toRate / fromRate);
    }
}
