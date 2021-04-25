package proiect;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Rectangle;
import java.awt.Graphics;

 
public class Main {

    public static void main(String[] args) {
        new Main();
    }
    static Rectangle NullRectangle =new Rectangle(0,0);
	static Rectangle rect= NullRectangle;
	static int countClick= 0;
	
    public Main() {

        EventQueue.invokeLater(new Runnable() {
        	@Override
            public void run() {
                try {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    }

                    BufferedImage img = ImageIO.read(new File("F:\\image.jpg"));
                    final ImagePanel imgPane = new ImagePanel(img);
                    JScrollPane scrollPane = new JScrollPane(imgPane);
                    final JLabel report = new JLabel("...");

                    imgPane.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Point panelPoint = e.getPoint();
                            Point imgContext = imgPane.toImageContext(panelPoint);
                            
                            report.setText("You clicked at " + panelPoint + " which is relative to the image " + imgContext);
                            //System.out.println("You clicked at " + panelPoint + " which is relative to the image " + imgContext);
                            countClick++;
                            System.out.println( countClick);
                            
                            if(countClick==2)
                            {
                             	rect.add(e.getX(),e.getY());

                            	System.out.println("2 clk");
                             	System.out.println("Avem: "+rect.x+" "+rect.y+" "+rect.width+" "+rect.height);
                       
                            	 countClick= 0;
                            	 rect= NullRectangle;
                            
                            }
                            else if(countClick==1){
                            	rect= new Rectangle(panelPoint);
                            	
                            	System.out.println("1 clk");
                             	System.out.println("Avem: "+rect.x+" "+rect.y+" "+rect.width+" "+rect.height);

                            }
                           
                        }
                    });

                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(scrollPane);
                    frame.add(report, BorderLayout.SOUTH);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public class ImagePanel extends JPanel {

        private BufferedImage img;

        public ImagePanel(BufferedImage img) {
            this.img = img;
        }

       @Override
       public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
       }

        protected Point getImageLocation() {

            Point p = null;
            if (img != null) {
                int x = (getWidth() - img.getWidth()) / 2;
                int y = (getHeight() - img.getHeight()) / 2;
                p = new Point(x, y);
            }
            return p;

        }

        public Point toImageContext(Point p) {
            Point imgLocation = getImageLocation();
            Point relative = new Point(p);
            relative.x -= imgLocation.x;
            relative.y -= imgLocation.y;
            return relative;
        }
        
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            // Your code here
//        }
        
        public void paint(Graphics g) {
        	super.paint(g);
        	Graphics2D g2d = (Graphics2D) g;
        	
            if (img != null) {
                Point p = getImageLocation();
            g.drawImage(img, p.x, p.y, this);
            
        	Stroke stroke1 = new BasicStroke(5f);
        	g2d.setColor(Color.BLUE);
        	//g.setColor(Color.BLUE);
        	g2d.setStroke(stroke1);
 
        	if(rect.x!= 0 && rect.y!= 0 && rect.width!= 0 && rect.height!= 0 )
        		g.drawRect(rect.x,rect.y,rect.width,rect.height);
        		//g2d.drawRoundRect(rect.x,rect.y,rect.width,rect.height, 5, 5);
        	
        	// g.drawString("string", x, y);
        	
      }
        } 
    }

}

        	