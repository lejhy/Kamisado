
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GameViewController extends Controller{
	private Position position;
	private Game game;
	
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
    private Label player2Label;
    
    @FXML
    private Label player2Score;
    
    @FXML
    private Label player1Score;

    @FXML
    private Label player1Label;

    @FXML
    private ProgressIndicator loading;

    @FXML
    private ProgressBar timer;
    
    @FXML
    private Button undo;

    @FXML
    private Canvas gameView;
    
    private GraphicsContext gc;
    private double canvasWidth;
    private double canvasHeight;
    private double squareSize;
    
    @FXML
    void keyboardInput(KeyEvent event) {
    	switch(event.getCode()){
    	case UP:
    		if (position == null)
    			position = new Position(3,0);
    		if (position.y > 0)
    			position.y--;
    		update(null, null);
    		event.consume();
    		break;
    	case LEFT:
    		if (position == null)
    			position = new Position(0,3);
    		if (position.x > 0)
    			position.x--;
    		update(null, null);
    		event.consume();
    		break;
    	case DOWN:
    		if (position == null)
    			position = new Position(4,7);
    		if (position.y < 7)
    			position.y++;
    		update(null, null);
    		event.consume();
    		break;
    	case RIGHT:
    		if (position == null)
    			position = new Position(7,4);
    		if (position.x < 7)
    			position.x++;
    		update(null, null);
    		event.consume();
    		break;
    	case ENTER:
    		core.gameInput(new Position(position), Value.ACTION);
    		break;
    	case ESCAPE:
    		position = null;
    		core.gameInput(new Position(position), Value.HOVER);
    		break;
    	case U:
    		core.undoMove();
    		break;
    	case S:
    		core.saveGame();
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
    		
    		position = null;
    		core.gameInput(new Position(squareX, squareY), Value.ACTION);
    	}
    }
    
    @FXML
    void undoMove(ActionEvent event) {
    	core.undoMove();
    }
    
    @FXML
    void saveGame(ActionEvent event) {
    	core.saveGame();
    }

    @FXML
    void mainMenu(ActionEvent event) {
    	core.mainMenu();
    }

    @FXML
    void initialize() {
        assert player2Label != null : "fx:id=\"player2Name\" was not injected: check your FXML file 'Game.fxml'.";
        assert player1Label != null : "fx:id=\"player1Name\" was not injected: check your FXML file 'Game.fxml'.";
        assert loading != null : "fx:id=\"loading\" was not injected: check your FXML file 'Game.fxml'.";
        assert timer != null : "fx:id=\"timer\" was not injected: check your FXML file 'Game.fxml'.";
        assert gameView != null : "fx:id=\"gameView\" was not injected: check your FXML file 'Game.fxml'.";
        gc = gameView.getGraphicsContext2D();
        canvasWidth = gameView.getWidth();
    	canvasHeight = gameView.getHeight();
    	squareSize = gameView.getWidth()/8;
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
    
    public void roundOver(Value cause, String roundWinner) {
    	drawRoundOver(cause, roundWinner);
		gc.setFont(new Font("Avenir", squareSize/4));
		gc.fillText(
				"Click to continue to next round", 
				canvasWidth/2, 
				(canvasHeight + 3*squareSize)/2
		);
	}
    
    public void gameOver(Value cause, String roundWinner, String gameWinner) {
    	drawRoundOver(cause, roundWinner);
		gc.setFont(new Font("Courier New", squareSize/2));
		gc.fillText(
				gameWinner + " Won the Game!", 
				canvasWidth/2, 
				(canvasHeight + 3*squareSize)/2
		);
	}
    
    public void gameOverDraw(Value cause, String roundWinner) {
    	drawRoundOver(cause, roundWinner);
		gc.setFont(new Font("Courier New", squareSize/2));
		gc.fillText(
				"It's a DRAW!", 
				canvasWidth/2, 
				(canvasHeight + 3*squareSize)/2
		);
	}
    
    private void drawRoundOver(Value cause, String roundWinner) {
    	if (timer.isVisible())
    		timer.setVisible(false);
		
    	String gameOverCause = "GAME OVER";
    	if (cause == Value.TIME_UP)
    		gameOverCause = "TIME IS UP!";
    	if (cause == Value.DOUBLE_DEADLOCK)
    		gameOverCause = "DEADLOCK";
    	
		gc.drawImage(GAME_OVER, 0, 0, canvasWidth, canvasHeight);
		
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(new Font("Courier New",squareSize));
		gc.setFill(Color.WHITE);
		gc.fillText(
				gameOverCause, 
				canvasWidth/2, 
				(canvasHeight - squareSize)/2
		);
		gc.setFont(new Font("Avenir", squareSize/2));
		gc.fillText(
				roundWinner + " won this round!", 
				canvasWidth/2, 
				(canvasHeight + squareSize)/2
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
    
    public void showUndoButton() {
    	undo.setVisible(true);
    }
    
    public void hideUndoButton() {
    	undo.setVisible(false);
    }
    
    public void setPlayer1Name(String player1) {
    	player1Label.setText(player1);
    }
    
    public void setPlayer2Name(String player2) {
    	player2Label.setText(player2);
    }
    
    public void setPlayer1Score(int newScore) {
    	player1Score.setText(String.valueOf(newScore));
    }
    
    public void setPlayer2Score(int newScore) {
    	player2Score.setText(String.valueOf(newScore));
    }
    
    public void setGame(Game game) {
    	this.game = game;
    }
    
    public void drawHighlight(Position position) {
    	gc.drawImage(HIGHLIGHT, position.x*squareSize, position.y*squareSize, squareSize, squareSize);
    }
    
    public void drawHighlights(List<Position> positions) {
    	for (Position position: positions) {
    		gc.drawImage(HIGHLIGHT, position.x*squareSize, position.y*squareSize, squareSize, squareSize);
    	}
    }
    
    public void drawBoard() {
    	drawTiles(game.getBoard().getTiles());
    	drawPieces(game.getBoard().getPieces());
    	
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
	
	public void drawPieces(List<Piece> pieces) {
		GraphicsContext gc = gameView.getGraphicsContext2D();
    	double squareSize = gameView.getWidth()/8;
    	
    	for (Piece piece: pieces) {
    		switch(piece.getColor()) {
    		case ORANGE:
    			if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(ORANGE_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(ORANGE_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			case BLUE:
				if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(BLUE_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(BLUE_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			case PURPLE:
				if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(PURPLE_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(PURPLE_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			case PINK:
				if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(PINK_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(PINK_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			case YELLOW:
				if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(YELLOW_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(YELLOW_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			case RED:
				if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(RED_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(RED_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			case GREEN:
				if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(GREEN_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(GREEN_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			case BROWN:
				if (piece.getPlayerPosition() == Value.BOTTOM)
    				gc.drawImage(BROWN_WHITE, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(BROWN_BLACK, piece.getPosition().x*squareSize, piece.getPosition().y*squareSize, squareSize, squareSize);
				break;
			default:
				break;
    		}
    	}
    }

	@Override
	public void update(Observable o, Object arg) {
		
		drawBoard();
		List<Position> positions = core.getPositionsToHighlight();
		drawHighlights(positions);
		if (position != null) {
			drawHighlight(position);
		}
		
		if (game.isGameOver()) {
			if (game.hasNextRound())
				roundOver(game.getGameOverCause(), game.getRoundWinner().getName().get());
			else {
				Player overallWinner = game.getOverallWinner();
				if (overallWinner == null)
					gameOverDraw(game.getGameOverCause(), game.getRoundWinner().getName().get());
				else
					gameOver(game.getGameOverCause(), game.getRoundWinner().getName().get(), overallWinner.getName().get());
			}
		} else if (game instanceof SpeedGame) {
			if (arg == Value.TIMER_RESET)
				showTimer(((SpeedGame)game).getTimeLimit());
			else if (arg == Value.TIMER_HIDE)
				hideTimer();
		}
		
		if (!game.isGameOver() && game.getCurrentPlayer().getType() == Value.HUMAN && game.getLastPlayer().getType() != Value.HUMAN) {
			undo.setVisible(true);
		} else {
			undo.setVisible(false);
		}
	}
}
