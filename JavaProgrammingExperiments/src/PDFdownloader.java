import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFdownloader {

	
	
public static void main(String[] args) throws IOException {

			
		String newURL = inputURL();
		URL url = new URL(newURL);

		HttpURLConnection connection =	(HttpURLConnection) url.openConnection();
		
		 connection.setDoOutput(true); 
		 connection.setRequestProperty("Content-Type", "application/pdf"); 
		 connection.setRequestProperty("Connection", "Keep-Alive");
		 connection.setRequestProperty("Content-Disposition","attachment; filename=" + "yourFile.pdf"); 
		 connection.setRequestProperty("charset", "UTF-8"); 
		 //connection.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.13) Gecko/2009073021 Firefox/3.0.13");
		 connection.connect();
		
		if ( connection.getResponseCode() == 200 ) {
		    System.out.println("Connection type : " + connection.getContentType());    
		 	System.out.println("opening connection");
			InputStream in = (InputStream) connection.getInputStream(); 
			FileOutputStream fos = new FileOutputStream(new File("yourFile.pdf"));
			System.out.println("reading from resource and writing to file...");
			int length = -1;
			byte[] buffer = new byte[1024];// buffer for portion of data from connection
			while ((length = in.read(buffer)) > -1) {
			    fos.write(buffer, 0, length);
			}
			
			fos.close();
			in.close();
			System.out.println("File downloaded");
	    	}
		else {
			System.out.println("Invalid URL");
			
		}
		
}

public static String inputURL() throws IOException {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Input the PDF URL Link:");
	String synonymInput = br.readLine();
	return synonymInput;
}
}
