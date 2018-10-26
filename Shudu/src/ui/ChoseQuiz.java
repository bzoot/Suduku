package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;

import contorller.Controller;
import entrance.Frame;

/**
 * @author Mualim
 * @date 创建时间：2017-3-6 下午12:43:55
 * @version 1.0
 * @parameter
 * @return
 */
public class ChoseQuiz extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2373580336361790954L;

	public ChoseQuiz(final Controller controller) {
		setLayout(null);
		setVisible(true);
		setAutoscrolls(true);

		File file = new File("src\\asset\\3");
		File savedFile = new File("src\\asset\\saves\\3");
		int index = 0;
		if (savedFile.isDirectory()) {
			if (controller.isLoad()) {
				for (File f : savedFile.listFiles()) {
					final String path = f.getAbsolutePath();
					final String btnText = path.substring(path.lastIndexOf("\\") + 1,
							path.lastIndexOf("."));
					JButton btn = new JButton("第" + btnText + "关");
					btn.setFont(new Font("楷体", Font.BOLD, 16));

					// 布局并添加btn
					btn.setBounds(10 + index % 5 * 115, 10 + index / 5 * 95,
							100, 75);
					add(btn);
					index += 1;

					btn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if (arg0.getButton() == MouseEvent.BUTTON1) {
								controller.setFilePath("src\\asset\\3\\" + btnText + ".txt");
								controller.setSavedFilePath(path);
								Frame baseFrame = controller.getFrame();
								baseFrame.remove(controller.getJsp());
								controller.setJsp(null);
								controller.setChoseQuiz(null);
								baseFrame.add(new GamePage(controller));
								baseFrame.revalidate();
								baseFrame.repaint();
							}
						}
					});
				}
			} else {
				for (File f : file.listFiles()) {
					final String path = f.getAbsolutePath();
					String btnText = path.substring(path.lastIndexOf("\\") + 1,
							path.lastIndexOf("."));
					JButton btn = new JButton("第" + btnText + "关");
					btn.setFont(new Font("楷体", Font.BOLD, 16));

					// 布局并添加btn
					btn.setBounds(10 + index % 5 * 115, 10 + index / 5 * 95,
							100, 75);
					add(btn);
					index += 1;

					btn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if (arg0.getButton() == MouseEvent.BUTTON1) {
								controller.setFilePath(path);
								Frame baseFrame = controller.getFrame();
								baseFrame.remove(controller.getJsp());
								controller.setJsp(null);
								controller.setChoseQuiz(null);
								baseFrame.add(new GamePage(controller));
								baseFrame.revalidate();
								baseFrame.repaint();
							}
						}
					});
				}
			}
			JButton btn = new JButton("返   回");
			btn.setFont(new Font("楷体", Font.BOLD, 16));
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getButton() == MouseEvent.BUTTON1) {
						Frame baseFrame = controller.getFrame();
						baseFrame.remove(controller.getJsp());
						controller.setJsp(null);
						controller.setChoseQuiz(null);
						baseFrame.add(new FirstPage(controller));
						baseFrame.revalidate();
						baseFrame.repaint();
					}
				}
			});
			btn.setBounds(10 + index % 5 * 115, 10 + index / 5 * 95, 100, 75);
			add(btn);
			index += 1;
		}
		setLocation(0, 0);
		setPreferredSize(new Dimension(570, (index + 1) / 5 * 95));

		controller.setChoseQuiz(this);
	}

}
