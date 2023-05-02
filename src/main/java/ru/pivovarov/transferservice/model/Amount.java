package ru.pivovarov.transferservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Amount {
    private String currency;
    private int value;

    @Override
    public String toString() {
        return value + " " + "RUR";
    }
}
