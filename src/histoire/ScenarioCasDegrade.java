package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois obelix = new Gaulois("Ob�lix", 25);
		etal.acheterProduit(2, obelix);
		
		System.out.println("Fin du test");
	}
}
