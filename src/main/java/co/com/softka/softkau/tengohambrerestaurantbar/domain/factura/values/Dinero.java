package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Dinero implements ValueObject<Integer> {
    private final Integer value;

    public Dinero(Integer money) {
        this.value = Objects.requireNonNull(money);
    }

    @Override
    public Integer value() {
        return value;
    }
}
