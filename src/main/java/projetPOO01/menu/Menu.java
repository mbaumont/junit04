package projetPOO01.menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import projetPOO01.Enumerations.EPersonne;
import projetPOO01.GestionMenu.GestionFichiers;
import projetPOO01.GestionMenu.GestionsDico;
import projetPOO01.GestionPersonnes.Client;
import projetPOO01.GestionPersonnes.Fournisseur;
import projetPOO01.GestionPersonnes.IClient;
import projetPOO01.GestionPersonnes.IFournisseur;
import projetPOO01.GestionPersonnes.Personne;
import projetPOO01.GestionPersonnes.Salarie;
import projetPOO01.Methodes.Achat;
import projetPOO01.Methodes.Commande;
import projetPOO01.affiche.Affiche;
import projetPOO01.saisie.Controles;

public class Menu {
	
	public static Scanner sc= null;
	public static List<Personne> listPersonne = null;
	public static Boolean Patron = null;
	private static String choix = null;
	private static String choixMenu1 = null;
	public static void quitter(){
		System.exit(0);
	}
	
	/**
	 * Menu principal d'affichage, géré avec une map et des stream suivant le choix d'un numéro
	 * Chaque numéro lance un autre menu
	 */
	public static void afficheMenu() {
		
		System.out.println("Menu Principal");
		System.out.println("-----------------------------------------------");
		System.out.println("Bienvenue dans votre gestionnaire de personnel.\n");
		Map<String,iExecute> menu0 = new HashMap<String,iExecute>();
		menu0.put("Taper 1: Saisie d'un nouveau profil",Menu::afficheMenu1);
		menu0.put("Taper 2: Visualiser les profils existants",Menu::afficheMenu2);
		menu0.put("Taper 3: Gestion Client ",Menu::afficheMenu3);
		menu0.put("Taper 4: Gestion Fournisseurs",Menu::afficheMenu4);
		menu0.put("Taper 5: Sauvegarde du personnel",Menu::afficheMenu5);
		menu0.put("Taper 6: Chargement du personnel",Menu::afficheMenu6);
		menu0.put("Taper 7: Quitter",Menu::quitter);
		menu0.keySet().stream().sorted().forEach(System.out::println);
		String[] listString = {"1","2","3","4","5","6","7"};	
		while(true) {
			choix = Controles.validateAnswer(listString,sc);
			menu0.entrySet().stream().filter(e->e.getKey().charAt(6) == choix.charAt(0))
									 .forEach(e->e.getValue().apply());	
		}
	}
	
	/**
	 * Taper 5:
	 * Sauvegarde de la liste de personnes rentrées grace à un nom choisi par l'utilisateur (sans l'extension)
	 */
	public static void afficheMenu5() {
		String nomFichiersauv;
		System.out.println("Entrez le nom du fichier à sauvegarder:");
		nomFichiersauv = sc.nextLine();
		GestionFichiers.sauvegardeListe(nomFichiersauv);
		Affiche.addTime();				
		afficheMenu();
	}
	/**
	 * Taper 6:
	 * Chargement d'un fichier déjà existant composé d'une liste de personne
	 * L'utilisateur choisi le nom du fichier à charger sans l'extension
	 */
	public static void afficheMenu6() {
		String nomFichierch;
		System.out.println("Entrez le nom du fichier à charger:");
		nomFichierch = sc.nextLine();
		GestionFichiers.chargeListe(nomFichierch);
		Affiche.addTime();
		afficheMenu();
	}

	/**
	 * Taper 2:
	 * Affiche les valeurs de chaque personne et permet de retourner au menu principal ou quitter
	 */
	public static void afficheMenu2() {	
		for (Personne p:listPersonne) {
			System.out.println(p);			
		}	
		System.out.println("-----------------------------------------------");
		System.out.println("Taper entrer pour retourner au menu ou Q pour quitter.");
		String[] listString = {"","Q"};		
		String a = Controles.validateAnswer(listString,sc);
		if (a.equals("")) {
			afficheMenu();			
		}
	}
	
	/**
	 * Taper 3:
	 * Visualisation des différents IClient et choix de celui souhaité pour gérer ses achats
	 * Possibilité de revenir au menu principal via r
	 */
	public static void afficheMenu3() {
		List<String> listNClients  = new ArrayList<String>();
		List<IClient> listClient = new ArrayList<IClient>();
		String choixMenu3;
		listPersonne.stream()
				.filter(p -> p instanceof IClient)
				.forEach(p -> {
					IClient f = (IClient) p;
					if (f.clientOuPas()) {
						listNClients.add(f.afficheNClient());
						System.out.print(f.afficheNClient()+" : Type ");
						System.out.println(f);
						listClient.add(f);}});
		System.out.println("Taper le numéro client pour gérer ses achats, ou r pour retour: ");
		listNClients.add("r");
		String[] arrayClient = listNClients.toArray(new String[0]);
		choixMenu3 = Controles.validateAnswer(arrayClient,sc);
		if (!choixMenu3.equals("r")) {
			int index = listNClients.indexOf(choixMenu3);
			IClient client = listClient.get(index);
			System.out.println("----------------------------");
			System.out.println("Vous avez choisi le client: "+choixMenu3);
			List<Achat> listAchats = new ArrayList<Achat>();
			afficheMenuAchat(client, listAchats);
		}
		else {
			afficheMenu();	
		}
		
	}
	/**
	 * Taper 4:
	 * Gestion des IFournisseurs et choix d'un IFournisseur
	 */
	public static void afficheMenu4() {
		List<String> listNFournisseur  = new ArrayList<String>();
		List<IFournisseur> listFournisseur = new ArrayList<IFournisseur>();
		String choixMenu4;
		for(Personne p:listPersonne) {
			if (p instanceof IFournisseur) {
				IFournisseur f = (IFournisseur) p;
				if (f.fournisseurOuPas()) {
					listNFournisseur.add(f.afficheNFournisseur());
					System.out.print(f.afficheNFournisseur() + " : Type ");
					System.out.println(f);
					listFournisseur.add(f);
				}		
			}
		}
		System.out.println("Taper le numéro fournisseur pour gérer ses commandes, ou r pour retour: ");
		listNFournisseur.add("r");
		String[] arrayFournisseur = listNFournisseur.toArray(new String[0]);
		choixMenu4 = Controles.validateAnswer(arrayFournisseur,sc);
		if (!choixMenu4.equals("r")) {
			int index = listNFournisseur.indexOf(choixMenu4);
			IFournisseur Fournisseur = listFournisseur.get(index);
			System.out.println("----------------------------");
			System.out.println("Vous avez choisi le client: "+choixMenu4);
			List<Commande> listCommandes = new ArrayList<Commande>();
			afficheMenuCommandes(Fournisseur, listCommandes);
		}
		else {
			afficheMenu();	
		}	
	}

