package hashcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Hashcode {
    
    public static Slide readInput(){
        InputStream inputstream = null;
        try{
            inputstream = new FileInputStream(System.getProperty("java.class.path")+"/"+"A.txt");
        }catch(FileNotFoundException e) {}
		
	BufferedReader r = new BufferedReader(new InputStreamReader(inputstream));
	String line;
	int size = 0;
	String[] inString;
        Slide slide = new Slide();
	try {
            if((line = r.readLine()) != null){
		inString = line.split("\\s+");
                slide = new Slide(inString[0].charAt(0), Integer.parseInt(inString[1]), Arrays.copyOfRange(inString, 2, inString.length));
		String str = "";
		for(int i=0; i<inString.length;i+=1) {
                    str+=inString[i];
		}
            }
	}catch (IOException e) {}
        finally{
            return slide;
        }
    }

    public static void main(String[] args){
        Slide[] show = new Slide[4];
        for(int i=0; i<4; i++){
            show[i]=readInput();
        }
        Slide[] out = new Slide().interestCalculator(show);
    }
    
}
