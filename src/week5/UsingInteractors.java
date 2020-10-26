package week5;


import acm.program.*;
import acm.graphics.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UsingInteractors extends GraphicsProgram{

	private static final double BOX_WIDTH = 180;
	private static final double BOX_HEIGHT = 80;
	
	
	private JTextField objName;
	private JButton addO;
	private JButton remove;
	private JButton clear;
	private Map<String, GObject> obj;
	private GObject objSelected;
	private double pX;
	private double pY;
	private double[] current;

	
	
	
	public void init() {
		obj = new HashMap<String, GObject>();
		functions();
		addActionListeners();
		addMouseListeners();
	}
	
	private void functions() {
		objName = new JTextField(20);
		objName.addActionListener(this);
		addO = new JButton("Add");
		remove = new JButton("Remove");
		clear = new JButton("Clear");
		add(new JLabel("Name"), SOUTH);
		add(objName, SOUTH);
		add(addO, SOUTH);
		add(remove, SOUTH);
		add(clear, SOUTH);
	}
	
	private void addObj(String name) {
		GCompound object = new GCompound();
		GRect rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		object.add(rect, -BOX_WIDTH/2, -BOX_HEIGHT/2);
		object.add(label,-label.getWidth(), -label.getHeight());
		add(object, getWidth()/2, getHeight() /2);
		obj.put(name, object);
		
		
	}
	private void removeObj(String name) {
		GObject obj = this.obj.get(name);
		if (obj != null) {
			remove(obj);
		}
	}
	
	private void clearObj() {
		Iterator<String> it = obj.keySet().iterator();
		while(obj.isEmpty() != true) {
			removeObj(it.next());
		}
		obj.clear();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		if(o == objName || o == addO) {
			addObj(objName.getText());
		} else if (o == remove) {
			removeObj(objName.getText());
		} else if (o == clear) {
			clearObj();
		}
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		pX = e.getX();
		pY = e.getY();
		
		objSelected = getElementAt(pX, pY);
		
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
		if (objSelected != null) {
			objSelected.move(e.getX() - objSelected.getX(), 
								e.getY()-objSelected.getY());
			
		}
	}
	
	public void mouseClickedEvent(MouseEvent e) {
		
	}
	
	
	
}
