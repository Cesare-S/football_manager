package app;

import web.WebServer;
import persistence.ManagerRepository;
import service.RegistrationService;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
    
        ManagerRepository managerRepository = new ManagerRepository();

        RegistrationService registrationService = new RegistrationService(managerRepository);

        WebServer webServer = new WebServer(registrationService);

        WebServer.startServer(8080);

       

    }
   

}