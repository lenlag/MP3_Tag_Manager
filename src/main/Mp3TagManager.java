package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.print.DocFlavor.STRING;

/**
 * 
 * @author 34011-65-01
 *
 */

public class Mp3TagManager {
	private String tag;
	private String path;
	private String title;
	private String author;
	private String album;
	private String year;
	private int style;
	private String comment;

	private final static String TAG = "TAG";

	// constructeurs

	public Mp3TagManager(String path) throws IOException,Mp3TagException{	
		this.path = path;
		readTags();
	} 

	public Mp3TagManager() {

	}

	//getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	//méthodes

	public void writeTags () throws IOException { 
		RandomAccessFile raf = null;
		File file = new File (path);
		long lengthFile = file.length();
		
		try {
			raf = new RandomAccessFile(path, "rw");
			raf.seek(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue());
			int len = 0;

			raf.write(TAG.getBytes());
			len += Mp3Enum.TAG_LEN.getValue();
			
			
			raf.write(padAndLimit(title, Mp3Enum.TITLE_LEN.getValue()).getBytes());
			//raf.write(title.getBytes()); //old version
			len += Mp3Enum.TITLE_LEN.getValue();
			raf.seek(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue() + len);

			
			raf.write(padAndLimit(author, Mp3Enum.TAG_LEN.getValue()).getBytes());
			len += Mp3Enum.AUTHOR_LEN.getValue();
			raf.seek(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue() + len);
 
			raf.write(padAndLimit(album, Mp3Enum.ALBUM_LEN.getValue()).getBytes());
			len += Mp3Enum.ALBUM_LEN.getValue();
			raf.seek(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue() + len);

			raf.write(padAndLimit(year, Mp3Enum.YEAR_LEN.getValue()).getBytes());
			len += Mp3Enum.YEAR_LEN.getValue();
			raf.seek(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue() + len);

			raf.write(padAndLimit(comment, Mp3Enum.COMMENT_LEN.getValue()).getBytes());
			len += Mp3Enum.COMMENT_LEN.getValue();
			raf.seek(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue() + len);
			
			raf.write(style); 
			len += Mp3Enum.STYLE_LEN.getValue();
			raf.seek(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue() + len);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				if (raf !=null)
				raf.close();
			}
			catch  (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void readTags() throws IOException, Mp3TagException { // écrire cette fonction et l'appeler depuis le constructeur

		FileInputStream fis = null;
	
		File file = new File (path);
		file.length(); 

		long lengthFile = new File(path).length();
		
		try {
			fis = new FileInputStream(path);
			fis.skip(lengthFile - Mp3Enum.TAG_ZONE_LEN.getValue());

			byte [] bs = new byte [Mp3Enum.TAG_LEN.getValue()]; // 3 = tag ident-on
			fis.read(bs);
			tag = new String (bs); // on les transforme en String
			
			if ((tag.equals(null)) || (!tag.equals(TAG))) {
				throw new Mp3TagException();
			}
	
			bs = new byte [Mp3Enum.TITLE_LEN.getValue()]; // Title
			fis.read(bs);
			title = new String (bs).trim(); // trim pour enlever les blancs
	
			bs = new byte [Mp3Enum.AUTHOR_LEN.getValue()]; // Artist
			fis.read(bs);
			author = new String (bs).trim();
		
			bs = new byte [Mp3Enum.ALBUM_LEN.getValue()]; // Album
			fis.read(bs);
			album = new String (bs).trim(); 
			
			bs = new byte [Mp3Enum.YEAR_LEN.getValue()]; // Year
			fis.read(bs);
			year = new String (bs).trim(); 

			bs = new byte [Mp3Enum.COMMENT_LEN.getValue()]; // Comment
			fis.read(bs);
			comment = new String (bs).trim(); 
			
			bs = new byte [Mp3Enum.STYLE_LEN.getValue()]; // Genre
			fis.read(bs);
			style = (int)bs[0]; 
		} 
		catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fis !=null) {
					fis.close();	
				}
			}
			catch  (IOException e) {
				e.printStackTrace();
			}
		}
	} // méthode read

	@Override
	public String toString() {
		return "Mp3TagManager [title=" + title + ", author=" + author + ", album=" + album + ", year=" + year
				+ ", style=" + style + ", comment=" + comment + "]";
	}

	private String padAndLimit (String s, int lenMax) {
		if (s.length() > lenMax) {
			s = s.substring(0, lenMax);
		}
		
		while (s.length() < lenMax) {
			s = s+ " ";
		}
		return s;
	}
	
	/*public static void main(String[] args) {
		String a = new Mp3TagManager().padAndLimit( "0123456789", 15);
		System.out.println(a + "x");
	} */

} 
