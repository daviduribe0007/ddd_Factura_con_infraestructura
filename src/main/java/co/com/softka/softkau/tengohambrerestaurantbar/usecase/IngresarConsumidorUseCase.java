package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.Factura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarConsumidor;

public class IngresarConsumidorUseCase extends UseCase<RequestCommand<IngresarConsumidor>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<IngresarConsumidor> ingresarConsumidorRequestCommand) {
        var command = ingresarConsumidorRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        try {
            factura.ingresarConsumidor(command.getConsumidorId(), command.getNombre(), command.getCelular(),
                    command.getCorreo());
            emit().onResponse((new ResponseEvents(factura.getUncommittedChanges())));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }
    }
}
