package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Celular;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ConsumidorId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Correo;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;

public class ConsumidorIngresado extends DomainEvent {

    private final ConsumidorId consumidorId;
    private final Nombre nombre;
    private final Celular celular;
    private final Correo correo;

    public ConsumidorIngresado(ConsumidorId consumidorId, Nombre nombre, Celular celular, Correo correo) {
        super("tengohambrerestaurantbar.factura.consumidoringresado");
        this.consumidorId = consumidorId;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
    }

    public ConsumidorId getConsumidorId() {
        return consumidorId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Celular getCelular() {
        return celular;
    }

    public Correo getCorreo() {
        return correo;
    }
}
