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
        //se configura el inicio del flujo añadiendo una ruta y su codigo dentro de la clase MyRouteBuilder
        main.configure().addRoutesBuilder(new MyRouteBuilder());
        main.run(args);
    }

}

