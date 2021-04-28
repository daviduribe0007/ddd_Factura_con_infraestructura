package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.CrearFactura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CrearFacturaUseCaseTest {

    @Test
    void crearFactura(){
        var facturaId = FacturaId.of("1");
        var fecha =  new Fecha("2020,04,28");

        var command = new CrearFactura(facturaId, fecha);
        var useCase = new CrearFacturaUseCase();

        List<DomainEvent> events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        FacturaCreada facturaCreada = (FacturaCreada) events.get(0);

        Assertions.assertTrue(Objects.nonNull(facturaCreada.getClass()));
        Assertions.assertEquals("1", facturaCreada.aggregateRootId());
        Assertions.assertEquals("2020,04,28", facturaCreada.getFecha().value());

    }

}