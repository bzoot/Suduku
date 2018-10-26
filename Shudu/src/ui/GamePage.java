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
 * @date 创建时间：2017-3-4 下午10:19:11
 * @version 1.0
 * @parameter 控制器
 * @return 游戏页面
 */
public class GamePage extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5594260602765725106L;

	private JLabel numberPadOn = new JLabel();
	private JButton save = new JButton("保   存");
	private JButton restart = new JButton("重   玩");
	private JButton back = new JButton("返   回");
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
	 * GamePage构造函数
	 * 
	 * @param controller
	 */
	public GamePage(final Controller controller) {
		setLayout(null);
		setVisible(true);
		setSize(600, 670);

		// numberPadOn设置
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

		// 给listOfList填入空表
		for (int i = 0; i < 9; i++) {
			listOfRowList.add(new ArrayList<Integer>());
			listOfColList.add(new ArrayList<Integer>());
			listOfAreaList.add(new ArrayList<Integer>());
		}

		// 按钮大小位置
		save.setBounds(100, 20, 100, 50);
		save.setFont(new Font("楷体", Font.BOLD, 16));
		restart.setBounds(250, 20, 100, 50);
		restart.setFont(new Font("楷体", Font.BOLD, 16));
		back.setBounds(400, 20, 100, 50);
		back.setFont(new Font("楷体", Font.BOLD, 16));

		// 放置按钮
		add(save);
		allJButton.add(save);
		add(restart);
		allJButton.add(restart);
		add(back);
		allJButton.add(back);

		// 按钮功能
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					saveGame(controller.getFilePath().substring(
							controller.getFilePath().lastIndexOf("\\") + 1));
					// 提示保存成功
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

		// 坐标标记
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int d = i * 3 + j + 1;
				JLabel lbcol = new JLabel("第" + String.valueOf(d) + "列");
				lbcol.setBounds(90 + i * 20 + (d - 1) * 50, 90, 50, 50);
				lbcol.setFont(new Font("楷体", Font.PLAIN, 18));
				lbcol.setHorizontalAlignment(JLabel.CENTER);
				lbcol.setVerticalAlignment(JLabel.CENTER);
				add(lbcol);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int d = i * 3 + j + 1;
				JLabel lbcol = new JLabel("第" + String.valueOf(d) + "行");
				lbcol.setBounds(20, (d - 1) * 50 + i * 20 + 160, 50, 50);
				lbcol.setFont(new Font("楷体", Font.PLAIN, 18));
				lbcol.setHorizontalAlignment(JLabel.CENTER);
				lbcol.setVerticalAlignment(JLabel.CENTER);
				add(lbcol);
			}
		}

		// 画方格
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
										+ "," + String.valueOf(colP)); // 读入原始值
						boolean still = false;
						if (numberP != 0) {
							still = true;
						} else if (controller.isLoad()) {
							numberP = readNumberFromFile(
									controller.getSavedFilePath(),
									String.valueOf(rowP) + ","
											+ String.valueOf(colP)); // 如果是载入游戏
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
	 * 载入棋盘
	 * 
	 * @param filePath
	 * @param start
	 * @return int 棋盘数值
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
	 * 保存游戏
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
	 * 删除保存记录
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
