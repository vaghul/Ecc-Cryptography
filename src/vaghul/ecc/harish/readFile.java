package vaghul.ecc.harish;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class readFile {

	

    public String handleFile(File file, Charset encoding)
            throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in, encoding);
             // buffer for efficiency
             Reader buffer = new BufferedReader(reader)) {
        	int r;
            char ch;
            String value="";
            while ((r = reader.read()) != -1) {
            	ch = (char) r;
               // System.out.println("Character " + ch);
                value= value+ch;
            }
            return value;
        }
    }

/*    public String handleCharacters(Reader reader)
            throws IOException {
        int r;
        char ch;
        String value="";
        
        while ((r = reader.read()) != -1) {
        	ch = (char) r;
            System.out.println("Character " + ch);
            value= value+ch;
        }
        return value;
    }
*/}
