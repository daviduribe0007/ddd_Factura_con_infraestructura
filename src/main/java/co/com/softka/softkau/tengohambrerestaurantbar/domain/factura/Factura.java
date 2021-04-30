package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.CamareroIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoRemovido;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.*;
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
    protected Resena resena;




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

    public void ingresarConsumidor(ConsumidorId consumidorId,Nombre nombre,Celular celular, Correo correo){
        appendChange( new ConsumidorIngresado(consumidorId,nombre,celular,correo)).apply();
    }
    public void adicionarProducto(ProductoId productoId, Descripcion descripcion, Dinero precio){
        appendChange(new ProductoAdicionado(productoId, descripcion, precio));
        appendChange(new SubtotalModificado(subtotal.sumar(precio.value())));
    }

    public void retirarProducto(ProductoId productoId){
        var producto = productos.get(productoId);
        appendChange(new ProductoRemovido(productoId));
        appendChange(new SubtotalModificado(subtotal.restar(producto.precio().value())));
    }

    public void agregarResena(Resena resena){
        appendChange( new ResenaAgregada(resena)).apply();
    }

    public void calcularPropina(Dinero propina){
        appendChange(new PropinaCalculada(propina)).apply();
    }

    public Dinero getSubtotal() {
        return subtotal;
    }

}
