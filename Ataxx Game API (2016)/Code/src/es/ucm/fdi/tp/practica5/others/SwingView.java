package es.ucm.fdi.tp.practica5.others;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.extra.jcolor.ColorChooser;
import es.ucm.fdi.tp.practica6.Main;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;

@SuppressWarnings("serial")
public abstract class SwingView extends JFrame implements GameObserver { //Hace la ventana y el pane
	
	protected Controller ctrl; //Controller
	protected Observable<GameObserver> game; //Game
	
	private Piece localPiece; //Local piece
	private Piece turn; //Piece who can play
	private List<Piece> pieces; //List of pieces
	
	private Board board; //Board
	
	private Player randPlayer;
	private Player aiPlayer;
	
	private Map<Piece, Color> pieceColors;
	private Map<Piece, PlayerMode> playerModes;

	private JPanel backgroundPane;
	private JPanel boardPanel;
	private JPanel sidebarPanel;
	private JTextArea statusArea;
	private JComboBox<Piece> playerModePieces;
	private JComboBox<Piece> playerColor;
	private JComboBox<PlayerMode> modes;
	private PlayerInfoTableModel tableModel;
	
	private JButton randomMove;
	private JButton intelligentMove;
	private JButton setMode;
	private JButton chooseColor;
	private JButton restart;
	private JButton quit;
	private String gameDesc;
	
	/**
	 * Available playermodes
	 * @author Dani-MacBookPro
	 *
	 */
	enum PlayerMode {
		MANUAL("Manual", "Manual"),
		RANDOM("Random", "Random"),
		AI("Automatic", "Automatic");

		private String id;
		private String desc;

		PlayerMode(String id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return id;
		}
	}
	
	/**
	 * Constructor for the Swing View
	 * @param g
	 * @param c
	 * @param localPiece
	 * @param randPlayer
	 * @param aiPlayer
	 */
	public SwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		this.game = g;
		this.ctrl = c;
		this.localPiece = localPiece;
		this.randPlayer = randPlayer;
		this.aiPlayer = aiPlayer;
		this.pieceColors = new HashMap<Piece, Color>();
		this.playerModes = new HashMap<Piece, PlayerMode>();
		
