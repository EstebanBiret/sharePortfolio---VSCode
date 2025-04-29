package fr.utc.miage.Acteurs;

public abstract class Personne {

    /**
     * Name of the person
     */
    private String name;

    /**
    * First name of the person
    */
    private String firstName;

    /**
     * Password of the person
     */
    private String password;

    /**
     * Constructor of the Personne class
     * @param nom the name of the person
     * @param prenom the first name of the person
     * @param password the password of the person
     */
    protected Personne(String nom, String prenom, String password) {
        this.name = nom;
        this.firstName = prenom;
        this.password = password;
    }


    /**
     * Allows to consult the name of the person
     * @return the name of the person
     */
    public String getName() {
        return name;
    }
    /**
     * Allows to set the name of the person
     */
    
    public void setName(String nom) {
        this.name = nom;
    }

    /**
     * Allows to consult the first name of the person
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Allows to set the first name of the person
     */
    public void setFirstName(String prenom) {
        this.firstName = prenom;
    }
}
