import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

// main class
public class RenameAll {

	// main method
	public static void main( String[] args ) {
		String path = JOptionPane.showInputDialog("Give me a path: ");

		// if path is valid, rename all files, else not
		if ( pathIsValid( path ) ) {
			renameAllFiles( path );
		} else {
			System.out.println( "This path isn't exists or directory" );
		}
	}

	// path is valid? and is directory?
	public static boolean pathIsValid( String path ) {
		File temp = new File( path );
		return ( temp.exists() && temp.isDirectory() );
	}

	// rename name to ___name file or directory
	public static void rename( File file ) {
		File tempFile;
		String name = file.getName(), newName;

		// if the name hasn't ____ then i will to rename
		if ( ! name.substring( 0, 3 ).equals( "___" ) ) {
			newName = "___" + name;
			tempFile = new File( slashToString( file.getParent() ) + newName );
			file.renameTo( tempFile );
			System.out.println( name + " renamed to " + newName );
		}
	}

	// rename the directory content
	public static void renameAllFiles( String path ) {
		File content[], file = new File( path );
		if ( pathIsValid( path ) ) {
			content = file.listFiles();
			for ( File fileItem : content ) {
				renameAllFiles( fileItem.getAbsolutePath() );
			}
		}
		rename( file );
	}

	// add slash to string
	public static String slashToString( String text ) {
		if ( text.charAt( text.length() - 1 ) != '/' ) {
			return text.concat( "/" );
		}
		return text;
	}
}
