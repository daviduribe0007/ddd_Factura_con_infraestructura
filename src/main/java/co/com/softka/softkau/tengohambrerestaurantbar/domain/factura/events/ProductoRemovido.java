package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;

public class ProductoRemovido extends DomainEvent {

    private final ProductoId productoId;

    public ProductoRemovido( ProductoId productoId) {
        super("tengohambrerestaurantbar.factura.productoremovido");
        this.productoId = productoId;
    }

    public ProductoId getProductoId() {
        return productoId;
    }
}
