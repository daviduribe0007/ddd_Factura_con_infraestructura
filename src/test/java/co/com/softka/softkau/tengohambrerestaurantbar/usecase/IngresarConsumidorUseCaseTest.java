package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarConsumidor;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ConsumidorIngresado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ConsumidorId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Celular;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Correo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

class IngresarConsumidorUseCaseTest extends UseCaseHandleBaseTest{

    @Test
    void ingresarConsumidor() throws InterruptedException {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var consumidorId = ConsumidorId.of("123456789");
        var nombreConsumidor = new Nombre("Maria");
        var celular = new Celular("3193256835");
        var correo = new Correo("Daviduribe@gmail.com");
        var command = new IngresarConsumidor(facturaId, consumidorId, nombreConsumidor, celular, correo);
        var useCase = new IngresarConsumidorUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId, fecha));
        useCase.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .asyncExecutor(useCase, new RequestCommand<>(command))
                .subscribe(subscriber);

        Thread.sleep(800);
        verify(subscriber, times(1)).onNext(eventCaptor.capture());


        var event = (ConsumidorIngresado) eventCaptor.getAllValues().get(0);

        Mockito.verify(repository).getEventsBy(facturaId.value());
        Assertions.assertEquals("123456789", event.getConsumidorId().value());
        Assertions.assertEquals("Maria", event.getNombre().value());
        Assertions.assertEquals("3193256835", event.getCelular().value());
        Assertions.assertEquals("Daviduribe@gmail.com", event.getCorreo().value());
    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {
        return List.of(new FacturaCreada(facturaId, fecha));
    }
}