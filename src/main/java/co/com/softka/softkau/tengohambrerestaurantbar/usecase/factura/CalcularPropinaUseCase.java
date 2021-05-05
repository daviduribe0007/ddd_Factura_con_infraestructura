package co.com.softka.softkau.tengohambrerestaurantbar.usecase.factura;

import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.Factura;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.events.ResenaAgregada;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.Dinero;
import co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.values.FacturaId;

@EventListener(eventType = "tengohambrerestaurantbar.factura.calcularpropina")
public class CalcularPropinaUseCase extends UseCase<TriggeredEvent<ResenaAgregada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<ResenaAgregada> resenaAgregadaTriggeredEvent) {
        var event = resenaAgregadaTriggeredEvent.getDomainEvent();
        var factura = Factura.from(FacturaId.of(event.aggregateRootId()), retrieveEvents());
        var propina = new Dinero((int) calcularPropina(factura));

        try {
            factura.calcularPropina(propina);
            emit().onResponse((new ResponseEvents(factura.getUncommittedChanges())));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }
    }

    private float calcularPropina(Factura factura) {
        return factura.getSubtotal().value() * 0.05f;
    }
}
