package Tests;

import static org.junit.Assert.*;
import lib.R;

import org.junit.Before;
import org.junit.Test;

import Game.Board;
import Game.Board.SquareState;
import Game.Board.ValueWinner;
import Game.Board.Winner;

public class BoardTest {
	
	Board b;
	R r;
	@Before
	public void setUp(){
		b = new Board();
		r = new R(Board.class);
		setBoardTo(SquareState.EMPTY);
	}
	@Test
	public void testRowWinner(){
		setRowTo(0, SquareState.CROSS);
		assertEquals(Winner.CROSS, b.getWinner());
	}
	
	@Test
	public void testColWinner(){
		setColTo(0, SquareState.CROSS);
		assertEquals(Winner.CROSS, b.getWinner());
	}
	
	@Test
	public void testNoWinner(){
		assertEquals(Winner.NONE, b.getWinner());
	}
	
	@Test
	public void testDiagonalWinner(){
		setDiagonal(SquareState.CROSS);
		assertEquals(Winner.CROSS, b.getWinner());
		
	}
	
	@Test
	public void testInverseDiagonalWinner(){
		setInverseDiagonal( SquareState.CROSS );
		assertEquals(Winner.CROSS, b.getWinner());
	}
	
	@Test
	public void testTie(){
		b.set( 0, 0, SquareState.NOTCH);
		b.set( 0, 1, SquareState.CROSS);
		b.set( 0, 2, SquareState.NOTCH);

		b.set( 1, 0, SquareState.NOTCH);
		b.set( 1, 1, SquareState.CROSS);
		b.set( 1, 2, SquareState.CROSS);
		
		b.set( 2, 0, SquareState.CROSS);
		b.set( 2, 1, SquareState.NOTCH);
		b.set( 2, 2, SquareState.NOTCH);
		
		assertEquals(Winner.TIE, b.getWinner());
	}
	
	@Test
	public void numberOfTest(){
		setBoardTo(SquareState.EMPTY);
		assertEquals(9, b.numberOf( SquareState.EMPTY ));
	}
	
	@Test
	public void sameValueRow(){
		setRowTo(0, SquareState.CROSS);
		ValueWinner val = r.invoke( "sameNonEmptyValueRow", b, new int[]{0} );
		assertTrue(val.value);
		assertEquals(Winner.CROSS, val.winner);
	}
	
	@Test
	public void notSameValueRow(){
		b.set( 0, 0, SquareState.CROSS );
		ValueWinner val = r.invoke( "sameNonEmptyValueRow", b, new int[]{0} );
		assertFalse(val.value);
	}
	
	@Test 
	public void sameValueCol(){
		setColTo(0, SquareState.NOTCH);
		ValueWinner val = r.invoke( "sameNonEmptyValueCol", b, new int[]{0} );
		assertTrue(val.value);
		assertEquals(Winner.NOTCH, val.winner);
	}
	
	@Test
	public void winnerInRow(){
		setRowTo( 0, SquareState.CROSS );
		ValueWinner val = r.invoke( "existsSameNonEmptyValueRow", b);
		assertTrue(val.value);
		assertEquals(Winner.CROSS, val.winner);
	}
	
	
	private void setRowTo(int row, SquareState value){
		for(int i = 0; i < 3; i++){
			b.set( row, i, value );
		}
	}
	
	private void setColTo(int col, SquareState value){
		for(int i = 0; i < 3; i++){
			b.set( i, col, value );
		}
	}
	
	private void setBoardTo(SquareState value){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				b.set( i, j, value );
			}
		}
	}
	
	private void setDiagonal(SquareState value){
		b.set( 0, 0, value );
		b.set( 1, 1, value );
		b.set( 2, 2, value );
	}
	
	private void setInverseDiagonal(SquareState value){
		b.set( 0, 2, value );
		b.set( 1, 1, value );
		b.set( 2, 0, value );
	}
}
