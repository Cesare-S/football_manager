package app;

import controller.RegistrationController;
import web.*;
import persistence.ManagerRepository;
import service.RegistrationService;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
    
        ManagerRepository managerRepository = new ManagerRepository();

        RegistrationService registrationService = new RegistrationService(managerRepository);

         TemplateRenderer templateRenderer = new TemplateRenderer();

        // Creazione e collegamento del controller di registrazione scritti dall'agente AI su richiesta dell'utente.
        RegistrationController registrationController = new RegistrationController(registrationService, templateRenderer);

        WebServer webServer = new WebServer(registrationService, templateRenderer, registrationController);

       

        webServer.startServer(8080);

       

    }
   

}
