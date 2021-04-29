package co.com.softka.softkau.tengohambrerestaurantbar.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.Factura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarCamarero;

public class IngresarCamareroUseCase extends UseCase<RequestCommand<IngresarCamarero>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<IngresarCamarero> ingresarCamareroRequestCommand) {
        var command = ingresarCamareroRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(),retrieveEvents());

        try{
            factura.ingresarCamarero(command.getCamareroId(),command.getNombre(),command.getSector());
            emit().onResponse((new ResponseEvents(factura.getUncommittedChanges())));
        }catch(RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }
    }
}
