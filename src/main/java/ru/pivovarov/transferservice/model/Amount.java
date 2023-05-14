package ru.pivovarov.transferservice.model;

public record Amount (String currency, int value){

    @Override
    public String toString() {
        return value + " " + "RUR";
    }
}
