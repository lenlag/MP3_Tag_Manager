package main;

import java.io.IOException;

/**
 * 
 * @author 1802448
 *
 *
 * Lire les tags d'un mp3
 */
public class TestMain {

	public static void main(String[] args) {

		try { 
			Mp3TagManager mp = new Mp3TagManager("mp3/muzik.mp3");
			String titre = mp.getTitle();
			String auteur = mp.getAuthor();
			String album = mp.getAlbum();
			String annee = mp.getYear();
			String comment = mp.getComment();
			int style = mp.getStyle();
		
			System.out.println("Titre = "+titre+" Auteur = "+auteur+" Album = "+album+" Année = "+annee+" Commentaires = "+comment+" Style = "+style); 

			mp.setTitle("Le petit lapin");
			mp.setAuthor("Moi-meme");
			mp.setAlbum("Au pays des bestioles");
			mp.setYear("1964");
			mp.writeTags();
			// verifier
			mp.readTags();
			System.out.println("Check after re-write : " + mp.toString());
			// remettre les valeurs d'origine
			mp.setTitle(titre);
			mp.setAuthor(auteur);
			mp.setAlbum(album);
			mp.setYear(annee);
			mp.writeTags();
			// reverifier
			mp.readTags();
			System.out.println("Check if originals're back : " + mp.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Mp3TagException me) {
			me.printStackTrace();
		} 
		
	}
	
}