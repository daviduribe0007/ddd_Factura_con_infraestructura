package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.RemoverProducto;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.FacturaCreada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoAdicionado;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ProductoRemovido;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoverProductoUseCaseTest {


    @Mock
    private DomainEventRepository repository;

    @Test
    void removerProducto() {
        var facturaId = FacturaId.of("1");
        var fecha = new Fecha("2021,04,28");
        var productoId = ProductoId.of("11");

        var command = new RemoverProducto(facturaId,productoId);
        var useCase = new RemoverProductoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId, fecha, productoId));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ProductoRemovido) events.get(0);

        Mockito.verify(repository).getEventsBy(facturaId.value());
        Assertions.assertEquals("11", event.getProductoId().value());

    }
    private List<DomainEvent> eventStored (FacturaId facturaId, Fecha fecha,ProductoId productoId){
        var descripcion = new Descripcion("Sancocho trifasico");
        var precio = new Dinero(35000);
        return List.of(new FacturaCreada(facturaId, fecha),
        new ProductoAdicionado(productoId,descripcion,precio));
    }

}