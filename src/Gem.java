import java.util.ArrayList;
import java.util.Random;

public class Gem
{
	public static ArrayList<Gem> ForGem = new ArrayList<Gem>();
	public static ArrayList<Gem> DexGem = new ArrayList<Gem>();
	public static ArrayList<Gem> IntGem = new ArrayList<Gem>();
	
	
	public String name;
	public int force;
	public int dex;
	public int intell;
	public int level;
	
	public Gem(String name, int force, int dex, int intell, int level)
	{
		this.name = name;
		this.force = force;
		this.dex = dex;
		this.intell = intell;
		this.level = level;
	}
	
	public static void AddGem(String name, int force, int dex, int intell, int level)
	{
		Gem newGem = new Gem(name, force, dex, intell, level);
		if (force != 0)
		{
			ForGem.add(newGem);
		}
		if (dex != 0)
		{
			DexGem.add(newGem);
		}
		if (intell != 0)
		{
			IntGem.add(newGem);
		}
	}
	
	public static void Debug()
	{
		System.out.println("Gem Force Total: " + ForGem.size());
		System.out.println("Gem Dex Total: " + DexGem.size());
		System.out.println("Gem Int Total: " + IntGem.size());
	}
	
	public static Gem RandomGem()
	{
		int max = ForGem.size() + DexGem.size() + IntGem.size();
		if (max <= 0)
			return null;
		Random rand = new Random();
		ArrayList<Gem> genSelect = new ArrayList<Gem>();
		int rdFor = rand.nextInt(ForGem.size());
		genSelect.add(ForGem.get(rdFor));
		int rdDex = rand.nextInt(DexGem.size());
		genSelect.add(DexGem.get(rdDex));
		int rdInt = rand.nextInt(IntGem.size());
		genSelect.add(IntGem.get(rdInt));
		int rd = rand.nextInt(genSelect.size());
		return genSelect.get(rd);
	}
	
	public static Gem RamdomGemByAscendancy(Ascendancy acs)
	{
		int max = ForGem.size() + DexGem.size() + IntGem.size();
		if (max <= 0)
			return null;
		Random rand = new Random();
		ArrayList<Gem> genSelect = new ArrayList<Gem>();
		if (acs.force != 0)
		{
			int rdFor = rand.nextInt(ForGem.size());
			genSelect.add(ForGem.get(rdFor));
			rdFor = rand.nextInt(ForGem.size());
			genSelect.add(ForGem.get(rdFor));
		}
		if (acs.dex != 0)
		{
			int rdDex = rand.nextInt(DexGem.size());
			genSelect.add(DexGem.get(rdDex));
			rdDex = rand.nextInt(DexGem.size());
			genSelect.add(DexGem.get(rdDex));
		}
		if (acs.intell != 0)
		{
			int rdInt = rand.nextInt(IntGem.size());
			genSelect.add(IntGem.get(rdInt));
			rdInt = rand.nextInt(IntGem.size());
			genSelect.add(IntGem.get(rdInt));
		}
		if (genSelect.size() <= 0)
			return null;
		int rd = rand.nextInt(genSelect.size());
		return genSelect.get(rd);
	}
	
}
