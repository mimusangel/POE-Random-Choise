import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

public class Main implements ActionListener
{
	JFrame frame;
	JCheckBox scionCheck;
	JCheckBox gemByClass;
	JButton randAscendancy;
	JButton randGem;
	JButton selectAscendancy;
	JLabel resultClass;
	JLabel resultAscendancy;
	JLabel resultGem;
	
	Ascendancy selectedAscendancy = null;
	Gem	selectedGem = null;
	
	public Main()
	{
		frame = new JFrame();
		frame.setTitle("POE Random Choise - by MimusAngel");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	    frame.setLayout(new GridLayout(6, 2));
	    frame.add(scionCheck = new JCheckBox("Disable Scion", true));
	    frame.add(gemByClass = new JCheckBox("Rand Gem by Class", true));
	    frame.add(randAscendancy = new JButton("Rand Ascendancy"));
	    randAscendancy.addActionListener(this);
	    frame.add(randGem = new JButton("Rand Gem"));
	    randGem.addActionListener(this);
	    frame.add(selectAscendancy = new JButton("Select Ascendancy"));
	    selectAscendancy.addActionListener(this);
	    frame.add(resultClass = new JLabel(" "));
	    frame.add(new JLabel("Class:"));
	    frame.add(resultClass = new JLabel("-"));
	    frame.add(new JLabel("Ascendancy:"));
	    frame.add(resultAscendancy = new JLabel("-"));
	    frame.add(new JLabel("Gem:"));
	    frame.add(resultGem = new JLabel("-"));
	    frame.pack();
	    frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		try
		{
			loadGems();
			loadAscendancy();
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		} finally
		{
			Gem.Debug();
			Ascendancy.Debug();
			new Main();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == selectAscendancy)
		{
			selectedAscendancy = (Ascendancy)JOptionPane.showInputDialog(
				frame,
				"Select Ascendancy:",
				"Ascendancy",
				JOptionPane.PLAIN_MESSAGE,
				null,
				Ascendancy.Ascendancy.toArray(),
				Ascendancy.Ascendancy.get(0).name
			);
			if (selectedAscendancy != null)
			{
				resultClass.setText(selectedAscendancy.classe);
				resultAscendancy.setText(selectedAscendancy.name);
			}
			else
			{
				resultClass.setText("Error");
				resultAscendancy.setText("Error");
			}
		}
		else if (arg0.getSource() == randAscendancy)
		{
			selectedAscendancy = Ascendancy.RandomAscendancy(scionCheck.isSelected());
			if (selectedAscendancy != null)
			{
				resultClass.setText(selectedAscendancy.classe);
				resultAscendancy.setText(selectedAscendancy.name);
			}
			else
			{
				resultClass.setText("Error");
				resultAscendancy.setText("Error");
			}
		}
		else if (arg0.getSource() == randGem)
		{
			if (gemByClass.isSelected())
			{
				if (selectedAscendancy != null)
				{
					selectedGem = Gem.RamdomGemByAscendancy(selectedAscendancy);
				}
				else
				{
					// Pas de class Choisi
					JOptionPane.showMessageDialog(frame, "No Class Selected");
				}
			}
			else
			{
				selectedGem = Gem.RandomGem();
			}
			if (selectedGem != null)
			{
				resultGem.setText(selectedGem.name + "(Lvl: " + selectedGem.level + ")");
			}
			else
			{
				resultGem.setText("Error");
			}
		}
		
	}
	
	public static void loadGems() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("Gems"));
		String line;
		while ((line = reader.readLine()) != null)
		{
			String args[] = line.split(":");
			if (args.length == 5)
			{
				int force = Integer.parseInt(args[1]);
				int dex = Integer.parseInt(args[2]);
				int intell = Integer.parseInt(args[3]);
				int level = Integer.parseInt(args[4]);
				Gem.AddGem(args[0], force, dex, intell, level);
			}
		}
		reader.close();
	}
	
	public static void loadAscendancy() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("Ascendancy"));
		String line;
		while ((line = reader.readLine()) != null)
		{
			String args[] = line.split(":");
			if (args.length == 5)
			{
				int force = Integer.parseInt(args[2]);
				int dex = Integer.parseInt(args[3]);
				int intell = Integer.parseInt(args[4]);
				Ascendancy.AddAscendancy(args[1], args[0], force, dex, intell);
			}
		}
		reader.close();
	}
	
}
