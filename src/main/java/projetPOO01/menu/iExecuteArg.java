package projetPOO01.menu;

import java.util.Dictionary;


import projetPOO01.Enumerations.EPersonne;

@FunctionalInterface 
public interface iExecuteArg {
	void apply(String personne,Dictionary<EPersonne, String> dico);

}
