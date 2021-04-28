package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;

import java.util.Map;

public class Factura extends AggregateEvent<FacturaId> {
    protected Dinero propina;
    protected Dinero subtotal;
    protected Dinero total;
    protected Iva iva;
    protected Consumidor consumidor;
    protected Camarero camarero;
    protected Map<ProductoId,Producto> productos;
    protected Fecha fecha;


    public Factura(FacturaId facturaId,Fecha fecha) {
        super(facturaId);
        appendChange(new FacturaCreada(facturaId,fecha)).apply();
    }


}
