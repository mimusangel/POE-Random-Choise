import java.util.ArrayList;
import java.util.Random;

public class Ascendancy
{
	public static ArrayList<Ascendancy> Ascendancy = new ArrayList<Ascendancy>();
	
	public String name;
	public String classe;
	public int force;
	public int dex;
	public int intell;
	
	public Ascendancy(String name, String classe, int force, int dex, int intell)
	{
		this.name = name;
		this.classe = classe;
		this.force = force;
		this.dex = dex;
		this.intell = intell;
	}
	
	public String toString()
	{
		return (name + "(" + classe + ")");
	}
	
	public static void AddAscendancy(String name, String classe, int force, int dex, int intell)
	{
		Ascendancy.add(new Ascendancy(name, classe, force, dex, intell));
	}
		
	public static void Debug()
	{
		System.out.println("Ascendancy Total: " + Ascendancy.size());
	}
	
	public static Ascendancy RandomAscendancy(boolean disableScion)
	{
		if (Ascendancy.size() <= 0)
			return null;
		Random rand = new Random();
		int rd = rand.nextInt(Ascendancy.size());
		Ascendancy asc = Ascendancy.get(rd);
		if (disableScion && asc.classe.equalsIgnoreCase("Scion"))
			return RandomAscendancy(disableScion);
		return asc;
	}
}
