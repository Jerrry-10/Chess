package chess;
/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 * Class to display GUI
 */
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
	public static void display()  {
		BufferedImage all= null;
		try{
			all = ImageIO.read(new File("C:\\Users\\Jerry\\eclipse-workspace\\CSC330\\src\\chess\\chess.png"));
		} catch(IOException e) {}
        Image imgs[]=new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
        for(int x=0;x<1200;x+=200){
            imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
        ind++;
        }}    
        MoveableGamePiece[][] array = PlayableChessBoard.getDeepCopyOfPieces();
        System.out.println(array[1][1]);
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

        for(int row = 0; row < 8; row++){
        	for (int col = 0; col < 8; col++) {
        		int ind = 0;
        		//System.out.println(array[row][col].getClass().getSimpleName().equals("Pawn"));
        		if (row == 0|| row == 1 || row == 6 || row == 7) {
        			if(array[row][col].getClass().getSimpleName().equals("King")){
            			ind = 0;
            		}
        			if(array[row][col].getClass().getSimpleName().equals("Queen")){
            			ind = 1;
            		}
        			if(array[row][col].getClass().getSimpleName().equals("Bishop")){
            			ind = 2;
            		}
        			if(array[row][col].getClass().getSimpleName().equals("Knight")){
            			ind = 3;
            		}
        			if(array[row][col].getClass().getSimpleName().equals("Rook")){
            			ind = 4;
            		}
        			if(array[row][col].getClass().getSimpleName().equals("Pawn")){
        			ind = 5;
        			}
        			if((array[row][col].getColor() == chess.Color.WHITE)) {
        				ind+=6;
        			}
        		g.drawImage(imgs[ind],col*64,row*64,this);
        		}
        		

}};
        }};
frame.add(pn);
frame.setDefaultCloseOperation(3);
frame.setVisible(true);
}}

/*
 *      MoveableGamePiece[][] array = PlayableChessBoard.getDeepCopyOfPieces();
        System.out.println(array[1][1]);
        //        for(int row = 0; row < 8; row++){
//        	for (int col = 0; col < 8; col++) {
//        		ind = 5;
//        	if(array[row][col].getClass().getSimpleName().equals("Pawn")){
//        		ind = 5;
//        	}
//        	g.drawImage(imgs[ind],5*54,5*64,this);
//}};
        *
        *
        *
        *
        */
