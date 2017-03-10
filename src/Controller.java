import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Controller implements Observer{
	private Kamisado kamisado;
	private Game game;
	private Data data;
	private int squareSize;
	private int selectionX, selectionY;
	
	public Controller (Kamisado kamisado) {
		this.kamisado = kamisado;
		this.game = null;
		try {
			// try opening an existing file
			data = new Data("Kamisado.save");
		} catch (Exception e) {
			// create a new file
			data = new Data();
		}
	}
	
	@Override
	public void update (Observable observable, Object argument) {
		if (argument == Value.GAME_OVER) {
			updateGame();
			gameOver();
		}
		if (argument == Value.NEXT_TURN) {
			updateGame();
			System.out.println("update");
			if (!game.isGameOver()) {
				checkForAI();
				if (game instanceof SpeedGame) {
		    		timer.setVisible(true);
		    		timer.setProgress(1.0);
		    		Timeline timeline = new Timeline();
		    		KeyValue keyValue = new KeyValue(timer.progressProperty(), 0.0);
		    		KeyFrame keyFrame = new KeyFrame(new Duration(((SpeedGame) game).timeLimit), keyValue);
		    		timeline.getKeyFrames().add(keyFrame);
		    		timeline.play();
		    	} else {
		    		timer.setVisible(false);
		    	}
			}
		}
	}
	
	//Main Menu
	@FXML
    private Button resume;
	
	@FXML
	void resumeGame(ActionEvent event) {
		if (game != null) {
			kamisado.displayGame();
		}
	}

	@FXML
    void newGameMenu(ActionEvent event) {
    	kamisado.displayNewGame();
    }
	
	@FXML
    void loadGameMenu(ActionEvent event) {
    	kamisado.displayLoadGame();
    }
	
	@FXML
    void score(ActionEvent event) {
    	kamisado.displayScore();
    }
	
	@FXML
    void exit(Event event) {
		System.exit(0);
    }
	
	@FXML
    void mainMenu(ActionEvent event) {
    	kamisado.displayMainMenu();
    	if (game != null) {
    		resume.setDisable(false);
    	} else {
    		resume.setDisable(true);
    	}
    }
	
	// New Game
	@FXML
    private TextField player1NameInput;
	 
    @FXML
    private ToggleGroup player1Type;
	
    @FXML
    private TextField player2NameInput;

    @FXML
    private ToggleGroup player2Type;
    
    @FXML
    private ToggleGroup gameMode;
    
    @FXML
    void newGame(Event event) {
    	Value p1Value = getRBSelectionValue (player1Type);
    	Value p2Value = getRBSelectionValue (player2Type);
    	Value gameModeValue = getRBSelectionValue (gameMode);
    	
    	String p1Name = player1NameInput.getText();
    	if (p1Name.equals("")){
    		p1Name = "Player 1";
    	}
    	String p2Name = player2NameInput.getText();
    	if (p2Name.equals("")){
    		p2Name = "Player 2";
    	}
    	
    	Player player1 = new Player (p1Name, p1Value);
    	Player player2 = new Player (p2Name, p2Value);
    	
    	if (gameModeValue == Value.SPEED_MODE){
    		this.game = new SpeedGame(player1, player2);
    	} else {
    		this.game = new NormalGame(player1, player2);
    	}
    	game.addObserver(this);
    	initGame();
    	
    	kamisado.displayGame();
    	this.update(game, Value.NEXT_TURN);
    }
    
	// Load game
    @FXML
    private TableView<?> loadGameTable;
    
    @FXML
    void loadGame(Event event) {

    }
    
    // Score
    @FXML
    private TableView<?> scoreTable;
    
    //Game
	@FXML
    private Canvas gameView;
	
	@FXML
    private Label player1Name;
    
    @FXML
    private Label player2Name;
    
    @FXML
    private ProgressBar timer;
    
    @FXML
    private ProgressIndicator loading;

    @FXML
    void updateGame() {
    	initGame();
    }
    
    @FXML
    void gameInput(Event event) {
    	if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
    		double x = ((MouseEvent)event).getX();
    		double y = ((MouseEvent)event).getY();
    		System.out.println("X: " + x + "Y: " + y);
    		int squareX = ((int)x)/squareSize;
    		int squareY = ((int)y)/squareSize;
    		if (GameLogic.isValidTower(game.getBoard(), squareX, squareY)) {
    			selectionX = squareX;
    			selectionY = squareY;
    			Tower tower = game.getBoard().getTower(selectionX, selectionY);
    			showValidMoves(tower);
    		} else {
    			game.nextTurn(new Move(selectionX, selectionY, squareX, squareY), Value.HUMAN);
    		}
    	}
    }
    
    public void showValidMoves(Tower tower) {
    	updateGame();
    	List<Move> moves = GameLogic.getValidMoves(game.getBoard(), tower);
    	GraphicsContext gc = gameView.getGraphicsContext2D();
    	Image HIGHLIGHT = new Image("img/HIGHLIGHT.png");
    	for (Move move: moves) {
    		gc.drawImage(HIGHLIGHT, move.finishX*squareSize, move.finishY*squareSize, squareSize, squareSize);
    	}
    }
	
	public void gameOver() {
		System.out.println("game over");
		GraphicsContext gc = gameView.getGraphicsContext2D();
		timer.setVisible(false);
		Image GAME_OVER = new Image("img/GAME_OVER.png");
		gc.drawImage(GAME_OVER, 0, 0, gameView.getWidth(), gameView.getHeight());
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(new Font(squareSize));
		gc.fillText(
				"GAME OVER", 
				gameView.getWidth()/2, 
				(gameView.getHeight() - squareSize)/2
		);
		gc.setFont(new Font(squareSize/2));
		gc.fillText(
				game.getWinner().getName() + " is the Winner!", 
				gameView.getWidth()/2, 
				(gameView.getHeight() + squareSize)/2
		);
	}
	
	public void checkForAI() {
		if (game.getCurrentPlayer().getType() == Value.AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					if(!timer.isVisible()){
						loading.setVisible(true);
					}
					game.nextTurn(AI.MiniMaxAB(game.getBoard(), 11), Value.AI);
					loading.setVisible(false);
				}
			});
			thread.setDaemon(true);
			thread.start();
		}
	}
	
    public void initGame () {
    	System.out.println("Init game view");
    	
    	player1Name.setText(game.getPlayer1().getName());
    	player2Name.setText(game.getPlayer2().getName());
    	
    	GraphicsContext gc = gameView.getGraphicsContext2D();
    	
    	Image ORANGE = new Image("img/ORANGE.png");
    	Image BLUE = new Image("img/BLUE.png");
    	Image PURPLE = new Image("img/PURPLE.png");
    	Image PINK = new Image("img/PINK.png");
    	Image YELLOW = new Image("img/YELLOW.png");
    	Image RED = new Image("img/RED.png");
    	Image GREEN = new Image("img/GREEN.png");
    	Image BROWN = new Image("img/BROWN.png");

    	Image ORANGE_WHITE = new Image("img/ORANGE_WHITE.png");
    	Image BLUE_WHITE = new Image("img/BLUE_WHITE.png");
    	Image PURPLE_WHITE = new Image("img/PURPLE_WHITE.png");
    	Image PINK_WHITE = new Image("img/PINK_WHITE.png");
    	Image YELLOW_WHITE = new Image("img/YELLOW_WHITE.png");
    	Image RED_WHITE = new Image("img/RED_WHITE.png");
    	Image GREEN_WHITE = new Image("img/GREEN_WHITE.png");
    	Image BROWN_WHITE = new Image("img/BROWN_WHITE.png");
    	
    	Image ORANGE_BLACK = new Image("img/ORANGE_BLACK.png");
    	Image BLUE_BLACK = new Image("img/BLUE_BLACK.png");
    	Image PURPLE_BLACK = new Image("img/PURPLE_BLACK.png");
    	Image PINK_BLACK = new Image("img/PINK_BLACK.png");
    	Image YELLOW_BLACK = new Image("img/YELLOW_BLACK.png");
    	Image RED_BLACK = new Image("img/RED_BLACK.png");
    	Image GREEN_BLACK = new Image("img/GREEN_BLACK.png");
    	Image BROWN_BLACK = new Image("img/BROWN_BLACK.png");
    	
    	squareSize = (int)(gameView.getWidth()/8);
    	int row = 0;
    	int column = 0;
    	
    	for (Value[] tiles: game.getBoard().getTiles()) {
    		column = 0;
    		for (Value tile: tiles) {
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
    	
    	for (Tower tower: game.getBoard().getTowers()) {
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
	
	public Value getRBSelectionValue (ToggleGroup toggleGroup) {
    	RadioButton radioButton = (RadioButton)toggleGroup.getSelectedToggle();
    	String string = radioButton.getText();
    	Value value;
    	switch (string) {
    	case "Human":
    		value = Value.HUMAN;
    		break;
    	case "AI":
    		value = Value.AI;
    		break;
    	case "Normal":
    		value = Value.NORMAL;
    		break;
    	case "Speed Mode":
    		value = Value.SPEED_MODE;
    		break;
		default:
			value = Value.DEFAULT;
    		break;
    	}
    	
    	return value;
    }
}