		createGUI();
		game.addObserver(this);
	}
	
	/**
	 * Creates the whole GUI
	 */
	private void createGUI() {
		this.backgroundPane = new JPanel(new BorderLayout()); //Crea el marco grande del fondo
		this.setContentPane(backgroundPane);
		
		boardPanel = new JPanel(new BorderLayout()); //Crea el trozo que se usará para el tablero
		backgroundPane.add(boardPanel, BorderLayout.CENTER); //Lo coloca en la posición CENTER
		
		startBoardGUI(); //Esto inicializa la tabla
		
		sidebarPanel = new JPanel(); //Crea la barra lateral
		sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS)); //Lo establece como vertical
		backgroundPane.add(sidebarPanel, BorderLayout.LINE_END); //Lo añade al lado derecho de la ventana
	
		startSidebarGUI(); //Esto inicializa el lateral
		
		this.addWindowListener(new WindowListener() {
			//Creo que esto es para elegir las acciones que ejecutar en caso de que se haga algo con la ventana
			public void windowClosing(WindowEvent e) {
				quit();
			}

			public void windowOpened(WindowEvent e) {
			}
			@Override
			public void windowIconified(WindowEvent e) {
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
			
		});
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/**
	 * Quits the game (with confirmation)
	 */
	protected void quit() { //Method to execute when the user attempts to quit the game
		if (JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to exit the game?", "Exit confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == 0)
		{
			try 
			{
				ctrl.stop();
			}
			catch (GameError _e) {}
			setVisible(false);
			dispose();
			System.exit(0);	
		}
	}

	/**
	 * Getter
	 * @return
	 */
	final protected Piece getTurn() {
		return turn;
	}
	/**
	 * Getter
	 * @return
	 */
	final protected Board getBoard() {
		return board;
	}
	/**
	 * Getter
	 * @return
	 */
	final public List<Piece> getPieces() {
		return pieces;
	}
	/**
	 * Getter
	 * @param p
	 * @return
	 */
	final protected Color getPieceColorFromSwing(Piece p) {
		return pieceColors.get(p);
	}
	/**
	 * Setter
	 * @param p
	 * @param c
	 * @return
	 */
	final protected Color setPieceColor(Piece p, Color c) {
		return pieceColors.put(p,c);
	}
	/**
	 * Setter for the board area
	 * @param c
	 */
	final protected void setBoardArea(JComponent c) {
		boardPanel.add(c, BorderLayout.CENTER);
	}
	/**
	 * Adds a message to the sidebar
	 * @param msg
	 */
	final protected void addMsg(String msg)
	{
		statusArea.append(msg + "\n");
	}
	/**
	 * Makes a manual move
	 * @param manualPlayer
	 */
	final protected void decideMakeManualMove(Player manualPlayer) {
		try {
			ctrl.makeMove(manualPlayer);
		} catch (GameError e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Make an auto move
	 */
	final protected void decideMakeAutomaticMove() {
		if(playerModes.get(turn) == PlayerMode.AI)
		{
			intelligentMove();
		}
		else if(playerModes.get(turn) == PlayerMode.RANDOM)
		{
			randomMove();
		}
	}
	
	protected abstract void startBoardGUI();
	
	/**
	 * Starts the sidebar GUI
	 */
	protected void startSidebarGUI()
	{
		addStatusMessagesTextArea();
		addPlayerInfoTable();
		addPlayersColors();
		addPlayersModes();
		addButtons();
	}
	
	/**
	 * Adds both button panels
	 */
	private void addButtons()
	{
		addAutoPlayersButtons();
		addBottomButtons();
	}
	
	/**
	 * Adds quit and restart
	 */
	private void addBottomButtons()
	{
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //Creates the bottom panel for the buttons
		quit = new JButton("Quit");	//Creates the quit button
		quit.addActionListener(new ActionListener() { //Adds the action listener to the button
			@Override
			public void actionPerformed(ActionEvent e) {
				quit(); //Quit when it is clicked
			}
		});
		bottomPanel.add(quit); //Adds quit button to the bottom panel
		
		restart = new JButton("Restart"); //Same as quit button
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					ctrl.restart(); //Calls the controller to restart the game
					resetCounter();
				}
				catch(GameError _e){}
			}
		});
		bottomPanel.add(restart); //Adds restart button to the bottom panel
		
		addToSidePanel(bottomPanel); //Adds bottom panel to the side panel
	}

	/**
	 * Needed for ATTT on restart
	 */
	protected abstract void resetCounter();

	/**
	 * Adds the random and intelligent button
	 */
	private void addAutoPlayersButtons()
	{
		JPanel autoPlayerPanel = new JPanel(new GridLayout(0,2,5,5)); //Creates a panel with a grid of 2 columns and gaps of 5 units
		autoPlayerPanel.setBorder(BorderFactory.createTitledBorder("Automatic Moves")); //Creates and sets the label of the border
		autoPlayerPanel.setSize(new Dimension(10,50)); //Sets the size for the panel
		randomMove = new JButton("Random");	 //Creates the random move button
		randomMove.addActionListener(new ActionListener() { //Same as quit and restart buttons
			@Override
			public void actionPerformed(ActionEvent e) {
				randomMove(); //Makes a random move
			}
		});
		autoPlayerPanel.add(randomMove); //Adds the button to the Auto players panel
		
		intelligentMove = new JButton("Intelligent");
		intelligentMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				intelligentMove(); //Makes an intelligent move
			}
		});
		autoPlayerPanel.add(intelligentMove);
		
		addToSidePanel(autoPlayerPanel);
	}

	/**
	 * Makes an intelligent move
	 */
	protected void intelligentMove() {
		ctrl.makeMove(aiPlayer);
		tableModel.refresh();
	}

	/**
	 * Makes a random move
	 */
	protected void randomMove() {
		ctrl.makeMove(randPlayer);
		tableModel.refresh();
	}

	/**
	 * Adds the player modes panel
	 */
	private void addPlayersModes() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); //Creates JPanel
		panel.setBorder(BorderFactory.createTitledBorder("Player Modes")); //Creates titled border

		playerModePieces = new JComboBox<Piece>(new DefaultComboBoxModel<Piece>());	//Creates the left comboBox
		modes = new JComboBox<PlayerMode>(new DefaultComboBoxModel<PlayerMode>()); //Creates the right comboBox
		modes.addItem(PlayerMode.MANUAL); //Add the options in the ComboBox from the enum
		if(randPlayer != null)
		{
			modes.addItem(PlayerMode.RANDOM);
		}
		if(aiPlayer != null)
		{
			modes.addItem(PlayerMode.AI);
		}
		setMode = new JButton("Set Mode"); //Adds button and action listener
		setMode.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Piece selectedPiece = (Piece) playerModePieces.getSelectedItem();
			PlayerMode selectedMode = (PlayerMode) modes.getSelectedItem();
			PlayerMode currentMode = playerModes.get(selectedPiece);
			playerModes.put(selectedPiece, selectedMode);
			if(currentMode == PlayerMode.MANUAL && selectedMode != PlayerMode.MANUAL && turn.equals(localPiece))
				decideMakeAutomaticMove();
			}
		});
		panel.add(playerModePieces);
		panel.add(modes);
		panel.add(setMode);
		
		addToSidePanel(panel);
	}
	
	/**
	 * Adds the panel for the players to choose their colors
	 */
	private void addPlayersColors()
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(BorderFactory.createTitledBorder("Piece Colors"));
		playerColor = new JComboBox <Piece>();
		
		chooseColor = new JButton ("Choose Color");
		chooseColor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Piece p = (Piece) playerColor.getSelectedItem();
				ColorChooser c = new ColorChooser(new JFrame(),"Select a color for your piece", pieceColors.get(p));
				if(c.getColor() != null){
					pieceColors.put(p, c.getColor());
					repaint();
				}
			}
		});
		panel.add(playerColor);
		panel.add(chooseColor);
		
		addToSidePanel(panel);
	}

	/**
	 * Adds the player information table to the side
	 */
	private void addPlayerInfoTable()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Player Information"));
		
		tableModel = new PlayerInfoTableModel();
		JTable table = new JTable(tableModel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col){ 
				Component comp = super.prepareRenderer(renderer, row, col);
				comp.setBackground(pieceColors.get(pieces.get(row)));
				if(pieceColors.get(pieces.get(row)) == Color.BLACK || pieceColors.get(pieces.get(row)) == Color.blue)
				{
					comp.setForeground(Color.white);
				}
				else
				{
					comp.setForeground(Color.black);
				}
				return comp;
			}
		};
		panel.add(table);
		
		addToSidePanel(panel);
	}

	/**
	 * Adds the status messages area
	 */
	private void addStatusMessagesTextArea()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(100, 150));
		panel.setBorder(BorderFactory.createTitledBorder("Status Messages"));
		statusArea = new JTextArea(5, 10);
		statusArea.setEditable(false); //Can't be edited
		
		JScrollPane statusAreaScroll = new JScrollPane(statusArea); //Creates a scrollPane as well
		panel.add(statusAreaScroll, BorderLayout.CENTER);
			
		addToSidePanel(panel);
	}

	/**
	 * Adds a panel to the side
	 * @param panelToAdd
	 */
	private void addToSidePanel(JPanel panelToAdd) {
		sidebarPanel.add(panelToAdd);
	}

	protected abstract void activateBoard();
	protected abstract void deActivateBoard();
	protected abstract void redrawBoard();
	
	//GameObserver Methods
	public void onGameStart(final Board board, final String gameDesc, final List<Piece> pieces, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				handleGameStart(board, gameDesc, pieces, turn);
			}
		});
	}
	
	/**
	 * What it should do at start
	 * @param board
	 * @param gameDesc
	 * @param pieces
	 * @param turn
	 */
	private void handleGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		this.setTitle("Board games: " + gameDesc);
		addMsg("Game started");
		this.turn = turn;
		this.board = board;
		this.pieces = pieces;
		this.gameDesc = gameDesc;
		initPiecesColors();
		initPlayerModes();
		redrawBoard();
		
		handleOnChangeTurn(board, turn);
	}

	/**
	 * Initiates the player modes
	 */
	private void initPlayerModes() {
		if(playerModePieces != null) {
			if(localPiece == null)
			{
				for(Piece p: pieces)
				{
					if(playerModes.get(p) == null)
					{
						playerModes.put(p, PlayerMode.MANUAL);
						playerModePieces.addItem(p);
					}
				}
			}
			else
			{
				if(playerModes.get(localPiece) == null)
				{
					playerModes.put(localPiece, PlayerMode.MANUAL);
					playerModePieces.addItem(localPiece);
				}
			}
		}
	}

	/**
	 * Initiates the colors of the pieces
	 */
	private void initPiecesColors() 
	{
		int counter = 0;
		for(Piece p : pieces){
			if(pieceColors.get(p) == null)
			{
				switch(counter)
				{
					case 0: pieceColors.put(p, Color.RED); break;
					case 1: pieceColors.put(p, Color.BLACK); break;
					case 2: pieceColors.put(p, Color.WHITE); break;
					case 3: pieceColors.put(p, Color.BLUE); break;
					default: break;
				}
				counter++;
			}
			playerColor.addItem(p);
		}
	}
	
	/**
	 * Enables the view
	 */
	private void enableView()
	{
		enableButtons();
		activateBoard();
	}
	
	/**
	 * Disables the view
	 */
	private void disableView()
	{
		disableButtons();
		deActivateBoard();
	}

	/**
	 * Disables the buttons
	 */
	private void disableButtons() {
		quit.setEnabled(false);
		restart.setEnabled(false);
		randomMove.setEnabled(false);
		intelligentMove.setEnabled(false);
		setMode.setEnabled(false);
		chooseColor.setEnabled(false);
	}

	/**
	 * Enables the buttons
	 */
	private void enableButtons() {
		quit.setEnabled(true);
		restart.setEnabled(true);
		randomMove.setEnabled(true);
		intelligentMove.setEnabled(true);
		setMode.setEnabled(true);
		chooseColor.setEnabled(true);
	}

	@Override
	public void onGameOver(final Board board, final State state, final Piece winner) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				handleOnGameOver(board, state, winner);
			}
		});
	}

	/**
	 * What the program should do at the end of the game
	 * @param board
	 * @param state
	 * @param winner
	 */
	protected void handleOnGameOver(Board board, State state, Piece winner) {
		this.board = board;
		redrawBoard();
		if(state == State.Won)
		{
			addMsg(winner.toString() + " won the game!");
			JOptionPane.showMessageDialog(new JFrame(), winner.toString() + " is the winner!!!", "Match result", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (state == State.Draw)
		{
			addMsg("Ha habido un empate :(");
			JOptionPane.showMessageDialog(new JFrame(), "The game finished with a draw", "Match result", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void onMoveStart(final Board board, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				handleOnMoveStart(board, turn);
			}
		});
	}

	/**
	 * What to do at the start of a move
	 * @param board
	 * @param turn
	 */
	protected void handleOnMoveStart(Board board, Piece turn) {
		//Empty method
	}

	@Override
	public void onMoveEnd(final Board board, final Piece turn, final boolean success) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				handleOnMoveEnd(board, turn, success);
			}
		});
		
	}

	/**
	 * What to do at the end of a move
	 * @param board
	 * @param turn
	 * @param success
	 */
	protected void handleOnMoveEnd(Board board, Piece turn, boolean success) {
		this.board = board;
		this.turn = turn;
		tableModel.refresh();
	}

	@Override
	public void onChangeTurn(final Board board, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				handleOnChangeTurn(board, turn);
			}
		});
	}

	/**
	 * What to do when the turn changes
	 * @param board
	 * @param turn
	 */
	protected void handleOnChangeTurn(Board board, Piece turn) {
		this.board = board;
		this.turn = turn;
		redrawBoard();
		addMsg("Turn for player '" + turn + "'");
		if(Main.isMultiviews() || Main.isClientMode())
		{
			this.setTitle("Board games: " + gameDesc + " View from: " + localPiece + " (Turn for: " + turn.getId() + ")");
			if(turn.equals(localPiece) && playerModes.get(turn) == PlayerMode.MANUAL)
			{
				enableView();
			}
			else
			{
				disableView();
			}
		}
		else
		{
			this.setTitle("Board games: " + gameDesc + " (Turn for: " + turn + ")");
			enableView();
		}
		
		if(playerModes.get(turn) == PlayerMode.AI)
		{
			intelligentMove();
		}
		else if(playerModes.get(turn) == PlayerMode.RANDOM)
		{
			randomMove();
		}
	}

	@Override
	public void onError(final String msg) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				handleOnError(msg);
			}
		});
	}

	/**
	 * What to do on error
	 * @param msg
	 */
	protected void handleOnError(String msg) {
		if((turn.equals(localPiece) && playerModes.get(turn) == PlayerMode.MANUAL) || !Main.isMultiviews())
		{
			addMsg(msg);
			JOptionPane.showMessageDialog(new JFrame(), msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * The class for the table model
	 * (It needs to be here to get the attributes easier)
	 * @author Dani-MacBookPro
	 *
	 */
	class PlayerInfoTableModel extends AbstractTableModel{
		private String[] colNames;
		
		PlayerInfoTableModel(){
			this.colNames = new String[] {"Player", "Mode", "#Pieces"};
		}
		
		@Override
		public String getColumnName(int col){
			return colNames[col];
		}
		
		@Override
		public int getColumnCount(){
			return colNames.length;
		}
		
		@Override
		public int getRowCount(){
			if(pieces == null)
			{
				return 0;
			}
			else
			{
				return pieces.size();
			}
		}
		
		@Override
		public Object getValueAt(int row, int col){
			if(pieces == null){
				return null;
			}
			Piece p = pieces.get(row);
			
			switch(col)	
			{
				case 0: return p;
				case 1: return playerModes.get(p);
				default: return board.getPieceCount(p);
			}
		}
		
		public void refresh() {
			fireTableDataChanged();
		}
	}
}