package projetPOO01.GestionMenu;


import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import projetPOO01.Enumerations.EPersonne;
import projetPOO01.GestionPersonnes.Client;
import projetPOO01.GestionPersonnes.Fournisseur;
import projetPOO01.GestionPersonnes.Patron;
import projetPOO01.GestionPersonnes.Personne;
import projetPOO01.GestionPersonnes.Salarie;
import projetPOO01.menu.Menu;

public class GestionsDico {
	
	public static void initDicoPersonne(Dictionary<EPersonne, String> dico, String typePersonne){
		dico.put(EPersonne.nom,"Saisie du nom : ");
		dico.put(EPersonne.prenom,"Saisie du prénom : ");
		dico.put(EPersonne.adresse,"Saisie de l'adresse : ");
		dico.put(EPersonne.ville,"Saisie de la ville : ");
		dico.put(EPersonne.codepostal,"Saisie du code postal : ");
		switch(typePersonne) {
		case "s":
			dico.put(EPersonne.salaire,"Saisie du salaire au format: XXXX,XX");
			dico.put(EPersonne.nSecu,"Saisie du numéro de sécurité social : ");
			dico.put(EPersonne.clientOuPas,"Etes vous client (oui/non): ");
			break;
		case "p":
			dico.put(EPersonne.salaire,"Saisie du salaire au format: XXXX,XX");
			dico.put(EPersonne.nSecu,"Saisie du numéro de sécurité social : ");
			dico.put(EPersonne.clientOuPas,"Etes vous client (oui/non): ");
			break;		
		case "c":
			dico.put(EPersonne.nClient,"Saisie du num�ro Client (attention un numéro par client) : ");
			dico.put(EPersonne.fournisseurOuPas,"Etes vous également fournisseur (oui/non): ");
			break;
		case "f":
			dico.put(EPersonne.nFour,"Saisie du numéro Fournisseur (attention un numéro par fournisseur) : ");
			dico.put(EPersonne.clientOuPas,"Etes vous client (oui/non): ");
			
			break;		
		}
	}

	public static void addPatron(Dictionary<EPersonne, String> dico) {
		initDicoPersonne(dico, "p");
		initToutesPersonne(dico);
		Personne p3 = new Patron(dico);
		Menu.Patron = true;
		Menu.listPersonne.add(p3);
		System.out.println("Patron ajouté. \n");
	}
	
	public static void deletePatron() {
		for (Personne p:Menu.listPersonne) {
			if (p instanceof Patron) {
				Menu.listPersonne.remove(p);
				return;
			}
		}
	}
	public static void initToutesPersonne(Dictionary<EPersonne, String> dico) {
			boolean erreursaisie;
			boolean continueornot = true;		
			@SuppressWarnings("unchecked")
			Dictionary<EPersonne, String> dico2 = (Dictionary<EPersonne, String>) ((Hashtable<EPersonne, String>)dico).clone();	
			Enumeration<EPersonne> k; 
			k = dico2.keys();
			while (continueornot) {				 
				EPersonne ep = k.nextElement();
				System.out.println(dico2.get(ep));		
				erreursaisie = true;
				while(erreursaisie) {
	       	 		try {
	            		 String saisie= Menu.sc.nextLine();
	            		 switch(ep){
	            		 case clientOuPas: 
	            			 Personne.checkOuiNon(saisie);
	            			 if (saisie.equals("oui")) {
	            				 dico2.put(EPersonne.nClient,"Saisie du numéro Client(attention un numéro par client): ");}
	            			 break;
				       	 case fournisseurOuPas: 
				       		 Personne.checkOuiNon(saisie);
				       		 if (saisie.equals("oui")) {
				       			 dico2.put(EPersonne.nFour,"Saisie du numéro Fournisseur (attention un numéro par fournisseur):");}
				       		 break;
	            		 case codepostal: Personne.checkCodePostal(saisie);break;
	            		 case nSecu: Salarie.checknSecu(saisie);break;
	            		 case nClient: 
	            			 List<String> listClient = GestionListes.listNClient();
	            			 Client.testNClient(saisie,listClient); break;
	            		 case salaire: Salarie.checkSalaire(saisie);break;
	            		 case nFour:
	            			 List<String> listFournisseur = GestionListes.listNFournisseur();
	            			 Fournisseur.testNFournisseur(saisie,listFournisseur);break;
	            		 default: break;
	            		 }	
		           		 dico.put(ep,saisie);
		           		 dico2.remove(ep);
		           		 erreursaisie = false;
		           		 k = dico2.keys();
		           		 continueornot = k.hasMoreElements();
	
	       	 		}
	       	 		catch(Exception e) {
	       	 			System.err.println(e.getMessage());
	       	 			System.out.println(dico2.get(ep));
	       	 		}
	       	 	}			
			}
	}
	}
