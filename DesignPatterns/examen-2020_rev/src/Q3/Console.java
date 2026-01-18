package Q3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console implements Vue {
	private Execution execution;

	public Console(Execution execution) {
		this.execution = execution;
		execution.addVue(this);
	}

	public void go() {
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			if (!execution.hasNext())
				break;
			try {
				clavier.readLine();
			} catch (IOException e) {
				System.err.println("probl�me de lecture au clavier");
				break;
			}
			execution.execute();
		}
	}

	public void update(DeplacementDisque d) {
		new ActionConsole(d).effectuer();

	}
}
