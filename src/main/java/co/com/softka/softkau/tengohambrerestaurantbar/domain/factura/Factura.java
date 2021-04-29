package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.CamareroIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ConsumidorIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;

import java.util.List;
import java.util.Map;

public class Factura extends AggregateEvent<FacturaId> {

    protected Camarero camarero;
    protected Consumidor consumidor;
    protected Fecha fecha;
    protected Iva iva;
    protected Map<ProductoId,Producto> productos;
    protected Dinero propina;
    protected Dinero subtotal;
    protected Dinero total;



    public Factura(FacturaId facturaId,Fecha fecha) {
        super(facturaId);
        appendChange(new FacturaCreada(facturaId,fecha)).apply();
    }

    private Factura(FacturaId facturaId){
        super(facturaId);
        subscribe(new FacturaChange(this));
    }

    public static Factura from(FacturaId facturaId, List<DomainEvent> events) {
        var factura = new Factura(facturaId);
        events.forEach(factura::applyEvent);
        return factura;
    }

    public void ingresarCamarero(CamareroId camareroId,Nombre nombre,Sector sector){
        appendChange( new CamareroIngresado(camareroId,nombre,sector)).apply();
    }

    public void ingresarConsumidor(ConsumidorId consumidorId,Nombre nombre,Celular celular){
        appendChange( new ConsumidorIngresado(consumidorId,nombre,celular)).apply();
    }


}
