package model;

public class Manager{

    private Long id;
    private String name;
    private String email;
    private String password;
    private long budgetEur;

    public Manager(Long dbId, String dbName, String dbEmail, String dbPassword, long dbBudgetEur) {
        
        this(dbName, dbEmail, dbPassword);
        if (dbId == null) {
            throw new IllegalArgumentException("Id non può essere vuoto.");
        }

        this.id = dbId;
        this.budgetEur = dbBudgetEur;

    }

    public Manager(String formName, String formEmail, String formPassword) {

        
        if (formName == null || formName.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto.");
        }

        if (formEmail == null || formEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email non può essere vuoto.");
        }

        if (formPassword == null || formPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password non può essere vuoto.");
        }

        this.name = formName;
        this.email = formEmail;
        this.password = formPassword;
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public long getBudgetEur(){
        return budgetEur;
    }


}