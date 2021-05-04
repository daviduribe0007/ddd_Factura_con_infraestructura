package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarCamarero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.CamareroIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.CamareroId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Sector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

class IngresarCamareroUseCaseTest extends UseCaseHandleBaseTest{

    @Test
    void ingresarCamarero() throws InterruptedException {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var camareroId = CamareroId.of("C1");
        var nombreCamarero = new Nombre("David");
        var sector = new Sector("A1");
        var command = new IngresarCamarero(facturaId, camareroId, nombreCamarero, sector);
        var useCase = new IngresarCamareroUseCase();


        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId, fecha));
        useCase.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .asyncExecutor(useCase, new RequestCommand<>(command))
                .subscribe(subscriber);

        Thread.sleep(800);
        verify(subscriber, times(1)).onNext(eventCaptor.capture());

        var event = (CamareroIngresado) eventCaptor.getAllValues().get(0);

        Mockito.verify(repository).getEventsBy(facturaId.value());
        Assertions.assertEquals("C1", event.getCamareroId().value());
        Assertions.assertEquals("David", event.getNombre().value());
        Assertions.assertEquals("A1", event.getSector().value());
    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {
        return List.of(new FacturaCreada(facturaId, fecha));
    }
}