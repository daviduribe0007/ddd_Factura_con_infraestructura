package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.handles;

import co.com.sofka.business.annotation.CommandHandles;
        import co.com.sofka.business.annotation.CommandType;
        import co.com.sofka.business.asyn.UseCaseExecutor;
        import co.com.sofka.business.support.RequestCommand;
        import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarCamarero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarConsumidor;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.IngresarCamareroUseCase;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.IngresarConsumidorUseCase;

import java.util.Map;
        import java.util.Objects;

@CommandHandles
@CommandType(name = "tengohambrerestaurantbar.factura.consumidoringresado", aggregate = "factura")
public class IngresarConsumidorHandle  extends UseCaseExecutor {
    private RequestCommand<IngresarConsumidor> request;

    @Override
    public void run() {
        runUseCase( new IngresarConsumidorUseCase() ,request);
    }

    @Override
    public void accept(Map<String, String> stringStringMap) {
        var consumidorId = Objects.requireNonNull(stringStringMap.get("consumidorId"));
        var nombre  = Objects.requireNonNull(stringStringMap.get("nombre"));
        var celular  = Objects.requireNonNull(stringStringMap.get("celular"));
        var correo  = Objects.requireNonNull(stringStringMap.get("correo"));
        request =  new RequestCommand<>(new IngresarConsumidor(FacturaId.of(aggregateId()),
                ConsumidorId.of(consumidorId),
                new Nombre(nombre),
                new Celular(celular),
                new Correo(correo)
                ));
    }
}

