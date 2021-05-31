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


@SuppressWarnings("serial")
public class Imagine extends JFrame {
	
	private JPanel contentPane;
    File targetFile;
    public BufferedImage targetImg;
    
    public static  JPanel panel_1;
    static Imagine frame = new Imagine();
    
    static int index_list;
    String[] Elemente = new String[9];
    JLabel jl=null;

    Vector<Rectangle> v_rect = new Vector<Rectangle>();
    Vector<String> v_name = new Vector<String>();
    
    Boolean flag_browse= false;
    
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
		    frame.setResizable(false);
		} catch (Exception e) {
		    e.printStackTrace();
		}
 
    }


	/**
     * Create the frame.
     * @return 
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
       
       JButton btnNext= new JButton("Undo ");
       btnNext.setBackground(Color.LIGHT_GRAY);
       btnNext.setFont(new Font("Times New Roman", Font.BOLD, 24));
       btnNext.setBounds(246, 11, 184, 39);
     
       contentPane.add(btnNext);
       
       JButton btnSaveButton = new JButton("Save");
       btnSaveButton.setBackground(Color.LIGHT_GRAY);
       btnSaveButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
       btnSaveButton.setBounds(457, 11, 184, 39);
       contentPane.add(btnSaveButton);
       //**********************************

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
  		
  		ListEl.addListSelectionListener(new ListSelectionListener() {
  			@Override
  			public void valueChanged(ListSelectionEvent e) {
  				
  				index_list=ListEl.getSelectedIndex();
  			}
  		});
  		
  		//**********************************************
       
     
       
       //ADD ACTION LISTENERS FOR BUTTONS // 
       //**********************************
       btnBrowse.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   flag_browse=true;
        	   Browse browse= new Browse();
        	   browse.browseButtonActionPerformed(e);  
           }
       });
       
       btnNext.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		
          		if( !v_rect.isEmpty() && !v_name.isEmpty() )
          		{
          			v_rect.remove(v_rect.lastElement());
          			v_name.remove(v_name.lastElement());
          		}
          		frame.repaint();
          	}
       });
       btnSaveButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	  ///// de facut /////
           	Scrie s= new Scrie(); 
        	s.scriereFisier(v_rect, v_name);
           }
       });
      //*************************************
       
     //MOUSE LISTENER//
       //*******************************  

  		panel_1.addMouseListener(new MouseAdapter() {
      	    
              public void mouseClicked(MouseEvent e) {
     
	              countClick++;
	              if(countClick==2)
	                  {
	
	                  	h=Math.abs(y-e.getY());
	                  	w=Math.abs(x-e.getX());
	
	                  	v[0]=x; v[1]=y;	v[2]=w;	v[3]=h;
	                  	
	                  	if(flag_browse){
		                	v_rect.add(new Rectangle(v[0],v[1],v[2],v[3]));
		                	v_name.add(Elemente[index_list]);
	                  	}

	                  	countClick= 0;
	                  	
	                  	frame.repaint();                
	                   	
	                  }
	                  else if(countClick==1){
	                  	
	                  	x=e.getX();
	                  	y=e.getY();
	                  		
	                  	v[0]=x; v[1]=y; v[2]=w; v[3]=h;
	                  	
	                  }
              }
                
      }); }
      //************************************
    
    
  //PAINT// 
    //**************************************
    
//    public static boolean fitsInside(Rectangle rec1, Rectangle rec2) {
//
//    	return (rec1.width < rec2.width && rec1.height < rec2.height );        
//    }
    
    @Override
    public  void paint(Graphics g) {
    	super.paint(g);
    	
        Graphics2D g2d = (Graphics2D) g;
    	Stroke stroke1 = new BasicStroke(5f);
    	g2d.setColor(new Color((index_list*index_list*index_list)%256,100,15*index_list));
    	g2d.setStroke(stroke1);
    	g2d.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 20));
   
    	if((v[2]!=0 && v[3]!=0 ) && flag_browse) 
    			//&& fitsInside( new Rectangle(v[0]+15,v[1]+90,v[2],v[3]) ,panel_1.getBounds()))
    	{
    		for(int i=0; i<v_name.size();i++){
	    		g.drawString(v_name.elementAt(i),v_rect.elementAt(i).x+15,v_rect.elementAt(i).y+80);
	    		g2d.drawRoundRect(v_rect.elementAt(i).x+15,v_rect.elementAt(i).y+90,v_rect.elementAt(i).width,
	    				v_rect.elementAt(i).height,10, 10);
    		}
    		
    	}
       }
   //*******************************************
    
   }
