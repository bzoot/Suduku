package entrance;

import contorller.Controller;
import ui.FirstPage;

/** * @author  Mualim
 * @date 创建时间：2017-3-4 下午10:01:58 
 * @version 1.0 
 * @parameter  
 * @return  入口
 */
public class Initiate {
	private static Controller controller = new Controller();
	
	public static void main(String[] args) {
		Frame frame = new Frame(controller);
		frame.add(new FirstPage(controller));
	}

}
