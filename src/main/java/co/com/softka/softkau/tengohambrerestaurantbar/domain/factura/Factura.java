package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;

public class Factura extends AggregateEvent<FacturaId> {
    protected Dinero propina;
    protected Dinero subtotal;
    protected Dinero total;


    public Factura(FacturaId entityId) {
        super(entityId);
    }
}
