package co.camel;

import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main(); //es un main de camel,
        main.configure().addRoutesBuilder(new MyRouteBuilder());
        main.run(args);
    }

}

