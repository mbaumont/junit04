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
		Commande commande = Controles.rentrerCommande(Menu.sc);
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
		Achat achat = Controles.rentrerAchat(Menu.sc);
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

}
