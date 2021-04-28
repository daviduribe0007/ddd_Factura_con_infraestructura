package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.Entity;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.CamareroId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Sector;

public class Camarero extends Entity<CamareroId> {

    private final Nombre nombre;
    private final Sector sector;

    public Camarero(CamareroId entityId, Nombre  nombre, Sector sector) {
        super(entityId);
        this.nombre = nombre;
        this.sector = sector;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Sector getSector() {
        return sector;
    }


}
