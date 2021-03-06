package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.EventChange;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.CamareroIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.TotalCalculado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ConsumidorIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoAdicionado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.SubtotalModificado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoRemovido;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ResenaAgregada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.PropinaCalculada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Iva;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Resena;

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
            factura.resena = new Resena(" ");
        });

        apply((CamareroIngresado event) -> {
            factura.camarero = new Camarero(event.getCamareroId(),
                    event.getNombre(),
                    event.getSector());
        });

        apply((ConsumidorIngresado event) -> {
            factura.consumidor = new Consumidor(event.getConsumidorId(),
                    event.getNombre(),
                    event.getCelular(),
                    event.getCorreo());
        });

        apply((ProductoAdicionado event) -> {
            factura.productos.put(event.getProductoId(),
                    new Producto(event.getProductoId(),
                            event.getDescripcion(),
                            event.getPrecio()));
        });

        apply((SubtotalModificado event) -> {
            factura.subtotal = event.getSubtotal();
        });

        apply((ProductoRemovido event) -> {
            factura.productos.remove(event.getProductoId());
        });

        apply((ResenaAgregada event) -> {
            factura.resena = event.getResena();
        });

        apply((PropinaCalculada event) -> {
            factura.propina = event.getPropina();
        });

        apply((TotalCalculado event) -> {
            factura.total = event.getTotal();
        });
    }
}
