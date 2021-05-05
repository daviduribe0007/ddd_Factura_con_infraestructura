package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.CrearFactura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.CrearFacturaUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CrearFacturaUseCaseTest extends UseCaseHandleBaseTest {

    @Test
    void crearFactura() throws InterruptedException {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2020,04,28");
        var command = new CrearFactura(facturaId, fecha);
        var useCase = new CrearFacturaUseCase();

        UseCaseHandler.getInstance()
                .asyncExecutor(useCase, new RequestCommand<>(command))
                .subscribe(subscriber);

        Thread.sleep(800);
        verify(subscriber, times(1)).onNext(eventCaptor.capture());

        FacturaCreada facturaCreada = (FacturaCreada) eventCaptor.getAllValues().get(0);

        Assertions.assertTrue(Objects.nonNull(facturaCreada.getClass()));
        Assertions.assertEquals("1", facturaCreada.aggregateRootId());
        Assertions.assertEquals("2020,04,28", facturaCreada.getFecha().value());
    }
}