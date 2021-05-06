package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.infra.handle;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class FacturaMaterialize {

    private static final String COLLECTION_NAME = "factura";
    private static final Logger logger = Logger.getLogger(FacturaMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleEventFacturaCreada(FacturaCreada facturaCreada) {
        logger.info("****** Handle event facturaCreada");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", facturaCreada.getFacturaId().value());
        data.put("_fecha",facturaCreada.getFecha().value());
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventCamareroAdicionado(CamareroIngresado camareroIngresado) {
        logger.info("****** Handle event camareroIngresado");
        Update update = new Update();
        var id = camareroIngresado.getCamareroId().value();
        update.set("camarero."+id+".nombre", camareroIngresado.getNombre().value());
        update.set("camarero."+id+".Sector", camareroIngresado.getSector().value());

        mongoTemplate.updateFirst(getFilterByAggregateId(camareroIngresado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventConsumidorAdicionado(ConsumidorIngresado consumidorIngresado) {
        logger.info("****** Handle event consumidorIngresado");
        Update update = new Update();
        var id = consumidorIngresado.getConsumidorId().value();
        update.set("consumidor."+id+".nombre", consumidorIngresado.getNombre().value());
        update.set("consumidor."+id+".celular", consumidorIngresado.getCelular().value());
        update.set("consumidor."+id+".correo", consumidorIngresado.getCorreo().value());

        mongoTemplate.updateFirst(getFilterByAggregateId(consumidorIngresado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventProductoAdicionado(ProductoAdicionado productoAdicionado) {
        logger.info("****** Handle event consumidorIngresado");
        Update update = new Update();
        var id = productoAdicionado.getProductoId().value();
        update.set("producto."+id+".descripcion", productoAdicionado.getDescripcion().value());
        update.set("producto."+id+".precio", productoAdicionado.getPrecio().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(productoAdicionado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventSubtotalModificado(SubtotalModificado subtotalModificado) {
        logger.info("****** Handle event subtotal modificado");
        Update update2 = new Update();
        update2.set("subtotal", subtotalModificado.getSubtotal().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(subtotalModificado), update2, COLLECTION_NAME);
    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }



}