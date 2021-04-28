package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.Entity;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Celular;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ConsumidorId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;

public class Consumidor extends Entity<ConsumidorId> {

    private final Nombre nombre;
    private final Celular celular;

    public Consumidor(ConsumidorId entityId, Nombre nombre, Celular celular) {
        super(entityId);
        this.nombre = nombre;
        this.celular = celular;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Celular celular() {
        return celular;
    }
}
