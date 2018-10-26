package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import ui.GamePage;

import contorller.Controller;

/**
 * @author Mualim
 * @date 创建时间：2017-3-4 下午11:43:42
 * @version 1.0
 * @parameter 是否静态，初始数字，控制器
 * @return 方格
 */
public class Grid extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6036886807679053961L;

	private int row;
	private int col;
	private int areaNumber;
	private int number;

	public Grid(boolean still, int numberRaw, final Controller controller) {
		setSize(50, 50);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.BLACK));
		if (still) {
			setForeground(Color.RED);
		}
		setFont(new Font("楷体", Font.BOLD, 22));
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setOpaque(true);
		setVisible(true);
		final Grid thiz = this;
		
		if (numberRaw != 0 && numberRaw < 10) {
			this.setText(String.valueOf(numberRaw));
		}

		addMouseListener(new MouseAdapter() {
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
		});
		if (!still) {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getButton() == MouseEvent.BUTTON1) {
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
						controller.setCurrentGird(thiz);
						thiz.setBorder(new LineBorder(Color.RED, 3));
						thiz.revalidate();
						thiz.repaint();
						Point p = thiz.getLocation();
						int x = p.x + 25;
						if (x >= 435) {
							x = x - 200;
						}
						int y = p.y + 25 - 90;
						if (y >= 435) {
							y = y - 200;
						}
						NumberPad numberPad = new NumberPad(controller);
						numberPad.setLocation(x, y);
						numberPadOn.add(numberPad);
						controller.setNumberPad(numberPad);
						numberPadOn.revalidate();
						numberPadOn.repaint();
					} else if (arg0.getButton() == MouseEvent.BUTTON2
							&& thiz.getNumber() != 0) {
						GamePage gamePage = controller.getGamePage();
						Grid currentGrid = thiz;
						ArrayList<ArrayList<Integer>> listOfRowList = gamePage.getListOfRowList();
						ArrayList<ArrayList<Integer>> listOfColeList = gamePage.getListOfColList();
						ArrayList<ArrayList<Integer>> listOfAreaList = gamePage.getListOfAreaList();
						int currentGridRowInList = currentGrid.getRow() - 1;
						int currentGridColInList = currentGrid.getCol() - 1;
						int currentGridAreaInList = currentGrid.getAreaNumber() - 1;
						int indexForAreaList = currentGridRowInList % 3 * 3 + currentGridColInList % 3;
						
						JLabel numberPadOn = controller.getGamePage()
								.getNumberPadOn();
						thiz.setText("");
						thiz.setNumber(0);
						listOfRowList.get(currentGridRowInList).remove(currentGridColInList);
						listOfRowList.get(currentGridRowInList).add(currentGridColInList, 0);
						listOfColeList.get(currentGridColInList).remove(currentGridRowInList);
						listOfColeList.get(currentGridColInList).add(currentGridRowInList, 0);
						listOfAreaList.get(currentGridAreaInList).remove(indexForAreaList);
						listOfAreaList.get(currentGridAreaInList).add(indexForAreaList, 0);
						thiz.revalidate();
						thiz.repaint();
						numberPadOn.revalidate();
						numberPadOn.repaint();
					}
				}
			});
			addMouseWheelListener(new MouseAdapter() {
				@Override
				public void mouseWheelMoved(MouseWheelEvent arg0) {
					GamePage gamePage = controller.getGamePage();
					Grid currentGrid = thiz;
					ArrayList<ArrayList<Integer>> listOfRowList = gamePage.getListOfRowList();
					ArrayList<ArrayList<Integer>> listOfColeList = gamePage.getListOfColList();
					ArrayList<ArrayList<Integer>> listOfAreaList = gamePage.getListOfAreaList();
					int currentGridRowInList = currentGrid.getRow() - 1;
					int currentGridColInList = currentGrid.getCol() - 1;
					int currentGridAreaInList = currentGrid.getAreaNumber() - 1;
					int indexForAreaList = currentGridRowInList % 3 * 3 + currentGridColInList % 3;
					
					if (thiz.getNumber() != 0) {
						if (arg0.getButton() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
							JLabel numberPadOn = controller.getGamePage()
									.getNumberPadOn();
							thiz.setText("");
							thiz.setNumber(0);
							listOfRowList.get(currentGridRowInList).remove(currentGridColInList);
							listOfRowList.get(currentGridRowInList).add(currentGridColInList, 0);
							listOfColeList.get(currentGridColInList).remove(currentGridRowInList);
							listOfColeList.get(currentGridColInList).add(currentGridRowInList, 0);
							listOfAreaList.get(currentGridAreaInList).remove(indexForAreaList);
							listOfAreaList.get(currentGridAreaInList).add(indexForAreaList, 0);
							thiz.revalidate();
							thiz.repaint();
							numberPadOn.revalidate();
							numberPadOn.repaint();
						}
					}
				}
			});
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getAreaNumber() {
		return areaNumber;
	}

	public void setAreaNumber(int areaNumber) {
		this.areaNumber = areaNumber;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
