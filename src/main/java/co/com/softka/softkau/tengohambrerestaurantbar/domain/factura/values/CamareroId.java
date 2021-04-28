package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class CamareroId extends Identity {
    public CamareroId(String uuid) {
        super(uuid);
    }

    public CamareroId() {
    }

    public static CamareroId of(String uuid) {
        return new CamareroId(uuid);
    }
}
