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

    public Dinero sumar(Integer dinero) {
        if (dinero <= 0) {
            throw new IllegalArgumentException("El valor del dinero no puede ser negativo ni 0");
        }
        return new Dinero(this.value() + dinero);
    }

    public Dinero restar(Integer dinero) {
        if (0 <= dinero) {
            throw new IllegalArgumentException("El valor del dinero no puede ser negativo ni 0");
        }
        return new Dinero(this.value() - dinero);
    }
}
