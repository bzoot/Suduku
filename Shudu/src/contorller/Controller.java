package contorller;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import model.Grid;
import model.NumberPad;
import ui.FirstPage;
import ui.GamePage;
import entrance.Frame;

/**
 * @author Mualim
 * @date 创建时间：2017-3-4 下午10:09:39
 * @version 1.0
 * @parameter
 * @return 控制器
 */
public class Controller {
	private Frame frame;
	private FirstPage firstPage;
	private GamePage gamePage;
	private NumberPad numberPad;
	private Grid currentGird;
	private JLabel numberPadOn;
	private JLabel choseQuiz;
	private String filePath = "";
	private String savedFilePath = "";
	private JScrollPane jsp;
	private boolean done = false;
	private boolean load = false;
	private ArrayList<Grid> allGrid;

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public FirstPage getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(FirstPage firstPage) {
		this.firstPage = firstPage;
	}

	public GamePage getGamePage() {
		return gamePage;
	}

	public void setGamePage(GamePage gamePage) {
		this.gamePage = gamePage;
	}

	public NumberPad getNumberPad() {
		return numberPad;
	}

	public void setNumberPad(NumberPad numberPad) {
		this.numberPad = numberPad;
	}

	public Grid getCurrentGird() {
		return currentGird;
	}

	public void setCurrentGird(Grid currentGird) {
		this.currentGird = currentGird;
	}

	public JLabel getNumberPadOn() {
		return numberPadOn;
	}

	public void setNumberPadOn(JLabel numberPadOn) {
		this.numberPadOn = numberPadOn;
	}

	public JLabel getChoseQuiz() {
		return choseQuiz;
	}

	public void setChoseQuiz(JLabel choseQuiz) {
		this.choseQuiz = choseQuiz;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSavedFilePath() {
		return savedFilePath;
	}

	public void setSavedFilePath(String savedFilePath) {
		this.savedFilePath = savedFilePath;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isLoad() {
		return load;
	}

	public void setLoad(boolean load) {
		this.load = load;
	}

	public ArrayList<Grid> getAllGrid() {
		return allGrid;
	}

	public void setAllGrid(ArrayList<Grid> allGrid) {
		this.allGrid = allGrid;
	}

}
