package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.Factura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.RemoverProducto;

public class RemoverProductoUseCase extends UseCase<RequestCommand<RemoverProducto>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<RemoverProducto> removerProductoRequestCommand) {
        var command = removerProductoRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());
        try {
            factura.retirarProducto(command.getProductoId());
            emit().onResponse((new ResponseEvents(factura.getUncommittedChanges())));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }
    }
}




