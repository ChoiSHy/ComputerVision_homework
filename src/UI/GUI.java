package UI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Filter.*;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

public class GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private Filter filter;
	private BufferedImage bi;

	private JLabel lbl;

	private JButton run_btn;
	private JButton open_btn;
	
	private JFileChooser fc;

	public GUI() {
		fc= new JFileChooser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 500, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel.add(panel_2);

		String[] clist = { "Mean 3*3", "Mean 5*5", "Median 3*3", "Median 5*5", "Laplace 3*3" };

		comboBox = new JComboBox<>(clist);
		panel.add(comboBox);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel.add(panel_1);

		open_btn = new JButton("불러오기");
		open_btn.addActionListener(this);
		panel.add(open_btn);

		run_btn = new JButton("실행");
		run_btn.addActionListener(this);
		panel.add(run_btn);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == run_btn) {
			int sig = comboBox.getSelectedIndex();
			if(lbl!=null)
				contentPane.remove(lbl);
			switch (sig) {
			case 0:
				filter = new Mean_filter(3, bi);
				break;
			case 1:
				filter = new Mean_filter(5, bi);
				break;
			case 2:
				filter = new Median_filter(3, bi);
				break;
			case 3:
				filter = new Median_filter(5, bi);
				break;
			case 4:
				filter = new Laplacian_filter(bi);
				break;
			}
			filter.filtering();

			ImageIcon img = new ImageIcon(filter.arr2im());
			lbl = new JLabel(img);
			contentPane.add(lbl, BorderLayout.CENTER);
			pack();
			setVisible(true);
		}
		else {
			if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				try {
					System.out.println(fc.getSelectedFile().toString());
					bi = ImageIO.read(fc.getSelectedFile());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
