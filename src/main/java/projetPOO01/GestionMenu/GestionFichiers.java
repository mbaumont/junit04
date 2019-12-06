package projetPOO01.GestionMenu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import projetPOO01.GestionPersonnes.Personne;
import projetPOO01.menu.Menu;

public class GestionFichiers {
	/**
	 * 
	 * @param nomFichier Nom du fichier que l'on va sauvegarder
	 */
	public static void sauvegardeListe(String nomFichier) {		
		try {
			FileOutputStream fos = new FileOutputStream(nomFichier + ".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			System.out.println(Menu.listPersonne.toString());
			oos.writeObject(Menu.listPersonne);
			oos.flush();
			oos.close();
			System.out.println("Fichier sauvegardé avec succés.");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Echec de sauvegarde de fichiers. ");
		}		
	}
	@SuppressWarnings("unchecked")
	public static void chargeListe(String nomFichier){	
		try {
			 FileInputStream fis = new FileInputStream(nomFichier + ".ser");
			 ObjectInputStream ois = new ObjectInputStream(fis);
			 Menu.listPersonne = (List<Personne>)ois.readObject();
			 System.out.println(Menu.listPersonne.toString());
			 ois.close();
			 System.out.println("Fichier chargé avec succés.");
		} 
		catch ( IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
			System.out.println("Echec de charge du fichier.");
		}

	}
}
