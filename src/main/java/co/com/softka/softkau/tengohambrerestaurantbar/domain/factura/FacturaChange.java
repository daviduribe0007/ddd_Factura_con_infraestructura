package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.EventChange;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Iva;

import java.util.HashMap;

public class FacturaChange extends EventChange {

    public FacturaChange(Factura factura) {

        apply((FacturaCreada event) -> {
            factura.fecha = event.getFecha();
            factura.productos = new HashMap<>();
            factura.subtotal = new Dinero(0);
            factura.iva = new Iva(0.1f);
            factura.propina = new Dinero(0);
            factura.total = new Dinero(0);
        });
    }
}
