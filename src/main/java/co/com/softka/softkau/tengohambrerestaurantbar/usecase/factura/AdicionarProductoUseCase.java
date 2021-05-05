package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.Factura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.AdicionarProducto;

public class AdicionarProductoUseCase extends UseCase<RequestCommand<AdicionarProducto>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AdicionarProducto> adicionarProductoRequestCommand) {
        var command = adicionarProductoRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        if (command.getPrecio().value() <= 0) {
            throw new BusinessException(factura.identity().value(), "No se puede Ingresar valores negativos" +
                    " o iguales a 0");
        }

        try {
            factura.adicionarProducto(command.getProductoId(),
                    command.getDescripcion(),
                    command.getPrecio());
            emit().onResponse((new ResponseEvents(factura.getUncommittedChanges())));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }
    }
}
