package tools;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.MainFrame;
//背景类
public class GroundPanel extends JPanel{
	private ImageIcon icon;  
	private Image img;
	
	public GroundPanel(){   
		icon=new ImageIcon(MainFrame.class.getResource("/images/interface.jpg" ));  
		img=icon.getImage();  
	}   
	public void paintComponent(Graphics g)  
	{   
		super.paintComponent(g);  
		g.drawImage(img,0,0,null );  
	}   
}