	public static <T> void afficheMenuAchat(IClient Client, List<Achat> listAchats) {
		Affiche.afficheMenuCommande("achat");
		String[] listString = {"1","2","3","r"};		
		String a = Controles.validateAnswer(listString,sc);
		switch(a) {
		case "1": 
			Affiche.afficheNouvelAchat(Client,listAchats);
			break;
		case "2":
			Client.achete(listAchats);
			if (!listAchats.isEmpty()) {
				System.out.println("Achat réalisé avec succés!");
				Affiche.addTime();
				
			}
			else System.out.println("Attention aucun achat enregistré.");
			System.out.println("----------------------------");
			afficheMenuAchat(Client,listAchats);
			break;
		case "3":
			for (Achat ach:listAchats) {
				System.out.println(ach.toString());
			}
			System.out.println("Taper r pour retour ou entré pour le menu principal. ");
			String[] listChoixPatron = {"r",""};
			String b = Controles.validateAnswer(listChoixPatron,sc);
			switch(b) {
			case "":
				Menu.afficheMenu();
				break;
			case "r":	
				afficheMenuAchat(Client, listAchats);
				break;
			}
			break;
		default:
			Menu.afficheMenu();			
			break;
		}	
		
	}
	/**
	 * 
	 * @param Fournisseur IFourinsseur
	 * @param listCommandes liste de commandes du IFournisseur
	 */
	public static void afficheMenuCommandes(IFournisseur Fournisseur, List<Commande> listCommandes) {
		Affiche.afficheMenuCommande("commande");
		String[] listString = {"1","2","3","r"};	
		String a = Controles.validateAnswer(listString,sc);
		switch(a) {
		case "1": 
			Affiche.afficheNouvelleCommande(Fournisseur,listCommandes);
			break;
		case "2":
			
			if (!listCommandes.isEmpty()) {
				Fournisseur.commande(listCommandes);
				Fournisseur.livre();
				System.out.println("Commande réalisée avec succés!");
				System.out.println("----------------------------");
				Affiche.addTime();	
				afficheMenu();		
			}
			else {
				System.out.println("Attention aucune commande enregistrée.");
				System.out.println("----------------------------");
				afficheMenuCommandes(Fournisseur,listCommandes);
			}
			
			break;
		case "3":
			for (Commande com:listCommandes) {
				System.out.println(com.toString());
			}
			System.out.println("Taper r pour retour ou entré pour le menu principal. ");
			String[] listChoix = {"r",""};
			String b = Controles.validateAnswer(listChoix,sc);
			switch(b) {
			case "m":
				afficheMenu();
				break;
			case "r":	
				afficheMenuCommandes(Fournisseur, listCommandes);
				break;
			}
			break;
		
		default:
			afficheMenu();			
			break;
		}	
		
	}
	public static void retourMenuPrincipal(Object object, Object object2) {
	System.out.println("Retour au menu principal !");
	afficheMenu();
	}	
	public static void afficheMenu1() {
		Map<String,iExecute> menu1 = new HashMap<String,iExecute>();
		System.out.println("Saisie d'un nouveau profil");
		System.out.println("-----------------------------------------------");	
		System.out.println("Voulez vous saisir un profil salarié, client, fournisseur ou patron?");
		menu1.put("Taper s: Pour salarié",()->Affiche.afficheNouvellePersonne("salarié"));
		menu1.put("Taper c: Pour client",()->projetPOO01.affiche.Affiche.afficheNouvellePersonne("client"));
		menu1.put("Taper f: Pour fournisseur",()->projetPOO01.affiche.Affiche.afficheNouvellePersonne("fournisseur"));
		menu1.put("Taper p: Pour patron",()->projetPOO01.affiche.Affiche.afficheNouvellePersonne("patron"));
		menu1.put("Taper r: Pour retour",()->Menu.retourMenuPrincipal(null,null));
		menu1.keySet().stream().forEach(System.out::println);
		String[] listString = {"s","c","f","p","r"};	
		while(true) {
			choixMenu1 = Controles.validateAnswer(listString,sc);
			menu1.entrySet().stream().filter(e->e.getKey().charAt(6) == choixMenu1.charAt(0))
									 .forEach(e->e.getValue().apply());	
		}
	}


}
