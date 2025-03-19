package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	/* Attributes */
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	/* Constructor */
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	/* Methods */
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + 
				chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + 
				" vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + 
			" " + produit + ".\n");
		int i = marche.trouverEtalLibre();
		marche.utiliserEtal(i, vendeur, produit, nbProduit);
		i++;
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + 
				" à l'étal n°" + i + ".\n");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsVendeurs = marche.trouverEtals(produit);
		Gaulois vendeur;
		if (etalsVendeurs.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + 
				" au marché.\n");
		} else if (etalsVendeurs.length == 1) {
			vendeur = etalsVendeurs[0].getVendeur();
			chaine.append("Seul le vendeur " + vendeur.getNom() + " propose des " + 
				produit + " au marché.\n");
		} else {
			chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
			for (int i = 0; i < etalsVendeurs.length; i++) {
				vendeur = etalsVendeurs[i].getVendeur();
				chaine.append("- " + vendeur.getNom() + "\n");
				
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etalLibere = rechercherEtal(vendeur);
		return etalLibere.libererEtal();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village \"" + nom);
		if(marche.etals.length == 0) {
			chaine.append("\" ne possède pas d'étals.\n");
		} else if(marche.etals.length == 1) {
			chaine.append("\" possède un étal :\n");
		} else {
			chaine.append("\" possède plusieurs étals :\n");
		}
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
	
	/* Intern classes */
	private static class Marche {
		/* Attributes */
		private Etal[] etals;

		/* Constructor */
		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		/* Methods */
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!(etals[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbEtals = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					nbEtals++;
				}
			}
			Etal[] etalsVendeurs = new Etal[nbEtals];
			int j = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsVendeurs[j] = etals[i];
					j++;
				}
			}
			return etalsVendeurs;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalsLibres = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nbEtalsLibres++;
				}
			}
			chaine.append("Il reste " + nbEtalsLibres + 
					" étals non utilisés dans le marché.\n");
			return chaine.toString();
		}
	}
	
	/* Exceptions */
	public class VillageSansChefException extends
}