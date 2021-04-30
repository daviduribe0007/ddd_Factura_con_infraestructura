package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Correo implements ValueObject<String> {
    private final String value;

    public Correo(String correo) {
        this.value = Objects.requireNonNull(correo);
    }

    @Override
    public String value() {
        return value;
    }
}
