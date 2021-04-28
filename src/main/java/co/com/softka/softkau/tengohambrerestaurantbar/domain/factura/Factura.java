package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;

import java.util.Map;

public class Factura extends AggregateEvent<FacturaId> {

    protected Camarero camarero;
    protected Consumidor consumidor;
    protected Fecha fecha;
    protected Iva iva;
    protected Map<ProductoId,Producto> productos;
    protected Dinero propina;
    protected Dinero subtotal;
    protected Dinero total;



    public Factura(FacturaId facturaId,Fecha fecha) {
        super(facturaId);
        appendChange(new FacturaCreada(facturaId,fecha)).apply();
    }

    private Factura(FacturaId facturaId){
        super(facturaId);
        subscribe(new FacturaChange(this));
    }


}
