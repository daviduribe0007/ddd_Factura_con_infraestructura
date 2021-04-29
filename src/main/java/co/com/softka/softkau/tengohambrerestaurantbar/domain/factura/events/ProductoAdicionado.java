package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Descripcion;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;

public class ProductoAdicionado extends DomainEvent {

    private final ProductoId productoId;
    private final Descripcion descripcion;
    private final Dinero precio;

    public ProductoAdicionado(ProductoId productoId, Descripcion descripcion, Dinero precio) {
        super("tengohambrerestaurantbar.factura.productoadicionado");
        this.productoId = productoId;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public ProductoId getProductoId() {
        return productoId;
    }

    public Descripcion getDescripcion() {
        return descripcion;
    }

    public Dinero getPrecio() {
        return precio;
    }
}
