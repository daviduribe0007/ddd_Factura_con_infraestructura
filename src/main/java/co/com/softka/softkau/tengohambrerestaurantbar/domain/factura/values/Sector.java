package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Sector implements ValueObject<String> {
    private final String value;

    public Sector(String sector) {
        this.value = Objects.requireNonNull(sector);
    }

    @Override
    public String value() {
        return value;
    }
}
