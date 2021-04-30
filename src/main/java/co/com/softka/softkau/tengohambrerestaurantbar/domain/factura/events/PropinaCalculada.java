package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;

public class PropinaCalculada extends DomainEvent {
    private final Dinero propina;

    public PropinaCalculada(Dinero propina) {
        super("tengohambrerestaurantbar.factura.propinacalculada");
        this.propina = propina;
    }

    public Dinero getPropina() {
        return propina;
    }
}
