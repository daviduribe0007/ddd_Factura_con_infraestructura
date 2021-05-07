package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Resena;

public class ResenaAgregada extends DomainEvent {

    private final Resena resena;

    public ResenaAgregada(Resena resena) {
        super("tengohambrerestaurantbar.factura.resenaagregada");
        this.resena = resena;
    }

    public Resena getResena() {
        return resena;
    }

}
