package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarCamarero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.CamareroId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Nombre;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Sector;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.IngresarCamareroUseCase;

import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "tengohambrerestaurantbar.factura.ingresarcamarero", aggregate = "factura")
public class IngresarCamareroHandle  extends UseCaseExecutor {
    private RequestCommand<IngresarCamarero> request;

    @Override
    public void run() {
        runUseCase( new IngresarCamareroUseCase(),request);
    }

    @Override
    public void accept(Map<String, String> stringStringMap) {
        var camareroId = Objects.requireNonNull(stringStringMap.get("camareroId"));
        var nombre  = Objects.requireNonNull(stringStringMap.get("nombre"));
        var sector  = Objects.requireNonNull(stringStringMap.get("sector"));
        request =  new RequestCommand<>(new IngresarCamarero(FacturaId.of(aggregateId()),CamareroId.of(camareroId),
                new Nombre(nombre),new Sector(sector)));
    }
}
