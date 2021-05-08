# TENGO_HAMBRE_RESTAURANT_BAR

##Problema##

El restaurante TengoHambreRestaurantBar con la finalidad de prestar un mejor servicio y atención a sus consumidores desea tener un sistema que le permita generar la factura por el consumo de sus productos,con esto se aclararía mucho más al cliente sobre los costos que tuvo por producto consumido, el camarero que lo atendió , el subtotal, el costo de propina y su total calculado con el iva.

Al finalizar la compra, se adiciona una reseña para mejorar el servicio prestado, en una misma factura se pueden adicionar varios productos ya sea comida bebidas dulces tragos, entre otros.

Si por alguna razón se adicionan productos que el cliente no consumió o se cometió un error, este puede ser retirado de la factura.

El iva que manejara la factura por las nuevas leyes impuestas sera de un 10% este se calcula sobre el total del precio de los productos consumidos.

La propina es obligatoria y es un 5 % del total consumido sin el impuesto.

Del consumidor se requieren la cedula, el nombre, numero celular y su correo ya que este en un futuro nos podría ser muy útil.

Del camarero el sector que estaba atendiendo y su nombre.

El diagrama esta en el siguiente link: https://drive.google.com/file/d/1A2b6XTVOeupWcvnz6Ncb0hMmz_Sa30bM/view?usp=sharing

El orden de la app es 

Crear factura -> Agregar camarero -> agregar cliente -> agregar/remover productos->
Agregar reseña Este dispara -> calcular propina este dispara -> Calcular total

para correr el Rabbitmq en docker con el comando (docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management)
para correr el mongo se debe crear en el c:/ una carpeta llamada dockerdata y ejecutar este comando en cmd
docker (docker run -d -p 27017:27017 --name mongodb -v c:/dockerdata/mongodata:/data/db mongo) con este comando logramos persistencia

Este programa hace persistencia de eventos y comandos.


# CHALLENGE - HACIENDO DDD DESDE CERO #

## Summary ##

En este reto vamos a poner en practica las aspectos fundamentales de DDD aplicando todo el conocimiento que se a trabajado dentro del curso de DDD y las mentorías. Aquí se espera dar solución aplicando a las capas de dominio (aggregate) y business (use case).

  


Recuerde usar la librería de [https://github.com/Sofka-XT/ddd-generic-java ][https_github.com_Sofka-XT_ddd-generic-java]y puedes usar el siguiente ejemplo como base:

[https://github.com/Sofka-XT/java-ddd-demo-dice][https_github.com_Sofka-XT_java-ddd-demo-dice]

## Use Case/Problem ##

Con base al conocimiento adquirido sobre DDD poner en evidencia el conocimiento y dominio adquirido de esta temática, para ello realizaremos lo siguiente:

1.  Se debe definir un problema cualquiera sobre el cual se planteara un Modelo de Dominio .
2.  De manera individual se dará respuesta a la solución planteada expresada en el Modelo de Dominio y la librería de DDD.

  


Se deben tener en cuenta la siguientes directrices.

1.  Se debe tener mínimo 1 agregado como mínimo
2.  Se debe tener mínimo 3 entidades como mínimo
3.  Se debe tener mínimo 8 objetos de valor como mínimo
4.  Se debe tener mínimo 7 comportamientos con sus respectivos eventos de dominio y/o comandos
5.  Se debe tener mínimo 5 casos de uso disparados por comandos
6.  Se debe tener mínimo 2 casos de uso disparados por eventos

  


  


**IMPORTENTE**\: Se debe tener un cubrimiento de pruebas unitarias para todos los casos de uso planteados, si no se hace pruebas unitarias al caso de uso se considera que no finalizo dicho caso de uso.

## Evaluation criteria ##

| Criteria                                                                                       | Percentage |
| ---------------------------------------------------------------------------------------------- | ---------- |
| Implementa los casos 7 casos de uso según las directrices descritas en el problema del reto    | 35.0 %     |
| Implementa el modelo de dominio con un agregado y todos sus comportamientos, eventos y objetos | 50.0 %     |
| Implementar pruebas unitarias que cubran todos los casos de uso                                | 15.0 %     |


[https_github.com_Sofka-XT_ddd-generic-java]: https://github.com/Sofka-XT/ddd-generic-java
[https_github.com_Sofka-XT_java-ddd-demo-dice]: https://github.com/Sofka-XT/java-ddd-demo-dice
