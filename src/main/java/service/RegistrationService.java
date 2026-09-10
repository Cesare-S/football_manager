package service;

import model.Manager;
import persistence.ManagerRepository;
import java.sql.SQLException;
import java.util.Optional;

public class RegistrationService {

    private final ManagerRepository managerRepository;

    public RegistrationService(ManagerRepository managerRepository){

        this.managerRepository = managerRepository;
    }

    public Manager registerManager(String name, String email, String password) throws SQLException {

        Manager manager = new Manager(name, email, password);

        src.main.java.persistence.ManagerRepository managerRepository = new ManagerRepository();

        Optional checkManager = managerRepository.getByEmail(email);

        if (checkManager.isPresent()){
            throw new SQLException("Errore: email già presente!");
        }

        return managerRepository.saveManager(manager);

    }
    
}
