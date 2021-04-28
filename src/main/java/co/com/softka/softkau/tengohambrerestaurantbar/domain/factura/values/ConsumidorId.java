package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class ConsumidorId extends Identity {
    public ConsumidorId(String uuid) {
        super(uuid);
    }

    public ConsumidorId() {
    }

    public static ConsumidorId of(String uuid) {
        return new ConsumidorId(uuid);
    }
}
