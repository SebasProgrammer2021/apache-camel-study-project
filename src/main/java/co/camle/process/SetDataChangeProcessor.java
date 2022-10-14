package co.camle.process;

import co.pojos.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SetDataChangeProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
//        System.out.println("body in = " + exchange.getIn().getBody());
//        exchange.getOut().setBody("Body definido a partir de process");
//        exchange.getOut().setHeader("Header1", "Cabecera definida a partir de process");

//        CLASE 3 OCT 10
        Person person = new Person("Sebastian", 27);
        //exchange.getOut().setBody(person);

        /*
        EJEMPLO ESTABLECER EL OBJ EN LA CABECERA
        */
        exchange.getOut().setHeader("person", person);


    }
}
