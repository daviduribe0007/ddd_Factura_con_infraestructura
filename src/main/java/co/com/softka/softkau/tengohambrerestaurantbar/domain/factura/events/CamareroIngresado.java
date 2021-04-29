package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.CamareroId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Sector;

public class CamareroIngresado extends DomainEvent {

    private final CamareroId camareroId;
    private final Nombre nombre;
    private final Sector sector;

    public CamareroIngresado(CamareroId camareroId, Nombre nombre, Sector sector) {
        super("tengohambrerestaurantbar.factura.camareroingresado");
        this.camareroId = camareroId;
        this.nombre = nombre;
        this.sector = sector;
    }

    public CamareroId getCamareroId() {
        return camareroId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Sector getSector() {
        return sector;
    }
}
