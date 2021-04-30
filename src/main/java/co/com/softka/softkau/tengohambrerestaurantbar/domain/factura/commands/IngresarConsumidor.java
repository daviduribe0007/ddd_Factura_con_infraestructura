package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ConsumidorId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Celular;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Correo;

public class IngresarConsumidor implements Command {

    private final FacturaId facturaId;
    private final ConsumidorId consumidorId;
    private final Nombre nombre;
    private final Celular celular;
    private final Correo correo;

    public IngresarConsumidor(FacturaId facturaId, ConsumidorId consumidorId, Nombre nombre, Celular celular,
                              Correo correo) {
        this.facturaId = facturaId;
        this.consumidorId = consumidorId;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
    }

    public FacturaId getFacturaId() {
        return facturaId;
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

