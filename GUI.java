package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	public static void display() {
	JFrame frame = new JFrame(); // Just startup code
    frame.setBounds(10, 10, 512, 512);  // sets the size of the board.
    frame.setUndecorated(true);
    JPanel pn=new JPanel(){
        @Override
        public void paint(Graphics g) {  // Using a graphics class to draw the board
        boolean white=true;
        for(int y= 0;y<8;y++){// Double nested loop that places white in a column.
        	for(int x= 0;x<8;x++){
        		if(white){
        			g.setColor(new Color(235,235, 208));  // This sets the color of the column to white
        		}else{
        			g.setColor(new Color(119, 148, 85));  // this sets the color of the column to green                 
        		}
            g.fillRect(x*64, y*64, 64, 64);  // This executes the color that is being set @ lines 25 and 27
            white=!white; // boolean that changes every time the loop ends.
        	}
        	white=!white; // same as above.
        	}
}};
frame.add(pn);
frame.setDefaultCloseOperation(3);
frame.setVisible(true);
}}
