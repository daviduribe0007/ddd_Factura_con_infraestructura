package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;

public class SubtotalModificado extends DomainEvent {

    private final Dinero subtotal;

    public SubtotalModificado(Dinero subtotal) {
        super("tengohambrerestaurantbar.factura.subtotalmodificado");
        this.subtotal = subtotal;
    }

    public Dinero getSubtotal() {
        return subtotal;
    }
}
