package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Resena;

public class AgregarResena implements Command {

    private final FacturaId facturaId;
    private final Resena resena;

    public AgregarResena(FacturaId facturaId, Resena resena) {
        this.facturaId = facturaId;
        this.resena = resena;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }

    public Resena getResena() {
        return resena;
    }
}
