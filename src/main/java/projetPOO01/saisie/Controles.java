package projetPOO01.saisie;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import projetPOO01.Methodes.Achat;
import projetPOO01.Methodes.Commande;

public class Controles {
	

@SuppressWarnings("unchecked")
public static  <T> T rentrerAchatOuCommande(Scanner sc,String achatOuCommande) {
	
	System.out.println("Saisie d'un nouvel "+achatOuCommande);
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
	System.out.println("Intitulé:");
	String intitule = sc.nextLine();
	System.out.println("Quantité:");
	String quantite = sc.nextLine();
	switch (achatOuCommande) {
	case("achat"):
	Achat achat = new Achat(dateOb, intitule,quantite);
	return (T) achat;
	
	case("commande"):
	Commande commande = new Commande(dateOb, intitule,quantite);
	return (T) commande;
	}
	return null;

	}
	
	public static String validateAnswer(String[] listRep,Scanner sc){
		String element;
		boolean flag = true;
		do {
			element = sc.nextLine();
			if (testReponse(listRep,element)) {
				System.out.println("Attention votre réponse est non valable. Taper parmis les choix suivant: "); 
				for(String e:listRep) { 
					if (e.equals("")) {
						System.out.print("Entrer ");
					}
					System.out.print(e+" ");
				}		
			}
			else flag = false;
		}
		while (flag);
		return element;
	}
	
	public static boolean testReponse(String[] listRep, String element){
		Stream<String> streamarray = Stream.of(listRep);
		return !streamarray.anyMatch(r -> r.equals(element));			
	}

		
}
