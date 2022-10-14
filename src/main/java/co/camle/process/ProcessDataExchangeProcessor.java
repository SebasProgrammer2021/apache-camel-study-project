package co.camle.process;

import co.pojos.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessDataExchangeProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("2. body in  process file ");
//        System.out.println("2. cabecera1 file " + exchange.getIn().getHeader("Header1"));

//         clase 3 oct 10
//        es una mala practica hacer el casteo explicito de los metodoso que estamos recibiendo
        // Person person = (Person) exchange.getIn().getBody(); //cast de exchange a person. porque generaba error sin el cast

        //mejor practica
        /*le estamos diciendo qu eintente parseaer ese body quqe llega  ana instancia de tipo
        perso*/
//        Person person = (Person) exchange.getIn().getBody(Person.class);
//        if (person != null) System.out.println("Person name: " + person.getName());
//        else System.out.println("Person name is NULL ");

        /*
        EJEMPLO RECIBIR EL OBJ EN LA CABECERA
        */
        Person person = (Person) exchange.getIn().getHeader("person", Person.class);
        if (person != null) System.out.println("Person name: " + person.getName());
        else System.out.println("Person name is NULL ");

    }
}
