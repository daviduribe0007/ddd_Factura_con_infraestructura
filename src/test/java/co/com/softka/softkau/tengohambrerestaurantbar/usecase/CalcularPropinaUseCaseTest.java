package co.com.softka.softkau.tengohambrerestaurantbar.usecase;


import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ResenaAgregada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.PropinaCalculada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoAdicionado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.SubtotalModificado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Resena;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Descripcion;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

class CalcularPropinaUseCaseTest extends UseCaseHandleBaseTest{


    @Test
    void calcularPropina() throws InterruptedException {
        var resena = new Resena("Muy bueno todo");
        var event = new ResenaAgregada(resena);
        var useCase = new CalcularPropinaUseCase();

        event.setAggregateRootId("199");

        when(repository.getEventsBy(event.aggregateRootId())).thenReturn(eventStored(resena));
        useCase.addRepository(repository);

         UseCaseHandler.getInstance()
                .setIdentifyExecutor(event.aggregateRootId())
                .asyncExecutor(useCase, new TriggeredEvent<>(event))
                 .subscribe(subscriber);

        Thread.sleep(800);
        verify(subscriber,times(1)).onNext(eventCaptor.capture());

        var propinacalculada = (PropinaCalculada) eventCaptor.getAllValues().get(0);

        Assertions.assertEquals(1750, propinacalculada.getPropina().value());
    }

    private List<DomainEvent> eventStored(Resena resena) {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
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