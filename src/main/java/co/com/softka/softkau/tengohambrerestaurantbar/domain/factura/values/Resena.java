package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Resena implements ValueObject<String> {
    private final String value;

    public Resena(String reseña) {
        this.value = Objects.requireNonNull(reseña);
    }

    @Override
    public String value() {
        return value;
    }
}