package ru.pivovarov.transferservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransferRq {
    private Amount amount;
    private String cardFromCVV;
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardToNumber;
}
