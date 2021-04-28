package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Celular implements ValueObject<String> {

    private final String value;

    public Celular(String celular) {
        this.value = Objects.requireNonNull(celular);
    }

    @Override
    public String value() {
        return value;
    }
}
