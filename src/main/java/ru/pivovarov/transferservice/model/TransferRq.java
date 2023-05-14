package ru.pivovarov.transferservice.model;

public record TransferRq (Amount amount, String cardFromCVV, String cardFromNumber,
                          String cardFromValidTill, String cardToNumber) {}
