package main;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.print.attribute.IntegerSyntax;

public class Mp3TagUI {


	public Mp3TagUI () {
	}


	public void display (String path) throws IOException, Mp3TagException {
		Mp3TagManager mp = new Mp3TagManager(path);
		mp.readTags();
		System.out.println("Title = " + mp.getTitle());
		System.out.println("Author =" + mp.getAuthor());
		System.out.println("Album =" + mp.getAlbum());
		System.out.println("Comment =" + mp.getComment());
		System.out.println("Style = " + mp.getStyle());
		System.out.println("Year = " + mp.getYear());
	}




	public void manage (String path) throws IOException, Mp3TagException {
		
		Scanner sc = new Scanner (System.in);
		Mp3TagManager mp = new Mp3TagManager(path);
		String categorie = "";
		Mp3TagStyle [] a = Mp3TagStyle.values();
		//Mp3TagStyle.values ();
		while (true) {
			mp.readTags();
			System.out.println("1 - change title" + "[" + mp.getTitle() + "]");
			System.out.println("2 - change author" + "[" + mp.getAuthor() + "]");
			System.out.println("3 - change album" + "[" + mp.getAlbum() + "]");
			System.out.println("4 - change comment" + "[" + mp.getComment() + "]");
			System.out.println("5 - change style" + "[" + a[mp.getStyle()].toString() + "]");
			System.out.println("6 - change year" + "[" + mp.getYear() + "]");
			System.out.println("X - sortir" );
			String answer = sc.nextLine();
			
			if (answer.equalsIgnoreCase("x")) {
				return; // pour sortie de la fonction
			}
						
			
			switch (answer) { // 1er switch pour le choix de l'option 1-6 ou x
			
			case "1" : 
				categorie =  "title";
				break;
			case "2" :
				categorie = "author";
				break;
			case "3" :
				categorie = "album";
				break;
			case "4" :
				categorie = "comment";
				break;
			case "5" :
				categorie = "style";
				break;
			case "6" :
				categorie = "year";
				break;
			default : 
				beep();	
				continue;
							
			}
	
			System.out.println();
			System.out.println("Enter a new value [ " + categorie + " ] or type X");
			String answer2 = sc.nextLine();
			
			if (answer2.equalsIgnoreCase("x")) { // test pour Style, car x n'est pas un int
				continue;
			}
			
			switch (answer) { // 2ème switch pour la nouvelle valeur + écriture
			case "1" : 
			mp.setTitle(answer2);
			break;
			
			case "2" : 
			mp.setAuthor(answer2);
			break;
			
			case "3" : 
			mp.setAlbum(answer2);
			break;
			
			case "4" : 
			mp.setComment(answer2);
			break;
			
			case "5" :
				try {
				mp.setStyle(Integer.parseInt(answer2)); // recup sous forme d'Integer
				} catch (Exception e) {
				beep();
				continue;	
				} 
				break; 
			
			case "6" : 
			mp.setYear(answer2);
			break;
			
			default : 
				break;
			}
			
			mp.writeTags();
			if (!answer2.equalsIgnoreCase("x")) {
				mp.writeTags();
			}
					
		}
	}

	private void beep () { // f-on beep
		Toolkit.getDefaultToolkit().beep();
	}
	
	public static void main(String[] args) throws IOException, Mp3TagException {

		Mp3TagUI mp3Tag = new Mp3TagUI ();

	/*	try {
			mp3Tag.display("mp3/muzik.mp3");
		} catch (IOException | Mp3TagException e) {
			e.printStackTrace();
		} 
	 */

		mp3Tag.manage ("mp3/muzik.mp3");
		System.out.print("Le programme est terminé");
		
	
	Object o =new Object ();
	System.out.println(o);
	
		
	} 

} 
