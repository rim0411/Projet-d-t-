
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java .util.ArrayList;
import java.util.Collections ;
 class Tak{

	
	int[][] grille;

	

	 
	public Tak() {
		
		int[][] grille = new int [10][10] ;
		this.grille = grille;
	}

	/**
	  Constructeur Genere le Takuzu passe en parametre. 
	
	 */
	public Tak(int[][] grille) {
		this.grille = grille;
	}
	
	/**
	  Constructeur qui Genere le Takuzu vide
	  
	 */
	public Tak(int taileGrille) {
		int[][] grille = new int[taileGrille][taileGrille];
		
		for (int i = 0; i < taileGrille; i++) {
			int[] ligne = new int[taileGrille];
			for (int j = 0; j < ligne.length; j++) {
				Random r = new Random();
				int value = r.nextInt(3);
				ligne[j] = value;				
			}
			grille[i] = ligne;
		}
		
		this.grille = grille;
	}
	
	public int[][] getGrille() {
		return grille;
	}
	public void setGrille(int[][] grille) {
		this.grille = grille;
	}
	
	public String toString(){
		String s = "";
		for (int i = 0; i < grille.length; i++) {
			s += Arrays.toString(grille[i]);
			if(i != grille.length -1 ) s += "\n";
		}
		return s.replace(",", "").replace("2", "*").replace("[", "").replace("]", "");
	}
	
	
	public Tak duplique() {
		int[][] duplique = new int[this.grille.length][];
		for (int i = 0; i < duplique.length; i++) {
			duplique[i] = this.duplique().grille[i];
		}
		
		return new Tak(duplique);
	};
	public Tak resolution(){
		return resolution(this, 0, 0);
	}
	private Tak resolution(Tak tak, int ligne, int colone)
{	
		
		for (int i = 0; i < 2; i++) {
			if(this.grille[ligne][colone] == 2){ 											// Si la case est vide 
				tak.grille[ligne][colone] =0;
			}
			
/*			System.out.println(tak 
					+ "\t Travail sur L"+ ligne + " valide? " + tak.ligneValide(ligne) 
					+ "\t C"+ colone + " valide? " + tak.coloneValide(colone));*/
			
			if(colone < this.grille[ligne].length -1){										// Si l'on ne se trouve pas en fin de ligne, on passe a la colone suivante
				if(tak.ligneValide(ligne) && tak.coloneValide(colone)){
					Tak tmp = resolution(tak, ligne, colone +1);					// On passe a la resolution de la case suivante
					if(tmp != null && tmp.estValide()) return tmp;
				}
			} else if(ligne < this.grille.length -1){										//  si l'on est en fin de ligne on verifie la validitÃ© de l et col et l'unicite de ligne
				if(tak.ligneValide(ligne) && tak.coloneValide(colone) && tak.ligneEstUnique(ligne)){
					int l=ligne + 1 ;
					Tak tmp = resolution(tak,l,colone);
													// On passe a la resolution de la ligne suivante(ligne+1)
					if(tmp != null && tmp.estValide())
						return tmp;
				}
			}else {
				if(tak.estValide())
					return tak;
				return null;
			}
		}
		
		return null;
	}
	
	/**
	 Verifie la validite du Takuzu
	 */
	public boolean estValide(){
		// Pour chaque lignes
		for (int i = 0; i < grille.length; i++) {
			if(!ligneValide(i) || !ligneEstUnique(i))
				return false;
			// Pour chaque colone dans la ligne i
			for (int j = 0; j < grille[i].length; j++) {
				 if(!coloneValide(j) || !coloneEstUnique(j))
					 return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Verifier la validite de la ligne 
	 */
	public boolean ligneValide(int i){
		boolean coloneIncomplete = false; 	// Variable boolenne  indiquant si la ligne est completement remplis
		int nb_de_1 = 0;					// Nombre du num 1 de la ligne
		int nb_de_0 = 0;					// Nombre du num 0 de la ligne
		for (int j = 2; j < grille[i].length; j++) {


	if (j>2)	{
		
			// Si trois cases consecutives sont identiques (non vides)
 			if(grille[i][j-2] == grille[i][j-1] && grille[i][j-1] == grille[i][j] && grille[i][j] != -1)
				return false; // Le Takuzu est invalide
}
 			
 			if(grille[i][j] == 0) 
 				nb_de_0 ++;
 			else if(grille[i][j] == 1) 
 				nb_de_1 ++;
 			else 
 				coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		}
		
		return (nb_de_1 == (grille.length / 2) && nb_de_0 == (grille.length / 2)) || (coloneIncomplete && nb_de_0 <= (grille.length / 2) && nb_de_1 <= (grille.length / 2));
	}
	
	public void aff ( ){
		for (int k = 1; k< grille.length; k++)
		for (int l = 1; l < grille.length; l++)
			if( this.grille[k][l]==2){
				
			System.out.println(("*")) ;
			}
			else{
		System.out.println(this.grille[k][l]);
		}
		System.out.println("\n");
	}

	public boolean coloneValide(int i){
		boolean coloneIncomplete = false; 	// Variable indiquant si la ligne est completement remplis, ou non
		int nb_de_1 = 0;					// Nombre de 1 de la ligne
		int nb_de_0 = 0;					// Nombre de 0 de la ligne
		
		if(grille[0][i] == 0) 
			nb_de_0 ++;
		else if(grille[0][i] == 1) 
			nb_de_1 ++;
		else 
			coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		
		if(grille[1][i] == 0) 
			nb_de_0 ++;
		else if(grille[1][i] == 1) 
			nb_de_1 ++;
		else 
			coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		
		// Pour chaque lignes
		for (int j = 2; j < grille.length; j++) {
			// Si trois cases consecutives sont identique et remplis
 			if(grille[j-2][i] == grille[j-1][i] && grille[j-1][i] == grille[j][i] && grille[j][i] != -1)
				return false; // Le Takuzu est invalide
 			
 			if(grille[j][i] == 0) 
 				nb_de_0 ++;
 			else if(grille[j][i] == 1) 
 				nb_de_1 ++;
 			else 
 				coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		}
				
		return nb_de_1 == (grille.length / 2) || (coloneIncomplete && nb_de_1 <= (grille.length / 2));
	}
	
	/**
	 Verifie qu'une ligne n'a pas de double dans le Takuzu
	 */
	public boolean ligneEstUnique(int j){
		// Pour chaque ligne
		for (int i = 0; i < j; i++) {
			// Si deux lignes sont identique
			if(Arrays.equals(this.grille[i], this.grille[j]))
				return false;
		}
		
		return true;
	}
	
	/**
	 Verifie qu'une colone est unique
	 
	 */
	public boolean coloneEstUnique(int j){
		// Pour chaque ligne
		for (int i = 0; i < j; i++) {
			// Si deux lignes sont identique
			if(Arrays.equals(this.grille[i], this.grille[j]))
				return false;
		}
		
		return true;
	}
	
	/**
	 * Generer un Takuzu partiellement pre-remplis
	 
	 */
	public Tak generate(float proportionPreRemplissage) {
		Random rand = new Random();
		int nb = 0;
		
		// Melange les lignes du Takuzu
		do{
			int line1 = rand.nextInt(this.grille.length);
			int line2 = rand.nextInt(this.grille.length);
			
			int[] tmp = grille[line1];
			grille[line1] = grille[line2];
			grille[line2] = tmp;
			
		}while(++nb < this.grille.length && !this.estValide());
		
		// Parcours des lignes de la grille 
		for (int i = 0; i < grille.length; i++) {
			// Parcours des colones de la grille 
			for (int j = 0; j < grille[i].length; j++) {
				// Si le nombre tirÃ© alÃ©atoirement est plus grand de proportionPreRemplissage
				if(rand.nextFloat() > proportionPreRemplissage)
					this.grille[i][j] = 2; // La case est "vidÃ©"
			}
		}
		
		return this;
	}
	
}


public class test {
	private static String a;
	static ArrayList tab = new ArrayList() ;
	static ArrayList tab2 = new ArrayList() ;
	static long score=0;
	public static void main(String[] args) {
		Tak t1;
		Tak t;
		Tak essai = new Tak();

		int choix = -1;
		while (choix != 0) {

			System.out.println("0 -> quitter");
			System.out.println("1 -> regle du jeux");
			System.out.println("2 -> jouer");

			System.out.println("Votre Choix :");
			Scanner sc1 = new Scanner(System.in);
			int choixi = sc1.nextInt();

			if (choixi == 1) {
				System.out.println("c'est un jeux semblable a sudoku la grille est compose de 0 et 1  ");
				System.out.println("==========");
				System.out.println("les lignes doivent etre differents et les colonnes");
				System.out.println("==========");
				System.out.println("le nombre de 1 doit etre egale au nombre de 0 dans chaque ligne et colonne");

				System.out
						.println("Les grilles doivent etre carre, et les lignes doivent avoir un nombre pair de cases.");
				
				System.out.println("choisir le 2eme choix du menu pour jouer");
				System.out.println("==========");
				
				System.out
						.println("Lancer le jeux Takuzu de niveau entré.");
				System.out.println("");
				System.out
						.println(" la grille est preremplis aleatoirement");
				System.out.println("les cases vides sont designes par des etoiles ");
				System.out.println("si tu trouves que plus que la moitie des cases portant le meme nombre  tu peux changer");
			

				System.exit(0);
			}

			else if (choixi == 2) 
			{
				
					System.out
							.println("             ********************************");
					System.out
							.println("             *          1 Nouvelle partie     *");
					System.out.println("             *          Be ready   *");
					System.out
							.println("             ********************************");
					int taille = 0;
					do {
						System.out.println("donner le niveau du jeux 4*4 /6*6/8*8");
						Scanner sc2 = new Scanner(System.in);
						taille = sc2.nextInt();
					} while ((taille != 6) && (taille != 4) && (taille != 8));

					int l=1;

					while (l<3)
					{
						float f = 10;
					
						t = new Tak(taille);
						System.out.println(t.toString());
						
						System.out.println("donner ta"+" " +l+" "+"solution:");
						long startTime1 = System.currentTimeMillis();
						String a;
						for (int j = 0; j < taille; j++) {
							Scanner sc6 = new Scanner(System.in);
							a = sc6.next();
							for (int i = 0; i < taille; i++) {
								for (int k = 0; k < taille; k++) {

									essai.grille[i][k] = (int) a.charAt(k);

								}
							}
						}

						long endTime = System.currentTimeMillis();
						 score = (endTime - startTime1)/1000;
						System.out.println("****");
						System.out.println("ton score est:");
						System.out.println(score+" "+"secondes");
						
					
						
						Tak slt = essai.resolution();
						System.out.println(tab);
						if (essai.estValide()) {
							System.out.println("vous avez gagnez !voulez vous rejouer 1/0");
									
							Scanner sc7 = new Scanner(System.in);
							int a1 = sc7.nextInt();
							if (a1 == 0) {
								
				
								
							}
						} else 
						{
							System.out.println("vous avez perdu car:");
							
							for(int j=0;j<essai.grille.length;j++) {
							
							if (essai.ligneValide(j) ) 
							{
								System.out.println("la ligne"+" "+j+" " +"nest pas valide");
								System.out.println("******");
							}
							else if(essai.ligneEstUnique(j)){
								System.out.println("la ligne "+j+"est dupliqué");
								System.out.println("******");
							}
							
							if (essai.coloneValide(j) ) {
								System.out.println("la colone"+" "+j+""+"nest pas valide");
								System.out.println("*+*+*+*+*+*");
							}
							
							else if(essai.coloneEstUnique(j)){
								System.out.println("la colone "+" "+j+" "+"est dupliqué");
								System.out.println("******");
							}	
							}
							System.out.println("***********");
							
							
						}
						
						
						l++;
					}
					
					

					 tab.add(score) ;
					 Collections.sort(tab);
					for(int i =0;i<10;i++) {
						
						tab2.add(tab.get(i));
					}
					try 
					{
						PrintStream s = new PrintStream(new File ("C:\\Users\\user\\eclipse-workspace\\jeux Tak\\src\\scores.txt"));
						//System.setOut(s);
						if (tab.size()>10) {
							
						
						for (int i =0;i<10;i++) {
							s.print(tab2.get(i));
						
						}
						
					}
						else for (int i =0;i<tab.size();i++) {
							s.print(tab2.get(i));
						
						}	
						
					}
					catch(FileNotFoundException o) {
						System.out.println(o);
						
					}
					

					
					
					
				
			}
		}
	}
}
