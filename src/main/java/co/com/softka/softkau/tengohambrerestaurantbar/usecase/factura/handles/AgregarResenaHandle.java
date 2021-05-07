package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.handles;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.AgregarResena;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarCamarero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.AgregarResenaUseCase;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.IngresarCamareroUseCase;

import java.util.Map;
import java.util.Objects;


@CommandHandles
@CommandType(name = "tengohambrerestaurantbar.factura.resenaagregada", aggregate = "factura")
public class AgregarResenaHandle  extends UseCaseExecutor {
    private RequestCommand<AgregarResena> request;

    @Override
    public void run() {
        runUseCase( new AgregarResenaUseCase(),request);
    }

    @Override
    public void accept(Map<String, String> stringStringMap) {
        var resena = Objects.requireNonNull(stringStringMap.get("resena"));
        request =  new RequestCommand<>(new AgregarResena(FacturaId.of(aggregateId()),
                new Resena(resena)));
    }
}