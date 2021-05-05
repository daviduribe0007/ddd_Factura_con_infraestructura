package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.Factura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.PropinaCalculada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;

public class CalcularTotalUseCase extends UseCase<TriggeredEvent<PropinaCalculada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<PropinaCalculada> propinaCalculadaTriggeredEvent) {
        var event = propinaCalculadaTriggeredEvent.getDomainEvent();
        var factura = Factura.from(FacturaId.of(event.aggregateRootId()), retrieveEvents());
        var total = new Dinero((int) calcularTotal(factura));

        try {
            factura.calcularTotal(total);
            emit().onResponse((new ResponseEvents(factura.getUncommittedChanges())));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }
    }

    private float calcularTotal(Factura factura) {
        return factura.getSubtotal().value() * (1 + factura.getIva().value()) + factura.getPropina().value();
    }
}