package proiect;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;


/** Clasa <b>Browse</b> creata pentru incarcarea fisierelor pe gui.
 * 
 *  <b>basePath</b> - variabila care stocheaza calea catre fisierele pentru upload.                  
 *  
 *  
 *        
 * @author Neagu Irina Elena 
 *
 */
public class Browse {
	 private  final String basePath =
	    		"D:/PIP-imagini"; //folder poze
	private static JLabel jl=null;
	  
    public BufferedImage rescale(BufferedImage originalImage)
    {
        BufferedImage resizedImage = new BufferedImage(900,650, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 900,650, null);    
        g.dispose();
        return resizedImage;
    }
    
    
    public void setTarget(File reference)
    {
        try {
        	
        	Imagine.frame.targetFile = reference;
        	Imagine.frame.targetImg = rescale(ImageIO.read(reference));
        } catch (IOException ex) {
            Logger.getLogger(Imagine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Imagine.panel_1.setLayout(new BorderLayout(0, 0));
        if(this.jl != null)
        {
        	  Imagine.panel_1.remove(this.jl);
        }
        	
         jl= new JLabel(new ImageIcon(Imagine.frame.targetImg));
         Imagine.panel_1.add(jl); 
         Imagine.frame.setVisible(true);
        
    }
     void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
        JFileChooser fc = new JFileChooser(basePath);
        fc.setFileFilter(new JPEGImageFileFilter());
        int res = fc.showOpenDialog(null);
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                setTarget(file);
            }
           } catch (Exception iOException) {
           }
       }

}
