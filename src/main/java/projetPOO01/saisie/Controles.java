package projetPOO01.saisie;

import java.util.Date;
import java.util.Scanner;

import projetPOO01.Methodes.Achat;
import projetPOO01.Methodes.Commande;

public class Controles {
	
public static Achat rentrerAchat(Scanner sc) {
		
		System.out.println("Saisie d'un nouvel achat");
		System.out.println("----------------------------");
		System.out.println("Date (format XX/XX/XXXX):");	
		Date dateOb = new Date();
		boolean erreursaisie = true;
		while(erreursaisie) {
			try {
				String date = sc.nextLine();		
				dateOb = Achat.checkDate(date);
				erreursaisie = false;
		 	}
			catch(Exception e) {
				System.err.println("Mauvais format attention");
				System.out.println("Date (format XX/XX/XXXX):");
			}
		 }
		System.out.println("Intitul�:");
		String intitule = sc.nextLine();
		System.out.println("Quantit�:");
		String quantite = sc.nextLine();
		Achat achat = new Achat(dateOb, intitule,quantite);
		return achat;
		}

	
	
	public static Commande rentrerCommande(Scanner sc) {
		
		System.out.println("Saisie d'un nouvel achat");
		System.out.println("----------------------------");
		System.out.println("Date (format XX/XX/XXXX):");	
		Date dateOb = new Date();
		boolean erreursaisie = true;
		while(erreursaisie) {
			try {
				String date = sc.nextLine();		
				dateOb = Achat.checkDate(date);
				erreursaisie = false;
		 	}
			catch(Exception e) {
				System.err.println("Mauvais format attention");
				System.out.println("Date (format XX/XX/XXXX):");
			}
		 }
		System.out.println("Intitul�:");
		String intitule = sc.nextLine();
		System.out.println("Quantit�:");
		String quantite = sc.nextLine();
		Commande commande = new Commande(dateOb, intitule,quantite);
		return commande;
		}
	
	public static String validateAnswer(String[] listRep,Scanner sc){
		String element;
		element = sc.nextLine();
		while (testReponse(listRep,element)) {
			System.out.println("Attention votre r�ponse est non valable. Taper parmis les choix suivant: "); 
			for(String e:listRep) { // faire pour de 0 � l'avant dernier et g�rer le dernier dans un cas apart
				if (e.equals("")) {
					System.out.print("Entrer ");
				}
				System.out.print(e+" ");
			}
			element = sc.nextLine();
		}
		return element;
	}
	
	public static boolean testReponse(String[] listRep, String element){
		for (String r:listRep) {
			if (element.equals(r)) {
				return false;
			}		
		}	
		return true;	
	}

}
