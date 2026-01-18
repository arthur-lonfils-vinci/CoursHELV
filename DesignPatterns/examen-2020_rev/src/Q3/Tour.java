package Q3;

import java.awt.*;
import javax.swing.*;

public class Tour extends JPanel {

	private static final long serialVersionUID = 1L;
	private int top;

	public Tour(boolean first, int size) {
		setLayout(new GridLayout(size, 1));
		if (!first) {
			JButton b;
			JPanel p;
			for (int i = 0; i < size; i++) {
				b = new JButton(" ");
				b.setBackground(Color.white);
				p = new JPanel();
				p.add(b);
				add(p);
			}
			top = size;
			return;
		}
		JButton[] a = new JButton[7];
		a[0] = new JButton(" un ");
		a[0].setBackground(Color.red);
		a[1] = new JButton("   deux   ");
		a[1].setBackground(Color.green);
		a[2] = new JButton("       trois       ");
		a[2].setBackground(Color.yellow);
		a[3] = new JButton("         quatre         ");
		a[3].setBackground(new Color(120, 120, 255));
		a[4] = new JButton("                cinq                ");
		a[4].setBackground(Color.magenta);
		a[5] = new JButton("                     six                     ");
		a[5].setBackground(Color.red);
		a[6] = new JButton("                       sept                       ");
		a[6].setBackground(Color.green);
		JPanel p;
		for (int i = 0; i < size; i++) {
			p = new JPanel();
			p.add(a[i]);
			add(p);
		}
		top = 0;
	}

	public void deplacer(Tour to) {
		to.top--;
		Component disk = getComponent(top);
		remove(top);
		Component blank = to.getComponent(to.top);
		to.remove(to.top);
		add(blank, top);
		validate();
		to.add(disk, to.top);
		to.validate();
		top++;
		repaint();
		to.repaint();
	}
}