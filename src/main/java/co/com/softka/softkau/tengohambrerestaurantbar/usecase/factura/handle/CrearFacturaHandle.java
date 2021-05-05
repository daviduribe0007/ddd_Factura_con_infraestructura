package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.CrearFactura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Fecha;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.CrearFacturaUseCase;

import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "tengohambrerestaurantbar.factura.facturacreada", aggregate = "factura")
public class CrearFacturaHandle extends UseCaseExecutor {
    private RequestCommand<CrearFactura> request;

    @Override
    public void run() {
        runUseCase(new CrearFacturaUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> stringStringMap) {
        var fecha = Objects.requireNonNull(stringStringMap.get("fecha"));

        request = new RequestCommand<>(new CrearFactura(FacturaId.of(aggregateId()), new Fecha(fecha)));
    }
}
