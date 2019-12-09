package projetPOO01.GestionMenu;


import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import projetPOO01.Enumerations.EPersonne;
import projetPOO01.GestionPersonnes.Client;
import projetPOO01.GestionPersonnes.Fournisseur;
import projetPOO01.GestionPersonnes.Patron;
import projetPOO01.GestionPersonnes.Personne;
import projetPOO01.GestionPersonnes.Salarie;
import projetPOO01.menu.Menu;
import projetPOO01.menu.iExecute;
import projetPOO01.menu.iExecuteArg;

public class GestionsDico {
	private static Boolean four = null;
	private static Boolean client = null;
	public static Map<EPersonne, String> initDicoPersonne(char typePersonne){
		Map<EPersonne, iExecuteArg> dico = new Hashtable<EPersonne, iExecuteArg>();
		Map<EPersonne, String> dico2 = new Hashtable<EPersonne, String>();
		dico.put(EPersonne.nom,()->GestionsDico.initPersonnes(EPersonne.nom,"Saisie du nom : "));		
		dico.put(EPersonne.prenom,()->GestionsDico.initPersonnes(EPersonne.prenom,"Saisie du prénom : "));
		dico.put(EPersonne.adresse,()->GestionsDico.initPersonnes(EPersonne.adresse,"Saisie de l'adresse : "));
		dico.put(EPersonne.ville,()->GestionsDico.initPersonnes(EPersonne.ville,"Saisie de la ville : "));
		dico.put(EPersonne.codepostal,()->GestionsDico.initPersonnes(EPersonne.codepostal,"Saisie du code postal : "));
		switch(typePersonne) {
		case 's':			
		case 'p':
			dico.put(EPersonne.salaire,()->GestionsDico.initPersonnes(EPersonne.salaire,"Saisie du salaire au format: XXXX,XX"));
			dico.put(EPersonne.nSecu,()->GestionsDico.initPersonnes(EPersonne.nSecu,"Saisie du numéro de sécurité social : "));
			dico.put(EPersonne.clientOuPas,()->GestionsDico.initPersonnes(EPersonne.clientOuPas,"Etes vous client (oui/non): "));
			break;		
		case 'c':
			dico.put(EPersonne.nClient,()->GestionsDico.initPersonnes(EPersonne.nClient,"Saisie du numéro Client (attention un numéro par client) : "));
			dico.put(EPersonne.fournisseurOuPas,()->GestionsDico.initPersonnes(EPersonne.fournisseurOuPas,"Etes vous également fournisseur (oui/non): "));

			break;
		case 'f':
			dico.put(EPersonne.nFour,()->GestionsDico.initPersonnes(EPersonne.nFour,"Saisie du numéro Fournisseur (attention un numéro par fournisseur) : "));
			dico.put(EPersonne.clientOuPas,()->GestionsDico.initPersonnes(EPersonne.clientOuPas,"Etes vous client (oui/non): "));
			break;	
		}
		four = false;
		client = false;
		dico.entrySet().stream().filter(e->e.getKey().equals(EPersonne.fournisseurOuPas)&&e.getValue().apply().equals("oui"))
			.forEach(e->four = true);
		if (four) {
				dico.put(EPersonne.nFour,()->GestionsDico.initPersonnes(EPersonne.nFour,"Saisie du numéro Fournisseur (attention un numéro par fournisseur) : "));					
				dico.remove(EPersonne.fournisseurOuPas); }
		dico.entrySet().stream().filter(e->e.getKey().equals(EPersonne.clientOuPas)&&e.getValue().apply().equals("oui"))
			.forEach(e->client=true);
		if (client){
			dico.put(EPersonne.nClient,()->GestionsDico.initPersonnes(EPersonne.nClient,"Saisie du numéro Client (attention un numéro par Client)"));
						dico.remove(EPersonne.clientOuPas);
		}
		dico.entrySet().stream().forEach(e->dico2.put(e.getKey(), e.getValue().apply()));											
		System.out.println(dico2);		
		return dico2;
	}



	public static String initPersonnes(EPersonne ep, String texte) {
		String saisie = null;
		System.out.println(texte);
		boolean erreursaisie = true;
		while(erreursaisie) {
	   	 	try {
	   	 		saisie= Menu.sc.nextLine();   	 		
				switch(ep){
				 case clientOuPas: 
		      	 case fournisseurOuPas: 
		      		 Personne.checkOuiNon(saisie);
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
      		 erreursaisie = false;
 		}
  	 		catch(Exception e) {
  	 			System.err.println(e.getMessage());
  	 			System.out.println(texte);
  	 		}
   	 		}
		return saisie;
	}

	public static void addPatron(Map<EPersonne, String> dico ) {
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
	
}