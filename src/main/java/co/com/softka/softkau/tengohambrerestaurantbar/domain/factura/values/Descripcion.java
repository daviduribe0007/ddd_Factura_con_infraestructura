package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Descripcion implements ValueObject<String> {
    private final String value;

    public Descripcion(String descripcion) {
        this.value = Objects.requireNonNull(descripcion);
    }

    @Override
    public String value() {
        return value;
    }
}
