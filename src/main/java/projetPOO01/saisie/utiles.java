package projetPOO01.saisie;


public class utiles {
	
	
	
	public static <T> T mTemplate(Class<T> maclasse) throws InstantiationException, IllegalAccessException
	{
		T t;
		t = maclasse.newInstance();
		
		return  t;
	}

}