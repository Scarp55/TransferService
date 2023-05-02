package ru.pivovarov.transferservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfer {
    private Amount amount;
    private String cardFromCVV;
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardToNumber;
    private String date;
    private String time;

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
