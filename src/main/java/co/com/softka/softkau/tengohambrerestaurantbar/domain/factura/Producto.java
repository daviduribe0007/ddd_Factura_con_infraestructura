package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.Entity;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Descripcion;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;

public class Producto extends Entity<ProductoId> {
    private final Descripcion descripcion;
    private final Dinero precio;

    public Producto(ProductoId entityId, Descripcion descripcion, Dinero precio) {
        super(entityId);
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Descripcion descripcion() {
        return descripcion;
    }

    public Dinero precio() {
        return precio;
    }

}
