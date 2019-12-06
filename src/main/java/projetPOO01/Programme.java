package projetPOO01;


import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import projetPOO01.GestionPersonnes.IClient;

import projetPOO01.GestionPersonnes.Personne;

import projetPOO01.menu.Menu;
/**
 * 
 * @author Marie Baumont
 *
 */
public class Programme {
	static List<Personne> listPersonne = new ArrayList<Personne>();
	//static Boolean Patron = false;
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {	
		Menu.sc = Programme.sc;
		Menu.listPersonne = Programme.listPersonne;
		Menu.Patron =false;
		Menu.afficheMenu();

	}	


	
}
