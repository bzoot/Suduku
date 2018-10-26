package entrance;

import contorller.Controller;
import ui.FirstPage;

/** * @author  Mualim
 * @date ����ʱ�䣺2017-3-4 ����10:01:58 
 * @version 1.0 
 * @parameter  
 * @return  ���
 */
public class Initiate {
	private static Controller controller = new Controller();
	
	public static void main(String[] args) {
		Frame frame = new Frame(controller);
		frame.add(new FirstPage(controller));
	}

}
