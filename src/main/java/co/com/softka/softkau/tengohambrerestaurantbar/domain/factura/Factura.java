package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Iva;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;

import java.util.Map;

public class Factura extends AggregateEvent<FacturaId> {
    protected Dinero propina;
    protected Dinero subtotal;
    protected Dinero total;
    protected Iva iva;
    protected Consumidor consumidor;
    protected Camarero camarero;
    protected Map<ProductoId,Producto> productos;


    public Factura(FacturaId entityId,) {
        super(entityId);
    }
    private Factura(FacturaId )

}
