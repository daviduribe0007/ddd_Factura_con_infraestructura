package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.infra.handle;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.CamareroIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
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
        update.set("jugadores."+id+".nombre", camareroIngresado.getNombre().value());
        update.set("jugadores."+id+".Sector", camareroIngresado.getSector().value());

        mongoTemplate.updateFirst(getFilterByAggregateId(camareroIngresado), update, COLLECTION_NAME);
    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }



}