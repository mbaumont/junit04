package projetPOO01.affiche;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import projetPOO01.Enumerations.EPersonne;
import projetPOO01.GestionMenu.GestionsDico;
import projetPOO01.GestionPersonnes.Client;
import projetPOO01.GestionPersonnes.Fournisseur;
import projetPOO01.GestionPersonnes.IClient;
import projetPOO01.GestionPersonnes.IFournisseur;
import projetPOO01.GestionPersonnes.Personne;
import projetPOO01.GestionPersonnes.Salarie;
import projetPOO01.Methodes.Achat;
import projetPOO01.Methodes.Commande;
import projetPOO01.menu.Menu;
import projetPOO01.saisie.Controles;

public class Affiche {

	public static void afficheNouvelleCommande(IFournisseur Fournisseur,List<Commande> listCommandes) {
		Commande commande = Controles.rentrerAchatOuCommande(Menu.sc,"commande");
		listCommandes.add(commande);
		System.out.println("Taper 1: Nouvelle commande");
		System.out.println("Taper r: Retour");
		String[] listString2 = {"1","r"};	
		String b = Controles.validateAnswer(listString2,Menu.sc);
		switch(b) {
		case "1":
			afficheNouvelleCommande(Fournisseur,listCommandes);
			break;
		case "r":	
			Menu.afficheMenuCommandes(Fournisseur, listCommandes);
			break;
		}	
	}
	
	public static void afficheNouvelAchat(IClient client,List<Achat> listAchats) {
		Achat achat = Controles.rentrerAchatOuCommande(Menu.sc,"achat");
		listAchats.add(achat);
		System.out.println("Taper 1: Nouvel achat");
		System.out.println("Taper r: Retour");
		String[] listString2 = {"1","r"};	
		String b = Controles.validateAnswer(listString2,Menu.sc);
		switch(b) {
		case "1":
			afficheNouvelAchat(client,listAchats);
			break;
		case "r":	
			Menu.afficheMenuAchat(client, listAchats);
			break;
		}	
	}

	public static void afficheNouvellePersonne(String personne) {
		System.out.println("Vous avez choisi le profil "+personne+" .");
		System.out.println("-----------------------------------------------");
		Map<EPersonne, String> dico = new Hashtable<EPersonne, String>();
		System.out.println(personne.charAt(0));
		dico = GestionsDico.initDicoPersonne(personne.charAt(0));	
		switch(personne) {
		case "salarié":
			Personne p = new Salarie(dico);
			Menu.listPersonne.add(p);
			break;		
		case "client":
			Personne p1 = new Client(dico);
			Menu.listPersonne.add(p1);
			break;
		case "fournisseur":
			Personne p2 = new Fournisseur(dico);
			Menu.listPersonne.add(p2);
			break;
		case "patron":
			if (Menu.Patron) {
				System.out.println("Attention vous avez déjà ajouté un patron, si vous continuez vous allez le supprimer.\"");
				System.out.println("Taper r pour retourner ou c pour continuer:");
				String[] listChoixPatron = {"r","c"};
				String b = Controles.validateAnswer(listChoixPatron,Menu.sc);
				switch(b) {
				case "r":
					GestionsDico.deletePatron();
					GestionsDico.addPatron(dico);
					Affiche.addTime();
					Menu.afficheMenu1();
					return;
					
				case "c":
					Menu.afficheMenu();
					return;
				}		
			}
			else {
				GestionsDico.addPatron(dico);	
				break;
			}
		}
		
		System.out.println(personne.substring(0, 1).toUpperCase()+ personne.substring(1) + " ajouté. \n");
		Affiche.addTime();
		Menu.afficheMenu1();
		return;
	}
	
	public static void afficheMenuCommande(String CommandeouAchat) {
		System.out.println("Saisie des "+CommandeouAchat+"s");
		System.out.println("----------------------------");		
		System.out.println("Taper 1: Saisie d'une nouvelle "+CommandeouAchat);
		System.out.println("Taper 2: "+CommandeouAchat+" des articles");
		System.out.println("Taper 3: Visulaliser les "+CommandeouAchat+"s");
		System.out.println("Taper r: Retour");	
	}
	public static void addTime() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}	
	}
}
