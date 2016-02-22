package Launcher;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Game.Board;
import Game.Board.SquareState;
import Tests.BoardTest;

public class TestLauncher {

	public static void testWinnerRow(){
		
	}
	
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(BoardTest.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	}
}
