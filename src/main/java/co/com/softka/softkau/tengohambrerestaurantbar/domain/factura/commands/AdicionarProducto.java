package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Descripcion;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;

public class AdicionarProducto implements Command {

    private final FacturaId facturaId;
    private final ProductoId productoId;
    private final Descripcion descripcion;
    private final Dinero precio;

    public AdicionarProducto(FacturaId facturaId, ProductoId productoId, Descripcion descripcion, Dinero precio) {
        this.facturaId = facturaId;
        this.productoId = productoId;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public FacturaId getFacturaId() {
        return facturaId;
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
