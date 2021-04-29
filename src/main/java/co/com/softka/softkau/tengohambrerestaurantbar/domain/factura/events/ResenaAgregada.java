package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Resena;

public class ResenaAgregada extends DomainEvent {

    private final Resena reseña;

    public ResenaAgregada(Resena resena) {
        super("tengohambrerestaurantbar.factura.resenaagregada");
        this.reseña = resena;
    }

    public Resena getResena() {
        return reseña;
    }
}
