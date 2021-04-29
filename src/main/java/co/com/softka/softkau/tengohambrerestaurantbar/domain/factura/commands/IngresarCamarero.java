package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.CamareroId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Sector;

public class IngresarCamarero implements Command {

    private final FacturaId facturaId;
    private final CamareroId camareroId;
    private final Nombre nombre;
    private final Sector sector;

    public IngresarCamarero(FacturaId facturaId, CamareroId camareroId, Nombre nombre, Sector sector){
        this.facturaId = facturaId;

        this.camareroId = camareroId;
        this.nombre = nombre;
        this.sector = sector;
    }

    public FacturaId getFacturaId() {
        return facturaId;
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
