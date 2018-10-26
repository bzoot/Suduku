package ui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Grid;

import contorller.Controller;
import entrance.Frame;

/** 
 * @author  Mualim
 * @date ����ʱ�䣺2017-3-10 ����10:44:00 
 * @version 1.0 
 * @parameter  
 * @return  
 */
public class PopUpFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4651116199728751141L;

	/**
	 * @param controller
	 */
	public PopUpFrame(final Controller controller) {
		//���εײ�JFrame
		GamePage gp = controller.getGamePage();
		for (Grid g : gp.getAllGrid()) {
			for (MouseListener ml : g.getMouseListeners()) {
				g.removeMouseListener(ml);
			}
		}
		for (JButton btn : gp.getAllJButton()) {
			for (MouseListener ml : btn.getMouseListeners()) {
				btn.removeMouseListener(ml);
			}
		}
		
		setAlwaysOnTop(true);
		setLayout(null);
		setVisible(true);
		setBounds(150, 260, 300, 180);
		setTitle("Frame");
		setResizable(false);
		setLocationRelativeTo(controller.getFrame());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		final PopUpFrame thiz = this;
		
		JLabel lb = new JLabel();
		if (controller.isDone()) {
			lb.setText("��ϲ��������ɴ˹أ�");
		} else {
			lb.setText("��Ǹ������δ��ɴ˹ء�");
		}
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setVerticalAlignment(SwingConstants.CENTER);
		lb.setFont(new Font("����", Font.BOLD, 22));
		lb.setBounds(0, 0, 300, 60);
		JButton btn = new JButton("ȷ    ��");
		btn.setFont(new Font("����", Font.BOLD, 22));
		btn.setBounds(75, 70, 150, 60);
		add(lb);
		add(btn);
		
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton() == MouseEvent.BUTTON1) {
					if (controller.isDone()) {
						Frame baseFrame = controller.getFrame();
						baseFrame.remove(controller.getGamePage());
						controller.setGamePage(null);
						baseFrame.add(new FirstPage(controller));
						baseFrame.revalidate();
						baseFrame.repaint();
					}
					
					thiz.dispose();
				}
			}
		});
	}
	
}
