package Games;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class XO extends JFrame implements ActionListener {
	private JButton[] button = new JButton[9];
	private JButton pa, ex;
	JButton b;
	public boolean end = false;
	public boolean notYet = false;

	public XO() {
		setLayout(new GridLayout(4, 3));
		for (int i = 0; i < 9; i++) {

			button[i] = new JButton("");
			add(button[i]);
			button[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					b = (JButton) e.getSource();
					if (b.getText().equals("") && end == false) {
						b.setText("X");
						notYet = true;
						if (win() == true) {
							JOptionPane.showMessageDialog(null, "You win the game!");
							end = true;
						}
					}

					if (notYet == true && end == false) {
						Random ran = new Random();
						boolean s = false;
						int count = 1;
						while (!s) {
							int r = ran.nextInt(9);

							if (button[r].getText().equals("")) {
								button[r].setText("O");
								s = true;
							}
							count++;
							if (count == 9)
								break;
						}
						if (win() == true) {
							JOptionPane.showMessageDialog(null, "Computer wins!");
							end = true;
						}

					}

					notYet = false;
				}

			});
		}
		pa = new JButton("Play Again");
		add(pa);
		pa.addActionListener(this);
		ex = new JButton("Exit");
		add(ex);
		ex.addActionListener(this);

	}

	public boolean win() {
		if (checkAdjucent(0, 1) && checkAdjucent(1, 2))
			return true;
		else if (checkAdjucent(3, 4) && checkAdjucent(4, 5))
			return true;
		else if (checkAdjucent(6, 7) && checkAdjucent(7, 8))
			return true;
		else if (checkAdjucent(0, 3) && checkAdjucent(3, 6))
			return true;
		else if (checkAdjucent(1, 4) && checkAdjucent(4, 7))
			return true;
		else if (checkAdjucent(2, 5) && checkAdjucent(5, 8))
			return true;
		else if (checkAdjucent(0, 4) && checkAdjucent(4, 8))
			return true;
		else if (checkAdjucent(2, 4) && checkAdjucent(4, 6))
			return true;
		else
			return false;
	}

	public boolean checkAdjucent(int a, int b) {
		if (button[a].getText().equals(button[b].getText()) && !button[a].getText().equals(""))
			return true;
		else
			return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pa) {
			for (int i = 0; i < 9; i++)
				button[i].setText("");
			end = false;
			notYet = false;
		} else if (e.getSource() == ex)
			System.exit(0);
	}

	public static void main(String[] args) {
		XO a = new XO();
		a.setSize(600, 600);
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
		a.setVisible(true);
	}

}
