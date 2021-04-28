package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;

public class FacturaCreada extends DomainEvent {

    private final FacturaId facturaId;
    private final Fecha fecha;

    public FacturaCreada(FacturaId facturaId, Fecha fecha) {
        super("tengohambrerestaurantbar.factura.facturacreada");
        this.facturaId = facturaId;
        this.fecha = fecha;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
