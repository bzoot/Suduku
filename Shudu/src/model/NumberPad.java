package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import ui.GamePage;
import ui.PopUpFrame;

import contorller.Controller;

/**
 * @author Mualim
 * @date 创建时间：2017-3-5 上午12:08:02
 * @version 1.0
 * @parameter 控制器
 * @return 数字板
 */
public class NumberPad extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5896040874696924356L;

	public NumberPad(final Controller controller) {
		setSize(200, 200);
		setBorder(new LineBorder(Color.BLACK));
		setOpaque(true);
		setVisible(true);

		final GamePage gamePage = controller.getGamePage();
		final Grid currentGrid = controller.getCurrentGird();
		final ArrayList<ArrayList<Integer>> listOfRowList = gamePage
				.getListOfRowList();
		final ArrayList<ArrayList<Integer>> listOfColeList = gamePage
				.getListOfColList();
		final ArrayList<ArrayList<Integer>> listOfAreaList = gamePage
				.getListOfAreaList();
		final int currentGridRowInList = currentGrid.getRow() - 1;
		final int currentGridColInList = currentGrid.getCol() - 1;
		final int currentGridAreaInList = currentGrid.getAreaNumber() - 1;
		final int indexForAreaList = currentGridRowInList % 3 * 3
				+ currentGridColInList % 3;

		// 设置按钮
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				JButton btn = new JButton();
				final int result = row * 3 + col + 1;
				btn.setText(String.valueOf(row * 3 + col + 1));
				btn.setFont(new Font("楷体", Font.BOLD, 16));
				btn.setBounds(col * 55 + 15, row * 55 + 15, 50, 50);
				add(btn);
				boolean rowContain = listOfRowList.get(currentGridRowInList)
						.contains(result);
				boolean colContain = listOfColeList.get(currentGridColInList)
						.contains(result);
				boolean areaContain = listOfAreaList.get(currentGridAreaInList)
						.contains(result);
				if (rowContain || colContain || areaContain) {
					btn.setEnabled(false);
				} else {
					btn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if (arg0.getButton() == MouseEvent.BUTTON1) {
								currentGrid.setText(String.valueOf(result));
								currentGrid.revalidate();
								currentGrid.repaint();
								currentGrid.setNumber(result);
								controller.getNumberPadOn().revalidate();
								controller.getNumberPadOn().repaint();
								listOfRowList.get(currentGridRowInList).remove(
										currentGridColInList);
								listOfRowList.get(currentGridRowInList).add(
										currentGridColInList, result);
								listOfColeList.get(currentGridColInList)
										.remove(currentGridRowInList);
								listOfColeList.get(currentGridColInList).add(
										currentGridRowInList, result);
								listOfAreaList.get(currentGridAreaInList)
										.remove(indexForAreaList);
								listOfAreaList.get(currentGridAreaInList).add(
										indexForAreaList, result);
								ArrayList<Grid> allGrid = controller.getAllGrid();
								for (Grid g : allGrid) {
									int number = g.getNumber();
									if (number == 0) {								
										controller.setDone(false);
										return;
									}
								}
								controller.setFilePath("");
								controller.setDone(true);
								new PopUpFrame(controller);
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
					});
				}
			}
		}

		addMouseListener(new MouseAdapter() {// 拖拽移动
			int preX, preY, afterX, afterY, moveX, moveY, newX, newY;

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					preX = arg0.getX();
					preY = arg0.getY();
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					afterX = arg0.getX();
					afterY = arg0.getY();
					moveX = afterX - preX;
					moveY = afterY - preY;
					Point p = getLocation();
					newX = p.x + moveX;
					if (newX < 0) {
						newX = 0;
					} else if (newX > 400) {
						newX = 400;
					}
					newY = p.y + moveY;
					if (newY < 70) {
						newY = 70;
					} else if (newY > 390) {
						newY = 390;
					}
					setLocation(newX, newY);
				}
			}
		});

		controller.setNumberPad(this);
	}

}
