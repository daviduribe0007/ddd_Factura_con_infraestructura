package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.AgregarResena;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ResenaAgregada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Resena;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgregarResenaUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void agregarResena() {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var resena = new Resena("Muy buena la comida pero demorada");
        var command = new AgregarResena(facturaId, resena);
        var useCase = new AgregarResenaUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId, fecha));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ResenaAgregada) events.get(0);

        Mockito.verify(repository).getEventsBy(facturaId.value());
        Assertions.assertEquals("Muy buena la comida pero demorada", event.getResena().value());
    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {
        return List.of(new FacturaCreada(facturaId, fecha));
    }
}