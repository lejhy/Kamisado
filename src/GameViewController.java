
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GameViewController extends Controller{
	private Position position;
	
	private Image GAME_OVER;
	private Image GRUDGE;
	private Image HIGHLIGHT;
	
	private Image ORANGE;
	private Image BLUE;
	private Image PURPLE;
	private Image PINK;
	private Image YELLOW;
	private Image RED;
	private Image GREEN;
	private Image BROWN;
	private Image ORANGE_WHITE;
	private Image BLUE_WHITE;
	private Image PURPLE_WHITE;
	private Image PINK_WHITE;
	private Image YELLOW_WHITE;
	private Image RED_WHITE;
	private Image GREEN_WHITE;
	private Image BROWN_WHITE;
	
	private Image ORANGE_BLACK;
	private Image BLUE_BLACK;
	private Image PURPLE_BLACK;
	private Image PINK_BLACK;
	private Image YELLOW_BLACK;
	private Image RED_BLACK;
	private Image GREEN_BLACK;
	private Image BROWN_BLACK;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label player2Name;

    @FXML
    private Label player1Name;

    @FXML
    private ProgressIndicator loading;

    @FXML
    private ProgressBar timer;

    @FXML
    private Canvas gameView;
    
    @FXML
    void keyboardInput(KeyEvent event) {
    	switch(event.getCode()){
    	case UP:
    		if (position == null)
    			position = new Position(3,0);
    		if (position.y > 0)
    			position.y--;
    		core.gameInput(new Position(position), Value.HOVER);
    		break;
    	case LEFT:
    		if (position == null)
    			position = new Position(0,3);
    		if (position.x > 0)
    			position.x--;
    		core.gameInput(new Position(position), Value.HOVER);
    		break;
    	case DOWN:
    		if (position == null)
    			position = new Position(4,7);
    		if (position.y < 7)
    			position.y++;
    		core.gameInput(new Position(position), Value.HOVER);
    		break;
    	case RIGHT:
    		if (position == null)
    			position = new Position(7,4);
    		if (position.x < 7)
    			position.x++;
    		core.gameInput(new Position(position), Value.HOVER);
    		break;
    	case ENTER:
    		core.gameInput(new Position(position), Value.ACTION);
    		break;
    	case ESCAPE:
    		position = null;
    		core.gameInput(new Position(position), Value.HOVER);
    		break;
    	case B:
    		core.mainMenu();
    		break;
		default:
			break;
    	}
    }

    @FXML
    void gameInput(MouseEvent event) {
    	if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
    		double x = ((MouseEvent)event).getX();
    		double y = ((MouseEvent)event).getY();
    		System.out.println("X: " + x + "Y: " + y);
    		
    		double squareSize = gameView.getWidth()/8;
    		int squareX = (int)(x/squareSize);
    		int squareY = (int)(y/squareSize);
    		
    		core.gameInput(new Position(squareX, squareY), Value.ACTION);
    	}
    }

    @FXML
    void mainMenu(ActionEvent event) {
    	core.mainMenu();
    }

    @FXML
    void initialize() {
        assert player2Name != null : "fx:id=\"player2Name\" was not injected: check your FXML file 'Game.fxml'.";
        assert player1Name != null : "fx:id=\"player1Name\" was not injected: check your FXML file 'Game.fxml'.";
        assert loading != null : "fx:id=\"loading\" was not injected: check your FXML file 'Game.fxml'.";
        assert timer != null : "fx:id=\"timer\" was not injected: check your FXML file 'Game.fxml'.";
        assert gameView != null : "fx:id=\"gameView\" was not injected: check your FXML file 'Game.fxml'.";
        loadImages();
    }
    
    private void loadImages(){
    	GAME_OVER = new Image("img/GAME_OVER.png");
    	GRUDGE = new Image("img/GRUDGE.png");	
    	HIGHLIGHT = new Image("img/HIGHLIGHT.png");
    	
    	ORANGE = new Image("img/ORANGE.png");
    	BLUE = new Image("img/BLUE.png");
    	PURPLE = new Image("img/PURPLE.png");
    	PINK = new Image("img/PINK.png");
    	YELLOW = new Image("img/YELLOW.png");
    	RED = new Image("img/RED.png");
    	GREEN = new Image("img/GREEN.png");
    	BROWN = new Image("img/BROWN.png");
    	ORANGE_WHITE = new Image("img/ORANGE_WHITE.png");
    	BLUE_WHITE = new Image("img/BLUE_WHITE.png");
    	PURPLE_WHITE = new Image("img/PURPLE_WHITE.png");
    	PINK_WHITE = new Image("img/PINK_WHITE.png");
    	YELLOW_WHITE = new Image("img/YELLOW_WHITE.png");
    	RED_WHITE = new Image("img/RED_WHITE.png");
    	GREEN_WHITE = new Image("img/GREEN_WHITE.png");
    	BROWN_WHITE = new Image("img/BROWN_WHITE.png");
    	
    	ORANGE_BLACK = new Image("img/ORANGE_BLACK.png");
    	BLUE_BLACK = new Image("img/BLUE_BLACK.png");
    	PURPLE_BLACK = new Image("img/PURPLE_BLACK.png");
    	PINK_BLACK = new Image("img/PINK_BLACK.png");
    	YELLOW_BLACK = new Image("img/YELLOW_BLACK.png");
    	RED_BLACK = new Image("img/RED_BLACK.png");
    	GREEN_BLACK = new Image("img/GREEN_BLACK.png");
    	BROWN_BLACK = new Image("img/BROWN_BLACK.png");
    }
    
    public void gameOver(String winner) {
    	if (timer.isVisible())
    		timer.setVisible(false);
		
    	double width = gameView.getWidth();
    	double height = gameView.getHeight();
    	double squareSize = gameView.getWidth()/8;
    	
    	GraphicsContext gc = gameView.getGraphicsContext2D();
		gc.drawImage(GAME_OVER, 0, 0, width, height);
		
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(new Font("Courier New",squareSize));
		gc.setFill(Color.WHITE);
		gc.fillText(
				"GAME OVER", 
				width/2, 
				(height - squareSize)/2
		);
		
		gc.setFont(new Font("Avenir", squareSize/2));
		gc.fillText(
				winner + " is the Winner!", 
				width/2, 
				(height + squareSize)/2
		);
	}
    
    public void showLoader() {
    	// Show only if there is no timer
    	if (!timer.isVisible())
    		loading.setVisible(true);
    }
    
    public void hideLoader() {
    	loading.setVisible(false);
    }
    
    public void showTimer(int timeLimit) {
    	timer.setVisible(true);
		timer.setProgress(1.0);
		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(timer.progressProperty(), 0.0);
		KeyFrame keyFrame = new KeyFrame(new Duration(timeLimit), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
    }
    
    public void hideTimer() {
    	timer.setVisible(false);
    }
    
    public void setPlayerNames(String player1, String player2) {
    	player1Name.setText(player1);
    	player2Name.setText(player2);
    }
    
    public void drawHighlights(List<Position> positions) {
    	GraphicsContext gc = gameView.getGraphicsContext2D();
    	double squareSize = gameView.getWidth()/8;
    	for (Position position: positions) {
    		gc.drawImage(HIGHLIGHT, position.x*squareSize, position.y*squareSize, squareSize, squareSize);
    	}
    }
    
    public void drawBoard(Board board) {
    	drawTiles(board.getTiles());
    	drawTowers(board.getTowers());
    	
    	GraphicsContext gc = gameView.getGraphicsContext2D();
    	double squareSize = gameView.getWidth()/8;
    	gc.drawImage(GRUDGE, 0, 0, squareSize*8, squareSize*8);
    	
    	if(position != null) {
    		List<Position> positions = new ArrayList<Position>();
    		positions.add(position);
    		drawHighlights(positions);
    	}
    }	
    	
	public void drawTiles(Value[][] tiles){
		GraphicsContext gc = gameView.getGraphicsContext2D();
    	double squareSize = gameView.getWidth()/8;
    	int row = 0;
    	int column = 0;
    	
    	for (Value[] tilesRow: tiles) {
    		column = 0;
    		for (Value tile: tilesRow) {
    			switch(tile){
    			case ORANGE:
    				gc.drawImage(ORANGE, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
    			case BLUE:
    				gc.drawImage(BLUE, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
    			case PURPLE:
    				gc.drawImage(PURPLE, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
    			case PINK:
    				gc.drawImage(PINK, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
    			case YELLOW:
    				gc.drawImage(YELLOW, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
    			case RED:
    				gc.drawImage(RED, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
				case GREEN:
					gc.drawImage(GREEN, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
				case BROWN:
					gc.drawImage(BROWN, row*squareSize, column*squareSize, squareSize, squareSize);
    				break;
				default:
    				break;
    			}
    			column++;
    		}
    		row++;
    	}
	}
	
	public void drawTowers(List<Tower> towers) {
		GraphicsContext gc = gameView.getGraphicsContext2D();
    	double squareSize = gameView.getWidth()/8;
    	
    	for (Tower tower: towers) {
    		switch(tower.getColor()) {
    		case ORANGE:
    			if (tower.getPlayerValue())
    				gc.drawImage(ORANGE_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(ORANGE_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case BLUE:
				if (tower.getPlayerValue())
    				gc.drawImage(BLUE_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(BLUE_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case PURPLE:
				if (tower.getPlayerValue())
    				gc.drawImage(PURPLE_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(PURPLE_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case PINK:
				if (tower.getPlayerValue())
    				gc.drawImage(PINK_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(PINK_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case YELLOW:
				if (tower.getPlayerValue())
    				gc.drawImage(YELLOW_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(YELLOW_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case RED:
				if (tower.getPlayerValue())
    				gc.drawImage(RED_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(RED_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case GREEN:
				if (tower.getPlayerValue())
    				gc.drawImage(GREEN_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(GREEN_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case BROWN:
				if (tower.getPlayerValue())
    				gc.drawImage(BROWN_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(BROWN_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			default:
				break;
    		}
    	}
    }
}