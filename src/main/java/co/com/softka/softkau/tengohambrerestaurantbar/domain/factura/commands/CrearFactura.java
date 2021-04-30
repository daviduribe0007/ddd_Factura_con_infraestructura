package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;

public class CrearFactura implements Command {
    private final FacturaId facturaId;
    private final Fecha fecha;

    public CrearFactura(FacturaId facturaId, Fecha fecha) {

        this.facturaId = facturaId;
        this.fecha = fecha;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
