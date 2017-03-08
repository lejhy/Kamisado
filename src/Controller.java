import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller implements Observer{
	private Kamisado kamisado;
	private Game game;
	private Data data;
	private int squareSize;
	private int selectionX, selectionY;
	
	@FXML
    private Canvas gameView;
	
	@FXML
    private TextField player1Name;
	 
    @FXML
    private ToggleGroup player1Type;
    
    @FXML
    private TextField player2Name;

    @FXML
    private ToggleGroup player2Type;
	
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
	
	@FXML
    void exit(Event event) {

    }

    @FXML
    void loadGame(Event event) {

    }

    @FXML
    void newGame(Event event) {
    	Value p1Value = getRBSelectionValue (player1Type);
    	Value p2Value = getRBSelectionValue (player2Type);
    	
    	String p1Name = player1Name.getText();
    	String p2Name = player2Name.getText();
    	
    	Player player1 = new Player (p1Name, p1Value);
    	Player player2 = new Player (p2Name, p2Value);
    	
    	this.game = new Game(player1, player2);
    	game.addObserver(this);
    	initGame();
    	
    	kamisado.displayGame();
    	this.update(game, null);
    }

    @FXML
    void scene(ActionEvent event) {
    	
    }
    
    @FXML
    void updateGame() {
    	initGame();
    }
    
    @FXML
    void gameInput(Event event) {
    	if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
    		double x = ((MouseEvent)event).getSceneX();
    		double y = ((MouseEvent)event).getSceneY();
    		System.out.println("X: " + x + "Y: " + y);
    		int squareX = ((int)x)/squareSize;
    		int squareY = ((int)y)/squareSize;
    		Tower tower = game.getBoard().getTower(squareX, squareY);
    		if (tower != null) {
    			selectionX = squareX;
    			selectionY = squareY;
    		} else {
    			game.nextTurn(new Move(selectionX, selectionY, squareX, squareY), Value.HUMAN);
    		}
    	}
    }
    
    @FXML
    void newGameMenu(ActionEvent event) {
    	kamisado.displayNewGame();
    }
    
    public void initGame () {
    	System.out.println("Init game view");
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
    			if (tower.getPlayer())
    				gc.drawImage(ORANGE_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(ORANGE_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case BLUE:
				if (tower.getPlayer())
    				gc.drawImage(BLUE_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(BLUE_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case PURPLE:
				if (tower.getPlayer())
    				gc.drawImage(PURPLE_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(PURPLE_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case PINK:
				if (tower.getPlayer())
    				gc.drawImage(PINK_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(PINK_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case YELLOW:
				if (tower.getPlayer())
    				gc.drawImage(YELLOW_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(YELLOW_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case RED:
				if (tower.getPlayer())
    				gc.drawImage(RED_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(RED_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case GREEN:
				if (tower.getPlayer())
    				gc.drawImage(GREEN_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(GREEN_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			case BROWN:
				if (tower.getPlayer())
    				gc.drawImage(BROWN_WHITE, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
    			else
    				gc.drawImage(BROWN_BLACK, tower.getPositionX()*squareSize, tower.getPositionY()*squareSize, squareSize, squareSize);
				break;
			default:
				break;
    		}
    	}
    }

	
	@Override
	public void update (Observable observable, Object argument) {
		if (observable instanceof Game) {
			updateGame();
			System.out.println("update");
			if (game.isGameOver()) {
				gameOver();
			} else {
				checkForAI();
			}
		}
	}
	
	public void gameOver() {
		System.out.println("game over cont");
		GraphicsContext gc = gameView.getGraphicsContext2D();
		Image GAME_OVER = new Image("img/GAME_OVER.png");
		gc.drawImage(GAME_OVER, 0, 0, gameView.getWidth(), gameView.getHeight());
	}
	
	public void checkForAI() {
		if (game.getCurrentPlayer().getType() == Value.AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					game.nextTurn(AI.MiniMaxAB(game.getBoard(), 8), Value.AI);
				}
			});
			thread.setDaemon(true);
			thread.start();
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
		default:
			value = Value.HUMAN;
    		break;
    	}
    	
    	return value;
    }
	
	public void towerSelected(Tower tower) {
		// highlight moves
	}
	
	public void makeMove(Move move, Value type) {
		this.game.nextTurn(move, type);
	}
	
	public void newGame(Player player1, Player player2){
		this.game = new Game(player1, player2);
	}
	
	public void saveGame() {
		if (game != null){
			data.addGame(game);
		}
	}
   
	public void exit() {
		data.saveDataToFile();
		System.exit(0);
	}	
   
	public String[] loadData () {
		String[] load = {"load"};
		return load;
	}	
   
	public String[] scoreData () {
		String[] score = {"score"};
		return score;
	}	
	
	public Game getGame() {
		return game;
	}
}