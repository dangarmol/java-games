package es.ucm.fdi.tp.practica4.ataxx;

import java.util.ArrayList;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.control.ConsolePlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.views.GenericConsoleView;
import es.ucm.fdi.tp.basecode.connectn.ConnectNFactory;

public class AtaxxFactory extends ConnectNFactory {
	
	private static final long serialVersionUID = 1L;
	private int dim;
	private int obs;
	
	
	public AtaxxFactory(int dim, int obsNum) {
		super(dim); 
		
		if (dim % 2 == 0 || dim < 5) {
			throw new GameError("Dimension must be odd and greater than five");
		} else {
			this.dim = dim;
			this.obs = obsNum;
		}
	}

	//Default
	public AtaxxFactory(){
		this(5, 0);
	}
	
	@Override
	public GameRules gameRules() {
		return new AtaxxRules(dim,this.obs);
	}
	
	@Override
	public Player createConsolePlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new AtaxxMove());
		return new ConsolePlayer(new Scanner(System.in), possibleMoves);
	}
	
	@Override
	public void createConsoleView(Observable<GameObserver> g, Controller c) {
		new GenericConsoleView(g, c);
	}
	
	@Override
	public Player createRandomPlayer() {
		return new AtaxxRandomPlayer();
	}

}
