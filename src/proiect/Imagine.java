package proiect;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import javax.swing.JFrame;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;

/** Clasa <b>Imagine</b> creata pentru dezvoltarea frame-ului si a partii de ActionListeners pentru 
 *  elementele de pe GUI( butoane,lista de elemente,click-uri la nivel de Jpanel).  
 *    
 * @author Neagu Irina Elena 
 *
 */

@SuppressWarnings("serial")
public class Imagine extends JFrame { 
	
    /**
     * <b>contentPane</b> - JPanel ce incadreaza tot continutul(butoane,lista,panel_1).
     */
    private JPanel contentPane; 
	
    File targetFile;
    public BufferedImage targetImg;
    
    /**
     *  <b>panel_1</b> - JPanel ce incadreaza doar imaginea incarcata pe GUI .
     */
    public static  JPanel panel_1; 
  
    
    /**
     *   <b>frame</b> - definit ca obiect de tipul clasei principale  <b>Imagine</b>
     */
    static Imagine frame = new Imagine();
    
    /**
	 * <b>index_list</b> - la selectarea unui element din lista ,variabila index_list preia indexul 
	 *                     de pozitionare a componentei in JList pentru o folosire ulterioara a acestuia.
	 */
    static int index_list;
    
    /**
	 * <b>Elemente</b> - vector cu elemente de tipul String care socheaza numele componentelor 
	 *                  vizate in imagini pentru incadrare. 
	 */
    String[] Elemente = new String[9];
    JLabel jl=null;
    
    /**
	 * <b>v_rect</b> - vector care stocheaza obiecte de tipul Rectangle(pentru coordonatele dreptunghiurilor
	 *                 desenate pe imagine)
	 */
    Vector<Rectangle> v_rect = new Vector<Rectangle>();
    
    /**
	 * <b>v_name</b> - vector care stocheaza elemente de tipul String(numele elementelor din lista)
	 */
    Vector<String> v_name = new Vector<String>();
    
    /** 
     *   <b>flag_browse</b> - flag-ul pus pe true(dupa apasarea butonului "Browse" si incarcarea imaginii)
     *                        permite desenarea dreptunghiurilor in Jpanel.  
     */
    Boolean flag_browse= false;
    
  //***************
    /** 
     *  <b>countClick</b> - variabila care contorizeaza numarul de click-uri.                    
     */
	int countClick= 0;
	
	 /** 
      *  <b>x</b> - coordonata x preluata dupa click                   
      */
    static int x= 0;
    /** 
     *  <b>y</b> - coordonata y preluata dupa click                   
     */
    static int y= 0;
    
    /** 
     *  <b>w</b> - latime dr. calculata dupa efectuarea a 2 click-uri                
     */
    static int w= 0;
    
    /** 
     *  <b>h</b> - inaltime dr. calculata dupa efectuarea a 2 click-uri                
     */
    static int h= 0;

 //*************************   
    
    /**
     * Launch the application.
     * Componenta <b>main</b> in care sun facute modificari asupra frame-ului( vizibil, redimensionabil).
     *@param args
     */

    public static void main(String[] args) {

		try {
		    frame.setVisible(true);
		    frame.setResizable(false);
		} catch (Exception e) {
		    e.printStackTrace();
		}
 
    }


