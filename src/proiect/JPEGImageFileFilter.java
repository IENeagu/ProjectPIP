package proiect;

import java.io.File;

import javax.swing.filechooser.FileFilter;
/** Clasa <b> JPEGImageFileFilter</b> creata pentru filtrarea extensiillor fisierelor ce sunt incarcate 
 *                                    la apasarea butonului "Browse"; se permite numai incarcarea 
 *                                    imaginilor, fisire cu extensia "jpeg". 
 *                     
 *  
 *  
 *        
 * @author Neagu Irina Elena 
 *
 */

class JPEGImageFileFilter extends FileFilter implements java.io.FileFilter
{
	public boolean accept(File f)
	{
		if (f.getName().toLowerCase().endsWith(".jpeg")) return true;
		if (f.getName().toLowerCase().endsWith(".jpg")) return true;
		if(f.isDirectory())return true;
		return false;
	}
	
	public String getDescription()
	{
		return "JPEG files";
	}
}

