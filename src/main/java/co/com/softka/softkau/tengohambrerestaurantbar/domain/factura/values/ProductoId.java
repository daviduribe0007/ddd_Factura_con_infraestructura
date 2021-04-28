package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class ProductoId extends Identity {

    public ProductoId(String uuid) {
        super(uuid);
    }

    public ProductoId() {
    }

    public static ProductoId of(String uuid) {
        return new ProductoId(uuid);
    }
}
