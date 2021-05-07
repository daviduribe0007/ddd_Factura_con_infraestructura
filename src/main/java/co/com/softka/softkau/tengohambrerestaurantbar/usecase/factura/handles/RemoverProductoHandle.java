package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.handles;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.commands.RemoverProducto;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.ProductoId;
import co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura.RemoverProductoUseCase;

import java.util.Map;
import java.util.Objects;


@CommandHandles
@CommandType(name = "tengohambrerestaurantbar.factura.productoremovido", aggregate = "factura")
public class RemoverProductoHandle extends UseCaseExecutor {
    private RequestCommand<RemoverProducto> request;

    @Override
    public void run() {
        runUseCase(new RemoverProductoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> stringStringMap) {
        var id = Objects.requireNonNull(ProductoId.of(stringStringMap.get("productoId")));

        request = new RequestCommand<>(new RemoverProducto(FacturaId.of(aggregateId()), id));
    }
}