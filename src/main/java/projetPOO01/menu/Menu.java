package projetPOO01.menu;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import projetPOO01.Programme;
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
	
	public static void afficheMenuAchat(IClient Client, List<Achat> listAchats) {
		System.out.println("Saisie des achats");
		System.out.println("----------------------------");
		
		System.out.println("Taper 1: Saisie d'un nouvel achat");
		System.out.println("Taper 2: Achat des articles");
		System.out.println("Taper 3: Visulaliser les achats");
		System.out.println("Taper r: Retour");
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				
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
	
	public static void afficheMenu() {
		String a;
		System.out.println("Menu Principal");
		System.out.println("-----------------------------------------------");
		System.out.println("Bienvenue dans votre gestionnaire de personnel.\n");
		System.out.println("Taper 1: Saisie d'un nouveau profil");
		System.out.println("Taper 2: Visualiser les profils existants");
		System.out.println("Taper 3: Gestion Client ");
		System.out.println("Taper 4: Gestion Fournisseurs");
		System.out.println("Taper 5: Sauvegarde du personnel");
		System.out.println("Taper 6: Chargement du personnel");
		
		
		String[] listString = {"1","2","3","4","5","6"};		
		a = Controles.validateAnswer(listString,sc);
		switch(a) {
		case "1": 
			Menu.afficheMenu1();
			break;
		case "2":
			Menu.afficheMenu2();
			break;
		case "3":
			afficheMenu3();
			break;
		case "4":
			afficheMenu4();
			break;
		case "5":
			String nomFichiersauv;
			System.out.println("Entrez le nom du fichier à sauvegarder:");
			nomFichiersauv = sc.nextLine();
			GestionFichiers.sauvegardeListe(nomFichiersauv);
			Programme.addTime();				
			afficheMenu();
			break;
		case "6":
			String nomFichierch;
			System.out.println("Entrez le nom du fichier à charger:");
			nomFichierch = sc.nextLine();
			GestionFichiers.chargeListe(nomFichierch);
			Programme.addTime();
			afficheMenu();
			break;
		default:
			afficheMenu();			
			break;
		}	
	}
	
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
	
	public static void afficheMenu3() {
		List<String> listNClients  = new ArrayList<String>();
		List<IClient> listClient = new ArrayList<IClient>();
		String choixMenu3;
		for(Personne p:listPersonne) {
			if (p instanceof IClient) {
				IClient f = (IClient) p;
				if (f.clientOuPas()) {
					listNClients.add(f.afficheNClient());
					System.out.print(f.afficheNClient()+" : Type");
					System.out.println(f);
					listClient.add(f);
				}		
			}
		}
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
	public static void afficheMenuCommandes(IFournisseur Fournisseur, List<Commande> listCommandes) {
		System.out.println("Saisie des commandes");
		System.out.println("----------------------------");
		
		System.out.println("Taper 1: Saisie d'une nouvelle commande");
		System.out.println("Taper 2: Commande des articles");
		System.out.println("Taper 3: Visulaliser les commande");
		System.out.println("Taper r: Retour");
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
				Programme.addTime();	
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
	
	public static void afficheMenu1() {
		String choixMenu1;
		System.out.println("Saisie d'un nouveau profil");
		System.out.println("-----------------------------------------------");
		System.out.println("Voulez vous saisir un profil salarié, client, fournisseur ou patron?");
		System.out.println("Taper s pour salarié, c pour client, f pour fournisseur, p pour patron ou r pour retourner au menu princpal: \n ");
		String[] listString = {"s","c","f","p","r"};
		choixMenu1 = Controles.validateAnswer(listString,sc);

		Dictionary<EPersonne, String> dico = new Hashtable<EPersonne, String>();		
		switch(choixMenu1) {
		case "s": 
			System.out.println("Vous avez choisi le profil salarié.");
			System.out.println("-----------------------------------------------");
			GestionsDico.initDicoPersonne(dico, choixMenu1);
			GestionsDico.initToutesPersonne(dico);		

			Personne p = new Salarie(dico); 
			listPersonne.add(p);
			System.out.println("Salarié ajouté. \n");
			Programme.addTime();
			afficheMenu1();
			break;

		case "c":
			System.out.println("Vous avez choisi le profil client.");
			System.out.println("-----------------------------------------------");
			GestionsDico.initDicoPersonne(dico, choixMenu1);
			GestionsDico.initToutesPersonne(dico);		
			Personne p1 = new Client(dico);
			listPersonne.add(p1);
			System.out.println("Client ajouté. \n ");
			Programme.addTime();
			afficheMenu1();
			break;
		case "f":
			System.out.println("Vous avez choisi le profil fournisseur.");
			System.out.println("----------------------------------------------- \n");
			GestionsDico.initDicoPersonne(dico, choixMenu1);			
			GestionsDico.initToutesPersonne(dico);
			Personne p2 = new Fournisseur(dico);
			System.out.println(dico);
			listPersonne.add(p2);
			System.out.println(p2);
			System.out.println("Fournisseur ajouté. \n");
			Programme.addTime();
			afficheMenu1();
			break;
		case "p":
			System.out.println("Vous avez choisi le profil patron.");
			System.out.println("----------------------------------------------- \n");
			if (Patron) {
				System.out.println("Attention vous avez déjé ajouté un patron, si vous continuez vous allez le supprimer.\"");
				System.out.println("Taper r pour retourner ou c pour continuer:");
				String[] listChoixPatron = {"r","c"};
				String b = Controles.validateAnswer(listChoixPatron,sc);
				switch(b) {
				case "r":
					GestionsDico.deletePatron();
					GestionsDico.addPatron(dico);
					Programme.addTime();
					afficheMenu1();
					break;
					
				case "c":
					afficheMenu();
					break;
				default:
					break;
				}		
				break;
			}
			else {
				GestionsDico.addPatron(dico);
				System.out.println("Patron ajouté, retour au menu principal. \n ");		
				afficheMenu1();
				break;
			}
			
		case "r":
			System.out.println("Retour au menu principal !");
			afficheMenu();
			break;	
		}				
	}

}
