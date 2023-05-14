package ru.pivovarov.transferservice.model;

public record Transfer(Amount amount, String cardFromCVV, String cardFromNumber,
                       String cardFromValidTill, String cardToNumber, String date, String time) {

    @Override
    public String toString() {
        return  "сумма и валюта =" + amount +
                ", с карты номер ='" + cardFromNumber + '\'' +
                ", на карту номер ='" + cardToNumber + '\'' +
                ", дата ='" + date + '\'' +
                ", время ='" + time + '\'' +
                '}';
    }
}
