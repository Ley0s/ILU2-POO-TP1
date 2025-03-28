package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Village village = new Village("village", 10, 3);
		village.afficherVillageois();
		//Etal etal = new Etal();
		//Gaulois obelix = new Gaulois("Obélix", 25);
		//etal.acheterProduit(2, obelix);
		
		System.out.println("Fin du test");
	}
}
