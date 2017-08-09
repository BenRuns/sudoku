package com.sudoku.domain;

import org.junit.Test;


import com.sudoku.domain.Sudoku;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuTest {


  @Test
  public void loads() {
    int[][] grid = new int[9][9];
    Sudoku sudoku = new Sudoku(grid);
  }


  @Test
  public void changeIsValid() {

    int[][] grid =  {

        { 0,0,0, 0,0,0, 0,0,3 },
        { 0,0,0, 0,0,0, 0,0,0 },
        { 2,0,0, 0,0,0, 1,0,0 },

        { 0,0,0, 0,0,0, 0,0,0 },
        { 0,9,0, 0,6,0, 0,0,0 },
        { 0,0,0, 0,0,0, 0,0,0 },

        { 0,0,0, 0,0,0, 0,0,0 },
        { 0,0,0, 0,7,0, 0,0,0 },
        { 0,0,0, 0,0,0, 0,0,3 }

    };
    Sudoku sudoku = new Sudoku(grid);
    assertFalse(sudoku.changeIsValid(0, 0, 2));
    assertTrue(sudoku.changeIsValid(1, 1, 3));
    assertFalse(sudoku.changeIsValid(6, 6, 3));
    assertFalse(sudoku.changeIsValid(6, 6, 1));
    assertTrue(sudoku.changeIsValid(8, 4, 8));

  }


}
