package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaId;

public class Factura extends AggregateEvent<FacturaId> {
    public Factura(FacturaId entityId) {
        super(entityId);
    }
}
