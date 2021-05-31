package proiect;

import java.awt.Rectangle;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

public class Scrie {

	void scriereFisier(Vector<Rectangle> v_rect, Vector<String> v_name)
		{
	    try{
	    	FileWriter fileWriter = new FileWriter("D:\\PIP-imagini\\out.txt");
	        PrintWriter printWriter = new PrintWriter(fileWriter);
	        
	    	for(int i=0; i<v_name.size(); i++)
	        	
	        	{
	        		printWriter.print(v_name.get(i)+ " ");
	        		printWriter.print("x= "+ v_rect.get(i).x+ ", " );
	        		printWriter.print("y= "+v_rect.get(i).y+ ", " );
	        		printWriter.print("w= "+v_rect.get(i).width+ ", " );
	        		printWriter.print("h= "+v_rect.get(i).height+ " " + '\n');
	        	}
	    	printWriter.close();
    }

	    catch (Exception e) {
	      e.getStackTrace();
	    }
	}
}