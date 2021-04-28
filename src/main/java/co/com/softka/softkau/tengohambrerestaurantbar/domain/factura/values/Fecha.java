package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Fecha implements ValueObject<String> {

    private final String value;

    public Fecha(String fecha) {
        this.value = Objects.requireNonNull(fecha);
    }

    @Override
    public String value() {
        return value;
    }
}