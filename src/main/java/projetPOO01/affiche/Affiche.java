package projetPOO01.affiche;

import java.util.List;

import projetPOO01.GestionPersonnes.IClient;
import projetPOO01.GestionPersonnes.IFournisseur;
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
	public static void addTime() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}	
	}
}
