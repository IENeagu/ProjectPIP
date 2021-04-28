package imag;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.Color;

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

@SuppressWarnings("serial")
public class imagine extends JFrame {
	
    private JPanel contentPane;
    File targetFile;
    BufferedImage targetImg;
    public JPanel panel_1;
    int pos = 0;
    private static final String basePath =
    		"C:/Users/Denisa/Desktop/PIP-imagini";
    private JButton btnNext;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    imagine frame = new imagine();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @return 
     */
    public  imagine() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 914, 692);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
       contentPane.setLayout(null);
       
       JButton btnBrowse = new JButton("Browse");
       btnBrowse.setBackground(Color.LIGHT_GRAY);
       btnBrowse.setFont(new Font("Times New Roman", Font.BOLD, 23));
       btnBrowse.setBounds(35, 11, 184, 39);
       contentPane.add(btnBrowse);
       
       panel_1 = new JPanel();
       panel_1.setBounds(12, 56, 872, 576);
       contentPane.add(panel_1);
       panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0,0), 1, true));
       panel_1.setLayout(null);
       
       btnNext = new JButton("Next ");
       btnNext.setBackground(Color.LIGHT_GRAY);
       btnNext.setFont(new Font("Times New Roman", Font.BOLD, 24));
       btnNext.setBounds(246, 11, 193, 39);
       btnNext.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		
       	}
       });
       contentPane.add(btnNext);
       
       JButton btnNewButton = new JButton("New button");
       btnNewButton.setBounds(451, 11, 169, 35);
       contentPane.add(btnNewButton);
       btnBrowse.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   browseButtonActionPerformed(e);  
        	 //  String list[]=
           }
       });
   }
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
            targetFile = reference;
            targetImg = rescale(ImageIO.read(reference));
        } catch (IOException ex) {
            Logger.getLogger(imagine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        panel_1.setLayout(new BorderLayout(0, 0));
        panel_1.add(new JLabel(new ImageIcon(targetImg))); 
        setVisible(true);
    }
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
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
        panel_1.repaint();
       }
   }
