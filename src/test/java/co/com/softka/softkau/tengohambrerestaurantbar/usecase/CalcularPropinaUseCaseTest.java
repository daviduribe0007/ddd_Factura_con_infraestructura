package co.com.softka.softkau.tengohambrerestaurantbar.usecase;


import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.*;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalcularPropinaUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    @Test
    void calcularPropina() {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var resena = new Resena("Muy bueno todo");
        var event = new ResenaAgregada(resena);
        var useCase = new CalcularPropinaUseCase();
        event.setAggregateRootId("199");

        when(repository.getEventsBy(event.aggregateRootId())).thenReturn(eventStored( facturaId, fecha,resena));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(event.aggregateRootId())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var propinacalculada = (PropinaCalculada) events.get(0);



        Assertions.assertEquals(1750,propinacalculada.getPropina().value());
    }
    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha, Resena resena) {
        var productoId = ProductoId.of("111");
        var descripcion = new Descripcion("Sancocho trifasico");
        var precio = new Dinero(35000);

        return List.of(
                new FacturaCreada(facturaId, fecha),
                new ProductoAdicionado(productoId, descripcion, precio),
                new SubtotalModificado(new Dinero(35000)),
                new ResenaAgregada(resena)
        );
    }
}