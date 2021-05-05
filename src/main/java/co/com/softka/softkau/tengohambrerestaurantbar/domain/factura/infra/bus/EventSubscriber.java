package co.com.softka.softkau.tengohambrerestaurantbar.domain.factura.infra.bus;

public interface EventSubscriber {
    void subscribe(String eventType, String exchange);
}
