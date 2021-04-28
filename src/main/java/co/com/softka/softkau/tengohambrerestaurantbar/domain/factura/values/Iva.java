package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;


import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Iva implements ValueObject<Float> {
    private final Float value;

    public Iva(Float iva) {
        this.value = Objects.requireNonNull(iva);
    }

    @Override
    public Float value() {
        return value;
    }
}
