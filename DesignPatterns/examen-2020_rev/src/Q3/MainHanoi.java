package Q3;

public class MainHanoi {
	public static void main(String[] args) {
		int taille = 5;
		if (args.length > 0)
			try {
				taille = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
			}
		Hanoi hanoi = Hanoi.getInstance(taille);
		hanoi.resoudre();
		final Execution execution = new Execution(hanoi);
		new Thread() {
			public void run() {
				new GUI(execution);
			}
		}.start();
		new Console(execution).go();
	}
}
