package Q3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI implements ActionListener, Vue {
	private Execution execution;
	private Tour[] tours = new Tour[3];
	private JButton suivant;

	public GUI(Execution execution) {
		this.execution = execution;
		this.execution.addVue(this);

		JFrame f = new JFrame("Tours de Hanoi");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		JPanel centre = new JPanel();
		centre.setLayout(new GridLayout(1, 3));
		for (int i = 0; i < 3; i++)
			centre.add(tours[i] = new Tour(i == 0, execution.getTaille()));
		JPanel bas = new JPanel();
		suivant = new JButton("Suivant");
		suivant.addActionListener(this);
		bas.add(suivant);

		f.getContentPane().add(centre, BorderLayout.CENTER);
		f.getContentPane().add(bas, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (execution.hasNext()) {
			execution.execute();
		}
	}

	public void update(DeplacementDisque d) {
		new ActionGUI(d, tours).effectuer();
		if (!execution.hasNext())
			suivant.setEnabled(false);
	}
}
