/**
 * 
 */
package entrance;

import javax.swing.JFrame;

import contorller.Controller;

/** 
 * @author  Mualim
 * @date 创建时间：2017-3-4 下午10:05:40 
 * @version 1.0 
 * @parameter  控制器
 * @return  窗口
 */

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7615705683412686401L;
	
	public Frame(Controller controller) {
		setLayout(null);
		setVisible(true);
		setSize(600, 700);
		setTitle("Frame");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		controller.setFrame(this);
	}

}
