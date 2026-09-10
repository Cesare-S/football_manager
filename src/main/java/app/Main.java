package app;

import web.*;
import persistence.ManagerRepository;
import service.RegistrationService;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
    
        ManagerRepository managerRepository = new ManagerRepository();

        RegistrationService registrationService = new RegistrationService(managerRepository);

         TemplateRenderer templateRenderer = new TemplateRenderer();

        WebServer webServer = new WebServer(registrationService, templateRenderer);

       

        webServer.startServer(8080);

       

    }
   

}