package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.PropinaCalculada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.TotalCalculado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoAdicionado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.SubtotalModificado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ResenaAgregada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Resena;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Descripcion;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.CalcularTotalUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class CalcularTotalUseCaseTest extends  UseCaseHandleBaseTest{

    @Test
    void calcularPropina() throws InterruptedException {
        var propina = new Dinero(10000);
        var event = new PropinaCalculada(propina);
        var useCase = new CalcularTotalUseCase();

        event.setAggregateRootId("199");

        when(repository.getEventsBy(event.aggregateRootId())).thenReturn(eventStored());
        useCase.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor(event.aggregateRootId())
                .asyncExecutor(useCase, new TriggeredEvent<>(event))
                .subscribe(subscriber);

        Thread.sleep(800);
        verify(subscriber,times(1)).onNext(eventCaptor.capture());

        var totalcalculado = (TotalCalculado) eventCaptor.getAllValues().get(0);

        Assertions.assertEquals(230000, totalcalculado.getTotal().value());
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