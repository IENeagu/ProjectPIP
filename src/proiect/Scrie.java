package proiect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Scrie {

	void scriereFisier(int[] vectorRect,String stringName)
		{
	    try{
	    	
	    	FileWriter fileWriter = new FileWriter("D:\\out.txt");
	        PrintWriter printWriter = new PrintWriter(fileWriter);
	        printWriter.print(stringName+ " : "+vectorRect[0]+" "+vectorRect[1]+" "+vectorRect[2]+ " "+ vectorRect[3]);
	        printWriter.close();
    }

	    catch (Exception e) {
	      e.getStackTrace();
	    }
	}
}
