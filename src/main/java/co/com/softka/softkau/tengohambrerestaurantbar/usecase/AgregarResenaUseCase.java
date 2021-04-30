package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.Factura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.AgregarResena;

public class AgregarResenaUseCase extends UseCase<RequestCommand<AgregarResena>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarResena> agregarResenaRequestCommand) {
        var command = agregarResenaRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        try {
            factura.agregarResena(command.getResena());
            emit().onResponse((new ResponseEvents(factura.getUncommittedChanges())));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }

    }
}
