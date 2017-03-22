import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Core implements Observer{
	private FileData data;
	private Game game;
	private Position selection;
	
	private View view;
	private MainMenuViewController mainMenuViewController;
	private NewGameViewController newGameViewController;
	private ScoreViewController scoreViewController;
	private GameViewController gameViewController;
	
	
	public Core () {
		data = new FileData("Kamisado.config");
		this.game = null;
		this.selection = new Position(-1,-1);
	}
	
	@Override
	public void update (Observable observable, Object argument) {
		if(observable == this.game){
			updateGameView();
		} else {
			
		}
	}
	
	public void gameInput(Position position, Value inputType) {
		if (inputType == Value.ACTION) {
			if (!game.isGameOver()) {
				if (GameLogic.isValidTower(game.getBoard(), position.x, position.y)) {
					selection = position;
				} else {
					game.nextTurn(new Move(selection.x, selection.y, position.x, position.y), Value.HUMAN);
					selection = GameLogic.getValidTower(game.getBoard()).getPosition();
					
				}
			} else if (game.hasNextRound()){
				game.nextRound();
			}
		} else if (inputType == Value.HOVER){
			
		}
		drawGame();
	}
	
	public void undoMove() {
		if(game.getCurrentPlayer().getType() == Value.HUMAN && game.getLastPlayer().getType() != Value.HUMAN) {
			game.getBoard().undoLastMove();
			updateGameView();
		}
	}
	
	private void drawGame() {
		gameViewController.drawBoard(game.getBoard());
		if (game.isGameOver()) {
			if (game.hasNextRound())
				gameViewController.roundOver(game.getGameOverCause(), game.getWinner().getName());
			else {
				Player overallWinner = game.getOverallWinner();
				if (overallWinner == null)
					gameViewController.gameOverDraw(game.getGameOverCause(), game.getWinner().getName());
				else
					gameViewController.gameOver(game.getGameOverCause(), game.getWinner().getName(), overallWinner.getName());
			}
		} else {
			Tower tower = game.getBoard().getTower(selection.x, selection.y);
			if (tower != null)
				drawValidMoves(tower);
		}
	}
	
	private void updateGameView() {
		System.out.println("updateGameView");
		if (game instanceof SpeedGame && !game.isGameOver()) {
			gameViewController.showTimer(((SpeedGame)game).getTimeLimit());
		} else {
			gameViewController.hideTimer();
		}
		drawGame();
		checkForAI();
	}
	
	public void saveGame() {
		data.setGame(game);
		data.saveDataToFile();
	}
	
	public void resumeGame() {
		if (game != null) {
			view.displayScene(gameViewController);
			updateGameView();
		}
	}

	public void newGameMenu() {
		view.displayScene(newGameViewController);
    }
	
	public void loadGame() {
		data.loadFile();
		this.game = data.getGame();
		initGame();
	}
	
	public void scoreMenu() {
		view.displayScene(scoreViewController);
    }
	
	public void exit() {
		data.saveDataToFile();
		System.exit(0);
    }
	
    void mainMenu() {
    	if (game == null) {
			mainMenuViewController.disableResumeButton();
		} else {
			mainMenuViewController.enableResumeButton();
		}
    	view.displayScene(mainMenuViewController);
    }
	
    void newGame(Player player1, Player player2, int points, Value gameMode) {
    	if (gameMode == Value.SPEED_MODE){
    		this.game = new SpeedGame(player1, player2, points);
    	} else {
    		this.game = new NormalGame(player1, player2, points);
    	}
    	game.getScore().addObserver(gameViewController);
    	initGame();
    }
    
    public void initGame() {
    	game.addObserver(this);
    	gameViewController.setPlayerNames(game.player1.getName(), game.player2.getName());
    	view.displayScene(gameViewController);
    	this.update(game, Value.READY);
    }
    
    
    public void drawValidMoves(Tower tower) {
    	List<Move> moves = GameLogic.getValidMoves(game.getBoard(), tower);
    	List<Position> positions = new ArrayList<Position>();
    	for (Move move:moves) {
    		positions.add(new Position(move.finishX, move.finishY));
    	}
    	gameViewController.drawBoard(game.board);
    	gameViewController.drawHighlights(positions);
    }
	
	public void checkForAI() {
		if (game.getCurrentPlayer().getType() == Value.EASY_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					game.nextTurn(AI.MiniMaxAB(game.getBoard(), 3), Value.EASY_AI);
					gameViewController.hideLoader();
				}
			});
			thread.setDaemon(true);
			thread.start();
		} else if (game.getCurrentPlayer().getType() == Value.HARD_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					game.nextTurn(AI.MiniMaxAB(game.getBoard(), 9), Value.HARD_AI);
					gameViewController.hideLoader();
				}
			});
			thread.setDaemon(true);
			thread.start();
		}
	}
	
	
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void setMainMenuViewController(Controller mainMenuViewController) {
		this.mainMenuViewController = (MainMenuViewController)mainMenuViewController;
		mainMenuViewController.setCore(this);
	}

	public void setNewGameViewController(Controller newGameViewController) {
		this.newGameViewController = (NewGameViewController)newGameViewController;
		newGameViewController.setCore(this);
	}

	public void setScoreViewController(Controller scoreViewController) {
		this.scoreViewController = (ScoreViewController)scoreViewController;
		scoreViewController.setCore(this);
	}

	public void setGameViewController(Controller gameViewController) {
		this.gameViewController = (GameViewController)gameViewController;
		gameViewController.setCore(this);
	}
}