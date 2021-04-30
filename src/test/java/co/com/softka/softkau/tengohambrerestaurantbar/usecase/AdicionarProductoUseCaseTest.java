package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.AdicionarProducto;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoAdicionado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarProductoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void ingresarProducto() {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var productoId = ProductoId.of("11");
        var descripcion = new Descripcion("Sancocho trifasico");
        var precio = new Dinero(35000);
        var command = new AdicionarProducto(facturaId, productoId, descripcion, precio);
        var useCase = new AdicionarProductoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId, fecha));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ProductoAdicionado) events.get(0);

        Mockito.verify(repository).getEventsBy(facturaId.value());
        Assertions.assertEquals("11", event.getProductoId().value());
        Assertions.assertEquals("Sancocho trifasico", event.getDescripcion().value());
        Assertions.assertEquals(35000, event.getPrecio().value());
    }


    @Test
    void ingresarProductoValorNegativo() {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var productoId = ProductoId.of("11");
        var descripcion = new Descripcion("Sancocho trifasico");
        var precio = new Dinero(-35000);
        var command = new AdicionarProducto(facturaId, productoId, descripcion, precio);
        var useCase = new AdicionarProductoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId, fecha));
        useCase.addRepository(repository);

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(facturaId.value())
                    .syncExecutor(useCase, new RequestCommand<>(command))
                    .orElseThrow();
        });

    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {
        return List.of(new FacturaCreada(facturaId, fecha));
    }
}