package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Dinero implements ValueObject<Integer> {
    private final Integer value;

    public Dinero(Integer dinero) {
        this.value = Objects.requireNonNull(dinero);
    }

    @Override
    public Integer value() {
        return value;
    }
}
