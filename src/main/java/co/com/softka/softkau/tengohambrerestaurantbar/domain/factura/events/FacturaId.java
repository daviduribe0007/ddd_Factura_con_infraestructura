package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.Identity;

public class FacturaId extends Identity {

    public FacturaId(String uuid) {
        super(uuid);
    }

    public FacturaId() {
    }

    public static FacturaId of(String uuid) {
        return new FacturaId(uuid);
    }
}
