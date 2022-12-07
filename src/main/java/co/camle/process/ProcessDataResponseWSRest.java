package co.camle.process;

import co.pojos.DataQuote;
import org.apache.camel.Exchange;

public class ProcessDataResponseWSRest implements org.apache.camel.Processor {

    /**
     * @param exchange
     * @throws Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        String response = exchange.getIn().getBody(String.class);
        System.out.println("Body response = " + response);
//        DataQuote dataQuote = exchange.getIn().getBody(DataQuote.class);
//        if(dataQuote != null) System.out.println("here is your data quote");
//        else System.out.println("no data quote");
    }

}
