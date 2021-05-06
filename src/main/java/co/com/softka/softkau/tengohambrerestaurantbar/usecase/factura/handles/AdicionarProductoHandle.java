package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.handles;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.AdicionarProducto;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.IngresarCamarero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.*;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.AdicionarProductoUseCase;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.IngresarCamareroUseCase;

import java.util.Map;
import java.util.Objects;



@CommandHandles
@CommandType(name = "tengohambrerestaurantbar.factura.productoadicionado", aggregate = "factura")
public class AdicionarProductoHandle  extends UseCaseExecutor {
    private RequestCommand<AdicionarProducto> request;

    @Override
    public void run() {
        runUseCase( new AdicionarProductoUseCase(),request);
    }

    @Override
    public void accept(Map<String, String> stringStringMap) {
        var productoId = Objects.requireNonNull(stringStringMap.get("productoId"));
        var descripcion  = Objects.requireNonNull(stringStringMap.get("descripcion"));
        var precio  = Integer.parseInt(Objects.requireNonNull(stringStringMap.get("precio")));
        request =  new RequestCommand<>(new AdicionarProducto(FacturaId.of(aggregateId()),
                ProductoId.of(productoId),
                new Descripcion(descripcion),
                new Dinero(precio)));
    }
}
