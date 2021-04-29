package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;

public class SubtotalSumado extends DomainEvent {

    private final Dinero sumar;

    public SubtotalSumado(Dinero sumar) {
        super("tengohambrerestaurantbar.factura.subtotalsumado");
        this.sumar = sumar;
    }

    public Dinero getSumar() {
        return sumar;
    }
}
