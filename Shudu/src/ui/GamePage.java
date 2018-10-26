package ui;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.Grid;

import contorller.Controller;
import entrance.Frame;

/**
 * @author Mualim
 * @date ����ʱ�䣺2017-3-4 ����10:19:11
 * @version 1.0
 * @parameter ������
 * @return ��Ϸҳ��
 */
public class GamePage extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5594260602765725106L;

	private JLabel numberPadOn = new JLabel();
	private JButton save = new JButton("��   ��");
	private JButton restart = new JButton("��   ��");
	private JButton back = new JButton("��   ��");
	private ArrayList<JButton> allJButton = new ArrayList<JButton>();
	private ArrayList<ArrayList<Integer>> listOfRowList = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> listOfColList = new ArrayList<ArrayList<Integer>>();;
	private ArrayList<ArrayList<Integer>> listOfAreaList = new ArrayList<ArrayList<Integer>>();;
	private ArrayList<Grid> allGrid = new ArrayList<Grid>();

	public JButton getSave() {
		return save;
	}

	public void setSave(JButton save) {
		this.save = save;
	}

	public JButton getRestart() {
		return restart;
	}

	public void setRestart(JButton restart) {
		this.restart = restart;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public ArrayList<Grid> getAllGrid() {
		return allGrid;
	}

	public void setAllGrid(ArrayList<Grid> allGrid) {
		this.allGrid = allGrid;
	}

	public ArrayList<JButton> getAllJButton() {
		return allJButton;
	}

	public void setAllJButton(ArrayList<JButton> allJButton) {
		this.allJButton = allJButton;
	}

	/**
	 * GamePage���캯��
	 * 
	 * @param controller
	 */
	public GamePage(final Controller controller) {
		setLayout(null);
		setVisible(true);
		setSize(600, 670);

		// numberPadOn����
		numberPadOn.setBounds(0, 90, 600, 580);
		add(numberPadOn);
		controller.setNumberPadOn(numberPadOn);
		/*numberPadOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON3) {
					JLabel numberPadOn = controller.getGamePage()
							.getNumberPadOn();
					if (controller.getNumberPad() != null) {
						numberPadOn.remove(controller.getNumberPad());
						numberPadOn.revalidate();
						numberPadOn.repaint();
					}
					if (controller.getCurrentGird() != null) {
						controller.getCurrentGird().setBorder(
								new LineBorder(Color.BLACK));
						controller.getCurrentGird().revalidate();
						controller.getCurrentGird().repaint();
					}
				}
			}
		});*/

		// ��listOfList����ձ�
		for (int i = 0; i < 9; i++) {
			listOfRowList.add(new ArrayList<Integer>());
			listOfColList.add(new ArrayList<Integer>());
			listOfAreaList.add(new ArrayList<Integer>());
		}

		// ��ť��Сλ��
		save.setBounds(100, 20, 100, 50);
		save.setFont(new Font("����", Font.BOLD, 16));
		restart.setBounds(250, 20, 100, 50);
		restart.setFont(new Font("����", Font.BOLD, 16));
		back.setBounds(400, 20, 100, 50);
		back.setFont(new Font("����", Font.BOLD, 16));

		// ���ð�ť
		add(save);
		allJButton.add(save);
		add(restart);
		allJButton.add(restart);
		add(back);
		allJButton.add(back);

		// ��ť����
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					saveGame(controller.getFilePath().substring(
							controller.getFilePath().lastIndexOf("\\") + 1));
					// ��ʾ����ɹ�
				}
			}
		});
		restart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					deleteSave(controller.getFilePath().substring(
							controller.getFilePath().lastIndexOf("\\") + 1));
					Frame baseFrame = controller.getFrame();
					baseFrame.remove(controller.getGamePage());
					controller.setGamePage(null);
					baseFrame.add(new GamePage(controller));
					baseFrame.revalidate();
					baseFrame.repaint();
				}
			}
		});
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					Frame baseFrame = controller.getFrame();
					baseFrame.remove(controller.getGamePage());
					controller.setGamePage(null);
					controller.setFilePath("");
					baseFrame.add(new FirstPage(controller));
					baseFrame.revalidate();
					baseFrame.repaint();
				}
			}
		});

		// ������
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int d = i * 3 + j + 1;
				JLabel lbcol = new JLabel("��" + String.valueOf(d) + "��");
				lbcol.setBounds(90 + i * 20 + (d - 1) * 50, 90, 50, 50);
				lbcol.setFont(new Font("����", Font.PLAIN, 18));
				lbcol.setHorizontalAlignment(JLabel.CENTER);
				lbcol.setVerticalAlignment(JLabel.CENTER);
				add(lbcol);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int d = i * 3 + j + 1;
				JLabel lbcol = new JLabel("��" + String.valueOf(d) + "��");
				lbcol.setBounds(20, (d - 1) * 50 + i * 20 + 160, 50, 50);
				lbcol.setFont(new Font("����", Font.PLAIN, 18));
				lbcol.setHorizontalAlignment(JLabel.CENTER);
				lbcol.setVerticalAlignment(JLabel.CENTER);
				add(lbcol);
			}
		}

		// ������
		for (int areaNumberV = 0; areaNumberV < 3; areaNumberV++) {
			int areaY = 160 + areaNumberV * 170;
			for (int areaNumber = 0; areaNumber < 3; areaNumber++) {
				int areaX = 90 + areaNumber * 170;
				for (int row = 0; row < 3; row++) {
					int y = areaY + row * 50;
					for (int col = 0; col < 3; col++) {
						int rowP = row + 1 + areaNumberV * 3;
						int colP = col + 1 + areaNumber * 3;
						int areaP = areaNumber + 1 + areaNumberV * 3;
						int numberP = readNumberFromFile(
								controller.getFilePath(), String.valueOf(rowP)
										+ "," + String.valueOf(colP)); // ����ԭʼֵ
						boolean still = false;
						if (numberP != 0) {
							still = true;
						} else if (controller.isLoad()) {
							numberP = readNumberFromFile(
									controller.getSavedFilePath(),
									String.valueOf(rowP) + ","
											+ String.valueOf(colP)); // �����������Ϸ
						}

						listOfRowList.get(rowP - 1).add(numberP);
						listOfColList.get(colP - 1).add(numberP);
						listOfAreaList.get(areaP - 1).add(numberP);

						int x = areaX + col * 50;
						Grid grid = new Grid(still, numberP, controller);
						grid.setLocation(x, y);
						grid.setRow(rowP);
						grid.setCol(colP);
						grid.setAreaNumber(areaP);
						grid.setNumber(numberP);
						add(grid);
						allGrid.add(grid);
					}
				}
			}
		}

		controller.setAllGrid(allGrid);
		controller.setGamePage(this);
	}

	/**
	 * ��������
	 * 
	 * @param filePath
	 * @param start
	 * @return int ������ֵ
	 */
	private int readNumberFromFile(String filePath, String start) {
		File file = new File(filePath);
		int result = 0;

		try {
			if (file.canRead()) {
				InputStreamReader reader = new InputStreamReader(
						new FileInputStream(file));
				BufferedReader br = new BufferedReader(reader);
				String temp = br.readLine();
				while (temp != null && !temp.isEmpty()) {
					if (temp.startsWith(start)) {
						result = Integer.parseInt(temp.substring(temp
								.indexOf("=") + 1));
						break;
					}
					temp = br.readLine();
				}

				br.close();
				reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * ������Ϸ
	 * 
	 * @param saveName
	 */
	private void saveGame(String saveName) {
		File file = new File("src\\asset\\saves\\3\\" + saveName);
		StringBuilder sb = new StringBuilder("");

		for (Grid g : allGrid) {
			int number = g.getNumber();
			if (number != 0) {
				int row = g.getRow();
				int col = g.getCol();
				sb.append(String.valueOf(row) + "," + String.valueOf(col) + "="
						+ String.valueOf(number) + "\r\n");
			}
		}
		String result = sb.toString();

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(result);
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ɾ�������¼
	 * 
	 * @param saveName
	 */
	private void deleteSave(String saveName) {
		File file = new File("src\\asset\\saves\\3\\" + saveName);

		try {
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JLabel getNumberPadOn() {
		return numberPadOn;
	}

	public void setNumberPadOn(JLabel numberPadOn) {
		this.numberPadOn = numberPadOn;
	}

	public ArrayList<ArrayList<Integer>> getListOfRowList() {
		return listOfRowList;
	}

	public void setListOfRowList(ArrayList<ArrayList<Integer>> listOfRowList) {
		this.listOfRowList = listOfRowList;
	}

	public ArrayList<ArrayList<Integer>> getListOfColList() {
		return listOfColList;
	}

	public void setListOfColList(ArrayList<ArrayList<Integer>> listOfColList) {
		this.listOfColList = listOfColList;
	}

	public ArrayList<ArrayList<Integer>> getListOfAreaList() {
		return listOfAreaList;
	}

	public void setListOfAreaList(ArrayList<ArrayList<Integer>> listOfAreaList) {
		this.listOfAreaList = listOfAreaList;
	}

}
