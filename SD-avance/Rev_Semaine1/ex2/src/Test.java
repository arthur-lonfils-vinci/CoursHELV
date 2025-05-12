public class Test {
	public static void main(String[] args) {
		ControleDAcces c= new ControleDAcces();
		Employe cd= new Employe("Damas","Christophe");
		Employe jvm= new Employe("Vander Meulen","Jos�");
		Badge b1= new Badge(245678);
		Badge b2= new Badge(4528);
		c.donnerBadge(b1, cd);
		c.donnerBadge(b2, jvm);
		if(!c.estDansBatiment(cd)&&!c.estDansBatiment(jvm))
			System.out.println("ok");
		else System.out.println("ko");
		c.entrerBatiment(b1);
		if(c.estDansBatiment(cd)&&!c.estDansBatiment(jvm))
			System.out.println("ok");
		else System.out.println("ko");
		c.entrerBatiment(b2);
		if(c.estDansBatiment(cd)&&c.estDansBatiment(jvm))
			System.out.println("ok");
		else System.out.println("ko");
		c.sortirBatiment(b1);
		if(!c.estDansBatiment(cd)&&c.estDansBatiment(jvm))
			System.out.println("ok");
		else System.out.println("ko");
	}
	
}