	/**
	 *  Constructorul  <b>Imagine</b> este dezvoltat pentru crearea frame-ului(butoane,Jlist-ul care contine elementele de selectat),
	 *                                initializarea tuturor componentelor si adaugarea lor pe GUI.
     * 
     * @author Neagu Irina Elena 
     *
     * Create the frame.
     * 
     */
    public  Imagine() {
    	
    	  // ADD COMPONENTS TO FRAME
  		//**********************************
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

         panel_1 = new JPanel();
         panel_1.setBounds(12, 56, 872, 576);
         contentPane.add(panel_1);
         panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0,0), 1, true));
         panel_1.setLayout(null);
       
         JButton btnUndo= new JButton("Undo ");
         btnUndo.setBackground(Color.LIGHT_GRAY);
         btnUndo.setFont(new Font("Times New Roman", Font.BOLD, 24));
         btnUndo.setBounds(246, 11, 184, 39);
     
         contentPane.add(btnUndo);
       
         JButton btnSaveButton = new JButton("Save");
         btnSaveButton.setBackground(Color.LIGHT_GRAY);
         btnSaveButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
         btnSaveButton.setBounds(457, 11, 184, 39);
         contentPane.add(btnSaveButton);
         
         //********************************
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
  		 
  	    /**
  	     *  <b>ListEl</b> - JList pentru stocarea elementelor din vectorul <b>Elemente</b>. 
  	     */	
  		
  		// Create JList to Show Elements Name
  		 JList ListEl = new JList(Elemente); 
  		
  		 ListEl.setFont(new Font("Courier New", Font.ITALIC+ Font.BOLD , 20));// editare text integral lista
  		 //ListEl.setSelectedIndex(0);
  		 ListEl.setSelectionBackground(new Color(7, 90, 180));//culoare bg selectie curenta cu rgb (responsive)
  		 ListEl.setBounds(900, 56, 150,250);
  		 ListEl.setVisible(true);
  		 contentPane.add(ListEl);
  		 
   	    /**
   	     *  <b>valueChanged</b> - public void function care implementeaza functionalitatea 
   	     *                        de addListSelectionListener aplicata listei ListEl;
   	     *                        in momentul selectarii unui element din JList este memorat inndexul
   	     *                        acestui element in variabila index_list. 
   	     */	
  		 
  		ListEl.addListSelectionListener(new ListSelectionListener() {
  			@Override
  			public void valueChanged(ListSelectionEvent e) {
  				
  			   index_list=ListEl.getSelectedIndex();
  			}
  		});
  		
  		 //***************************************************
                //ADD ACTION LISTENERS FOR BUTTONS // 
        //****************************************************
       
  		/**
   	     *  <b>actionPerformed</b> - public void function care implementeaza functionalitatea 
   	     *                           de addActionListener aplicata butonului  btnBrowse ;
   	     *                           in momentul selectarii butonului btnBrowse si upload-ului imaginii pe GUI
   	     *                           (flag_browse=true) se reactualizeaza vectorii v_name si v_rect.
   	     *                           
   	     */	
          btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        	    flag_browse=true;
        	    Browse browse= new Browse();
        	    browse.browseButtonActionPerformed(e);  
        	   
        	    v_name.removeAllElements();
        	    v_rect.removeAllElements();
           }
       });
       
          /**
     	   *  <b>actionPerformed</b> - public void function care implementeaza functionalitatea 
     	   *                           de addActionListener aplicata butonului  btnUndo ;
     	   *                           daca vectorii v_rect si v_name sunt populati se va sterge ultimul chenar desenat 
     	   *                           si se vor reactualiza imaginea si incadrarile de pe aceasta .                          
     	   */	
           btnUndo.addActionListener(new ActionListener() {
          	 public void actionPerformed(ActionEvent e) {
          		
          		if( !v_rect.isEmpty() && !v_name.isEmpty() )
          		{
          			v_rect.remove(v_rect.lastElement());
          			v_name.remove(v_name.lastElement());
          		}
          		frame.repaint();
          	}
       });
           
           /**
      	   *  <b>actionPerformed</b> - public void function care implementeaza functionalitatea 
      	   *                           de addActionListener aplicata butonului  btnSaveButton ;
      	   *                           se salveaza informatiile  
      	   */	
           
           btnSaveButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
           	Scrie s= new Scrie(); 
        	s.scriereFisier(v_rect, v_name);
           }
       });
       
       
       
       //******************************
             //MOUSE LISTENER//
      //*******************************  
       
        /**
	  	 * <b>mouseClicked</b> - public void function in care se contorizeaza click-urile 
	     *                       cu ajutorul variabilei <b>countClick</b>.
	     *                       La efectuarea a 2 click-uri oriunde la nivelul lui panel_1,
	  	 *                       sunt preluate coordonatele pentru implementarea dreptungiurilor.
	     *                                         
	  	 */

  		panel_1.addMouseListener(new MouseAdapter() {
      	    
        public void mouseClicked(MouseEvent e) {
     
	          countClick++;
	          if(countClick==2)
	            {
	
	                 h=Math.abs(y-e.getY());
	                 w=Math.abs(x-e.getX());

	                 if(flag_browse && w!=0 && h!=0 && x+w<872 && y+h<576 && y-15>0){ 
	                 //content pane size : 1084x661 pixels
	                 // panel_1 - imag size : 872x576 pixels
	                  v_rect.add(new Rectangle(x,y,w,h));
		              v_name.add(Elemente[index_list]);
	            }
	                else{
	                  	    if(y-15<0)
	                  		  {
	                  			System.out.println("text in afara");
	                  		  }
	                      System.out.println("nu este in chenar");
	                  	}

	                  	countClick= 0;
	                  	
	                  	frame.repaint();                
	                   	
	                  }
	                  else if(countClick==1){
	                  	
	                  	x=e.getX();
	                  	y=e.getY();
	                  }
              }
                
      }); }
    
    
       //************************************
                    //PAINT// 
       //*************************************
    
    
       /**
  	    * <b>paint</b> - public void function pentru creare si desenare chenar/dreptunghi 
        *                       in functie de elementele vectorilor v_name si v_rect si redesenarea 
        *                       tuturor chenarelor anterioare.                                       
  	    */
       @Override
       public  void paint(Graphics g) {
    	super.paint(g);
    	//System.out.println(frame.contentPane.getBounds());
    	//System.out.println(frame.panel_1.getBounds());
    	
        Graphics2D g2d = (Graphics2D) g;
    	Stroke stroke1 = new BasicStroke(5f);
    	g2d.setColor(new Color((index_list*index_list*index_list)%256,100,15*index_list));
    	g2d.setStroke(stroke1);
    	g2d.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 20));
   

		for(int i=0; i<v_name.size();i++){
    		g.drawString(v_name.elementAt(i),v_rect.elementAt(i).x+15,v_rect.elementAt(i).y+80);
    		g2d.drawRoundRect(v_rect.elementAt(i).x+15,v_rect.elementAt(i).y+90,v_rect.elementAt(i).width,
    				v_rect.elementAt(i).height,10, 10);
		}

       }
   //*******************************************
    
   }
