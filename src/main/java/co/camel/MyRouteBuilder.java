package co.camel;

import co.camle.process.ProcessDataExchangeProcessor;
import co.camle.process.ProcessDataResponseWSRest;
import co.pojos.DataQuote;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {
    /*
     * EJEMPLO NUEVA RUTA CLASE 4, CONSUMO API REST SIMPSONS
     */
//    para parsear la respuesta string a obj java
    private JacksonDataFormat jDataFormat;

    public MyRouteBuilder() {
        jDataFormat = new JacksonDataFormat(DataQuote.class);
    }
    /*EJEMPLO NUEVA RUTA CLASE 4, FIN*/


    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
//        from("file:src/data?noop=true") //RUTA
//            .choice()
//                .when(xpath("/person/city = 'London'"))
//                    .log("UK message")
//                    .to("file:target/messages/uk")
//                .otherwise()
//                    .log("Other message")
//                    .to("file:target/messages/others");
        from("timer:simple?period=3000") //RUTA- utiliza el componente timer de camel para una ejecucion periodica de un código.
                //.log("disparando procesamiento.")

                /*
                FORMA 1
                 */
//                .setHeader("Header1", constant("valor cabecera 1"))//los headers se definen de tipo clave v valor
//                .setBody(constant("Mensaje a procesar")) //constant: se uda para tener cadenas constantes o constantes dentro de la def de las rutas


                /*
                FORMA 2 PROCESOS
                me permite definir procesamientos de datos
                 */

                /*
               FORMA 1 PROCESS
                */
                //                .process(new Processor() { //se utiliza la interfaz processor
                //                    @Override
                //                    public void process(Exchange exchange) throws Exception { //recibe un arg exchange, el exchange de apache camel
                //                        //obteiene el body del mensaje de entrada
                //                        System.out.println("body in = " + exchange.getIn().getBody());
                //
                //                        //puedo establecer el body en el mensaje de entrada o de salida
                //                        //establecimiento en el mensaje de salid
                //                        exchange.getOut().setBody("Body definido a partir de process");
                //                        exchange.getOut().setHeader("Header1","Cabecera definida a partir de process");
                //
                //
                //                    }
                //                })

                /*
                    FORMA 2 SINTAXIS PROCESS, mas legible y menos lineas
                */
                /*
                lo ideal es separar el procesamiento de datos con la definicion de los flujos
                 */
//                .process((exchange -> {  //sintaxis lambda
//                    System.out.println("body in = " + exchange.getIn().getBody());
//                    exchange.getOut().setBody("Body definido a partir de process");
//                    exchange.getOut().setHeader("Header1", "Cabecera definida a partir de process");
//                }))

//                OCT 10 - VIDEO 3
//                se separa el procesamiento en un archivo aparte
//                .process(new SetDataExchangeProcessor())
//                .to("direct:procesarMensaje") //para disparar o activar la ruta que se creo

                /*
                 * EJEMPLO NUEVA RUTA CLASE 4, CONSUMO API REST SIMPSONS
                 */
                .to("direct:consumirWSRest")
                .end();

        //direct(componente): define rutas directas, rutas que van a ser llamadas desde otros puntos
        from("direct:procesarMensaje").log("inicio procesamiento mensaje")
//                impresion de otra manera - emeplo en ProcessDataExchangeProcessor
//                .log("Body= ${body}")//para acceder a headers o body, con notacion similar a string template
//                .log("Cabecera= ${header.header1}")

                //implementación separando archivos
                .process(new ProcessDataExchangeProcessor()).end();

        /*
        EJEMPLO NUEVA RUTA CLASE 4, CONSUMO API REST SIMPSONS
         */
        from("direct:consumirWSRest")
                //cuando es por get no es necesario especificar el tipo de metodo. lo toma por defecto
                /**
                 * para otros tipos de solicitud se debe usar lo siguiente
                 * ("CamelHttpMethod" esto se puede definir en una constante=HTTP_METHOD = "CamelHttpMethod")
                 * .setHeader(Exchange.HTTP_METHOD, constant('PUT');
                 */
                .to("https://thesimpsonsquoteapi.glitch.me/quotes?count=20")
                .log("Response ws rest simspons : ${body}")
//                .unmarshal(jDataFormat)
                .process(new ProcessDataResponseWSRest())
                .end();

    }
}
