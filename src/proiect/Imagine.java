package proiect;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    public BufferedImage targetImg;
    public  JPanel panel_1;
    int pos = 0;
    private  final String basePath =
    		"D:/PIP-imagini"; //folder poze
    private JButton btnNext;
    static Imagine frame = new Imagine();
    static int index_list;
    String[] Elemente = new String[9];
    
  //***************

	int countClick= 0;
    int x= 0;
	int y= 0;
	int w= 0;
	int h= 0;
    int[] v={0,0,0,0};

 //*************************   
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        try {
            frame.setVisible(true);
            frame.setResizable(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	/**
     * Create the frame.
     * @return 
     */
    public  Imagine() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
       contentPane.setLayout(null);
       final JLabel report = new JLabel("...");
       add(report);
       
       JButton btnBrowse = new JButton("Browse");
       btnBrowse.setBackground(Color.LIGHT_GRAY);
       btnBrowse.setFont(new Font("Times New Roman", Font.BOLD, 23));
       btnBrowse.setBounds(35, 11, 184, 39);
       contentPane.add(btnBrowse);
       
       //JLIST//
       
       //********************************
         
  		Elemente[0] = "masina";
  		Elemente[1] = "semafor";
  		Elemente[2] = "trecere";
  		Elemente[3] = "interzis";
  		Elemente[4] = "cedeaza";
  		Elemente[5] = "directie";
  		Elemente[6] = "intersectie";
  		Elemente[7] = "atentie";
  		Elemente[8] = "cladire";
  			
  		
  		// Create JList to Show Elements Name
  		JList ListEl = new JList(Elemente); 
  		
  		ListEl.setFont(new Font("Courier New", Font.ITALIC+ Font.BOLD , 20));// editare text integral lista
  		//ListEl.setSelectedIndex(0);
  		ListEl.setSelectionBackground(new Color(7, 90, 180));//culoare bg selectie curenta cu rgb (responsive)
  		ListEl.setBounds(900, 56, 150,250);
  		ListEl.setVisible(true);
  		contentPane.add(ListEl);
  		
  		
  	
  		/*
  		//Sample 05: Hand-Over the JList to ScrollPane & Display
  		JScrollPane jcp = new JScrollPane(ListEl);
  		ControlHost.add(jcp);
  		ControlHost.add(label);
  		*/
  		
  		//Sample 06: Handle the JList Event, jlist - responsive
  		ListEl.addListSelectionListener(new ListSelectionListener() {
  			@Override
  			public void valueChanged(ListSelectionEvent e) {
  				
  				index_list=ListEl.getSelectedIndex();
  			}
  		});
  		
  		//**********************************************
       
       
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
       
     //MOUSE LISTENER//
       
       //*******************************  
  		panel_1.addMouseListener(new MouseAdapter() {
      	    
              public void mouseClicked(MouseEvent e) {
               
                  countClick++;
                  if(countClick==2)
                      {

                      	h=Math.abs(y-e.getY());
                      	w=Math.abs(x-e.getX());

                      	v[0]=x;
                       	v[1]=y;
                       	v[2]=w;
                       	v[3]=h;
                       	
                      	 countClick= 0;

                      	 frame.repaint();                
                       	
                      }
                      else if(countClick==1){
                      	
                      	x=e.getX();
                      	y=e.getY();

                      	v[0]=x;
                      	v[1]=y;
                      	v[2]=w;
                      	v[3]=h;
                      }
              }
                
      });
  }
      //************************************
   
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
        JLabel jl= new JLabel(new ImageIcon(targetImg));
        panel_1.add(jl); 
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
        //panel_1.repaint();
       }
  //PAINT//
    
    //**************************************
    public static boolean fitsInside(Rectangle rec1, Rectangle rec2) {

    	return (rec1.width < rec2.width && rec1.height < rec2.height);
         
    }
    @Override
    public  void paint(Graphics g) {

    	Rectangle rect1=panel_1.getBounds();
        Graphics2D g2d = (Graphics2D) g;
    	Stroke stroke1 = new BasicStroke(5f);
    	g2d.setColor(new Color(50,100,15*index_list));
    	g2d.setStroke(stroke1);
    	g2d.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 20));
    	
    	//if((v[2]!=0 && v[3]!=0 ) && fitsInside( new Rectangle(v[0]+15,v[1]+90,v[2],v[3]) ,panel_1.getBounds()))
    	if((v[2]!=0 && v[3]!=0 ) && v[0]+15 >= rect1.x && v[1]+90 >= rect1.y ) //???? conditie extra
    	{
    		g.drawString(Elemente[index_list],v[0]+15,v[1]+80);
    		g2d.drawRoundRect(v[0]+15,v[1]+90,v[2],v[3],10, 10);
    		
    	}
       }
   //******************************************
    
   }
