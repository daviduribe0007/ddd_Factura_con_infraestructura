package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
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
class CalcularTotalUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void calcularPropina() {
        var propina = new Dinero(10000);
        var event = new PropinaCalculada(propina);
        var useCase = new CalcularTotalUseCase();
        event.setAggregateRootId("199");

        when(repository.getEventsBy(event.aggregateRootId())).thenReturn(eventStored());
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(event.aggregateRootId())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var totalcalculado = (TotalCalculado) events.get(0);

        Assertions.assertEquals(230000,totalcalculado.getTotal().value());
    }
    private List<DomainEvent> eventStored() {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var resena = new Resena("Muy bueno todo");
        var productoId = ProductoId.of("666");
        var descripcion = new Descripcion("Whisky Highland Park 12 Años Botella – 700ml");
        var precio = new Dinero(200000);

        return List.of(
                new FacturaCreada(facturaId, fecha),
                new ProductoAdicionado(productoId, descripcion, precio),
                new SubtotalModificado(new Dinero(200000)),
                new ResenaAgregada(resena),
                new PropinaCalculada(new Dinero(10000))

        );
    }

}