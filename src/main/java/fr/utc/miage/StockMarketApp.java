package fr.utc.miage;

import java.util.ArrayList;
import java.util.Scanner;

import fr.utc.miage.Acteurs.Gestionnaire;
import fr.utc.miage.Acteurs.Investisseur;
import fr.utc.miage.Acteurs.Personne;
import fr.utc.miage.Market.Marche;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;

public class StockMarketApp {
    public static void main(String[] args) {

        // Initialize the application
        Investisseur investisseur1 = new Investisseur("John", "Doe", "password123", 1000.0f);
        Investisseur investisseur2 = new Investisseur("Jane", "Smith", "password456", 2000.0f);

        Gestionnaire gestionnaire1 = new Gestionnaire("Alice", "Johnson", "admin123");
        Gestionnaire gestionnaire2 = new Gestionnaire("Bob", "Brown", "admin456");

        ArrayList<Personne> personnes = new ArrayList<>();
    
        personnes.add(investisseur1);
        personnes.add(investisseur2);
        personnes.add(gestionnaire1);
        personnes.add(gestionnaire2);

        initializeApplicationActors(gestionnaire1);


        
        System.out.println("Welcome to the Stocks Market Application!");
        System.out.println("Connect with your credentials to start trading.");
        System.out.print("Password : ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        boolean isValidPassword = false;
        Personne connectedPerson = null;

        while (!isValidPassword) {
            for (Personne personne : personnes) {
                if (personne.getPassword().equals(password)) {
                    isValidPassword = true;
                    connectedPerson = personne;
                    break;
                }
            }
            if (!isValidPassword) {
                System.out.println("Invalid password. Please try again.");
                System.out.print("Password : ");
                password = scanner.nextLine();
            }
        }
        System.out.println("Welcome " + connectedPerson.getFirstName() + " " + connectedPerson.getName() + "!");
        if (connectedPerson instanceof Investisseur) {
            System.out.println("Your balance is: " + ((Investisseur) connectedPerson).getBalance());
            menuInvestisseur((Investisseur) connectedPerson);
        } else if (connectedPerson instanceof Gestionnaire) {
            System.out.println("You are a manager.");
            menuGestionnaire((Gestionnaire) connectedPerson);
        }

        scanner.close();


    }

    private static void initializeApplicationActors(Gestionnaire gestionnaire1) {
    
        
        ActionSimple action1 = new ActionSimple("SNCF");
        ActionSimple action2 = new ActionSimple("TF1");
        ActionSimple action3 = new ActionSimple("KFC");
        ActionSimple action4 = new ActionSimple("FlixBus");
        ActionSimple action5 = new ActionSimple("Lenovo");
        ActionSimple action6 = new ActionSimple("Peugot");
        ActionSimple action7 = new ActionSimple("Ricard");

        action1.enrgCours(Jour.getActualJour(), 100.0f);
        action2.enrgCours(Jour.getActualJour(), 200.0f);
        action3.enrgCours(Jour.getActualJour(), 150.0f);
        action4.enrgCours(Jour.getActualJour(), 300.0f);
        action5.enrgCours(Jour.getActualJour(), 50.0f);
        action6.enrgCours(Jour.getActualJour(), 75.0f);
        action7.enrgCours(Jour.getActualJour(), 120.0f);

        gestionnaire1.createAction(action1, 100);
        gestionnaire1.createAction(action2, 200);
        gestionnaire1.createAction(action3, 150);
        gestionnaire1.createAction(action4, 300);
        gestionnaire1.createAction(action5, 50);
        gestionnaire1.createAction(action6, 75);
        gestionnaire1.createAction(action7, 120);

    }

    private static void menuInvestisseur(Investisseur investisseur) {

        char choice = '0';
        Scanner scanner = new Scanner(System.in);
        while (choice != 'q') {
            System.out.println("Menu:");
            System.out.println("1. Buy Action");
            System.out.println("2. Sell Action");
            System.out.println("3. View Portfolio");
            System.out.println("4. View Balance");
            System.out.println("5. View Actions Available");
            System.out.println("q. Quit");

            System.out.print("Enter your choice: ");
            choice = scanner.next().charAt(0);

            switch (choice) {
                case '1':
                    System.out.println("Enter the name of the action you want to buy: ");
                    String actionName = scanner.next();
                    Marche.getActionByLibelle(actionName);
                    if (Marche.getActionByLibelle(actionName) == null) {
                        System.out.println("Action not found. Please try again.");
                        break;
                    }
                    System.out.println("There is " + Marche.getActionsAvailable().get(Marche.getActionByLibelle(actionName)) + " available.");
                    System.out.println("Those are worth " + Marche.getActionByLibelle(actionName).valeur(Jour.getActualJour()) + " each.");
                    System.out.println("Enter the quantity you want to buy: ");
                    int quantity = scanner.nextInt();
                    System.out.println("Total: " + (Marche.getActionByLibelle(actionName).valeur(Jour.getActualJour()) * quantity) + ".");
                    System.out.println("Do you want to proceed? (y/n)");
                    char proceed = scanner.next().charAt(0);
                    if (proceed == 'y') {
                        if (investisseur.buyActionSimple((ActionSimple) Marche.getActionByLibelle(actionName), quantity)) {
                            System.out.println("Purchase successful!\n");
                        } else {
                            System.out.println("Purchase failed. Please check your balance or the action availability.\n");
                        }
                    } else {
                        System.out.println("Purchase cancelled.\n");
                    }

                    break;
                case '2':
                    System.out.println("Enter the name of the action you want to sell: ");
                    String actionNameToSell = scanner.next();
                    System.out.println("Enter the quantity you want to sell: ");
                    System.out.println("Those are worth " + Marche.getActionByLibelle(actionNameToSell).valeur(Jour.getActualJour()) + " each.");
                    System.out.println("You own " + investisseur.getWallet().getActions().get(Marche.getActionByLibelle(actionNameToSell)) + " of them.");
                    int quantityToSell = scanner.nextInt();
                    System.out.println("Total: " + (Marche.getActionByLibelle(actionNameToSell).valeur(Jour.getActualJour()) * quantityToSell) + ".");
                    System.out.println("Do you want to proceed? (y/n)");
                    char proceedSell = scanner.next().charAt(0);
                    if (proceedSell == 'y') {
                        if (investisseur.sellActionSimple((ActionSimple) Marche.getActionByLibelle(actionNameToSell), quantityToSell)) {
                            System.out.println("Sale successful!\n");
                        } else {
                            System.out.println("Sale failed. Please check your balance or the action availability.\n");
                        }
                    } else {
                        System.out.println("Sale cancelled.\n");
                    }
                    break;
                case '3':
                    System.out.println("Your portfolio: ");
                    investisseur.getWallet().getActions().forEach((action, quantityInWallet) -> {
                        System.out.println(action.getLibelle() + ": " + quantityInWallet);
                        System.out.println("Value: " + action.valeur(Jour.getActualJour()) * quantityInWallet);
                    });
                    System.out.println("Total value: " + investisseur.getTotalValueOfWallet()+".\n");
                    break;
                case '4':
                    System.out.println("Your balance: " + investisseur.getBalance() + ".\n");
                    break;
                case '5':
                    System.out.println("Actions available on the market: ");
                    Marche.getActionsAvailable().forEach((action, quantities) -> {
                        System.out.println(action.getLibelle() + ": " + quantities + " available.");
                        System.out.println("Value: " + action.valeur(Jour.getActualJour()) * quantities);
                    });
                    System.out.println();
                    break;
                case 'q':
                    System.out.println("Exiting the application.\n");
                    break; 
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    
    }
    private static void menuGestionnaire(Gestionnaire gestionnaire) {
    }

}
