package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Nombre implements ValueObject<String> {

    private final String value;

    public Nombre(String nombre) {
        this.value = Objects.requireNonNull(nombre);
    }

    @Override
    public String value() {
        return value;
    }
}
