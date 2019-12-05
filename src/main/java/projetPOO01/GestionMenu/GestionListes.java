package projetPOO01.GestionMenu;

import java.util.ArrayList;
import java.util.List;

import projetPOO01.GestionPersonnes.IClient;
import projetPOO01.GestionPersonnes.IFournisseur;
import projetPOO01.GestionPersonnes.Personne;
import projetPOO01.menu.Menu;

public class GestionListes {

	public static List<String> listNFournisseur() {
		List<String> listFournisseur = new ArrayList<String>() ;
		for(Personne p:Menu.listPersonne) {
			if (p instanceof IFournisseur) {
				IFournisseur f = (IFournisseur) p;
				if (f.fournisseurOuPas()) {
					listFournisseur.add(f.afficheNFournisseur());	
				}
			}
		}
		return listFournisseur;
		}			

	public static List<String> listNClient(){
		List<String> listClients  = new ArrayList<String>();
		for(Personne p:Menu.listPersonne) {
			if (p instanceof IClient) {
				IClient f = (IClient) p;
				if (f.clientOuPas()) {
					listClients.add(f.afficheNClient());	
				}
			}
		}
		return listClients;
		}		
	
}
