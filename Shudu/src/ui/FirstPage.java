package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import contorller.Controller;
import entrance.Frame;

/** 
 * @author  Mualim
 * @date 创建时间：2017-3-4 下午9:45:34 
 * @version 1.0 
 * @parameter  控制器
 * @return  第一页面
 */
public class FirstPage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5561476767953225991L;
	private JButton start = new JButton("开始游戏");
	private JButton load = new JButton("载入游戏");
	private JButton exit = new JButton("退    出");
	
	/**
	 * FirstPage构造函数
	 * @param controller
	 */
	public FirstPage(final Controller controller) {
		setLayout(null);
		setVisible(true);
		setSize(600, 670);
		
		//按钮大小位置
		start.setBounds(213, 148, 175, 75);
		start.setFont(new Font("楷体", Font.BOLD, 22));
		load.setBounds(213, 298, 175, 75);
		load.setFont(new Font("楷体", Font.BOLD, 22));
		exit.setBounds(213, 448, 175, 75);
		exit.setFont(new Font("楷体", Font.BOLD, 22));
		
		//放置按钮
		add(start);
		add(load);
		add(exit);
		
		//按钮功能
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton() == MouseEvent.BUTTON1) {
					controller.setLoad(false);
					Frame baseFrame = controller.getFrame();
					baseFrame.remove(controller.getFirstPage());
					controller.setFirstPage(null);
					ChoseQuiz cq = new ChoseQuiz(controller);
					JScrollPane jsp = new JScrollPane(cq);
					jsp.getVerticalScrollBar().setPreferredSize(new Dimension(18, 670));
					jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					jsp.setSize(600, 670);
					baseFrame.add(jsp);
					controller.setJsp(jsp);
					baseFrame.revalidate();
					baseFrame.repaint();
				}
			}
		});
		load.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton() == MouseEvent.BUTTON1) {
					controller.setLoad(true);
					Frame baseFrame = controller.getFrame();
					baseFrame.remove(controller.getFirstPage());
					controller.setFirstPage(null);
					ChoseQuiz cq = new ChoseQuiz(controller);
					JScrollPane jsp = new JScrollPane(cq);
					jsp.getVerticalScrollBar().setPreferredSize(new Dimension(18, 670));
					jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					jsp.setSize(600, 670);
					baseFrame.add(jsp);
					controller.setJsp(jsp);
					baseFrame.revalidate();
					baseFrame.repaint();
				}
			}
		});
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton() == MouseEvent.BUTTON1) {
					System.exit(0);
				}
			}
		});
		
		controller.setFirstPage(this);
	}

}
