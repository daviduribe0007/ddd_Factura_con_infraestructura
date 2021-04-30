package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;

public class TotalCalculado extends DomainEvent {

    private final Dinero total;

    public TotalCalculado(Dinero total) {
        super("tengohambrerestaurantbar.factura.totalcalculado");
        this.total = total;
    }

    public Dinero getTotal() {
        return total;
    }
}
