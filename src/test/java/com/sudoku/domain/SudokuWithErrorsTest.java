package com.sudoku.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class SudokuWithErrorsTest {


  @Test
  public void loads() {
    int[][] grid = new int[3][3];

    SudokuWithErrors sudoku = new SudokuWithErrors(grid);
  }



  @Test
  public void generateRegionErrors() {

   int[][] grid =  {

       { 0,5,0, 0,0,0, 0,0,0 },
       { 0,1,0, 0,0,0, 0,0,0 },
       { 0,0,5, 0,0,0, 0,0,0 },

       { 0,0,0, 0,2,0, 0,0,0 },
       { 0,0,0, 0,2,0, 0,0,0 },
       { 0,0,0, 0,0,0, 0,0,0 },

       { 0,0,0, 0,0,0, 0,0,0 },
       { 0,0,0, 0,0,0, 0,0,0 },
       { 0,0,0, 0,0,0, 0,0,0 }
   };

    SudokuWithErrors sudoku = new SudokuWithErrors(grid);
    sudoku.generateRegionErrors(1, 1);
    int[][] errors = sudoku.getErrors();
    assertEquals(1,errors[0][1]);
    assertEquals(1,errors[2][2]);
    assertEquals(0,errors[1][1]);


    assertEquals(0,errors[3][4]);
    assertEquals(0,errors[4][4]);
    assertEquals(0,errors[4][5]);

    sudoku.generateRegionErrors(3, 3);
    assertEquals(1, errors[3][4]);
    assertEquals(1, errors[4][4]);
    assertEquals(0, errors[4][5]);

  }

  @Test
  public void generateRelatedRowAndColErrors() {

   int[][] grid =  {

       { 0,5,0, 0,0,0, 0,0,0 },
       { 0,2,0, 0,0,0, 0,2,1 },
       { 0,0,5, 0,0,0, 0,0,0 },

       { 0,0,0, 0,2,0, 0,0,0 },
       { 0,0,0, 0,2,0, 0,0,0 },
       { 0,0,0, 0,0,0, 0,0,0 },

       { 0,0,0, 0,0,0, 0,0,0 },
       { 0,5,0, 0,0,0, 0,0,0 },
       { 0,0,0, 0,0,0, 0,0,0 }
   };

    SudokuWithErrors sudoku = new SudokuWithErrors(grid);
    sudoku.generateRelatedRowAndColErrors(1, 1);
    int[][] errors = sudoku.getErrors();

    assertEquals(1,errors[1][1]);
    assertEquals(1,errors[1][7]);
    assertEquals(1,errors[7][1]);
    assertEquals(0,errors[1][2]);

    //shouldn't have errors generated yet
    assertEquals(0,errors[3][4]);
    assertEquals(0,errors[4][4]);
    assertEquals(0,errors[5][4]);

    //assert errors occured
    sudoku.generateRelatedRowAndColErrors(3, 4);
    assertEquals(1,errors[3][4]);
    assertEquals(1,errors[4][4]);
    assertEquals(0,errors[5][4]);
  }

  @Test
  public void generateErrors() {

   int[][] grid =  {

       { 0,5,0, 0,0,0, 0,0,0 },
       { 0,2,0, 0,0,0, 0,2,1 },
       { 0,0,5, 0,0,0, 0,0,0 },

       { 0,0,0, 0,2,0, 0,0,0 },
       { 0,0,0, 0,2,0, 0,0,7 },
       { 0,0,0, 0,0,0, 0,0,0 },

       { 0,0,0, 0,0,0, 9,0,7 },
       { 0,5,0, 0,0,0, 6,0,6 },
       { 0,0,0, 0,0,0, 0,8,5 }
   };

    SudokuWithErrors sudoku = new SudokuWithErrors(grid);
    sudoku.generateErrors();
    int[][] errors = sudoku.getErrors();


    assertEquals(1,errors[3][4]);
    assertEquals(1,errors[4][4]);
    assertEquals(0,errors[5][4]);

    assertEquals(1,errors[1][1]);
    assertEquals(1,errors[1][7]);
    assertEquals(1,errors[7][1]);
    assertEquals(0,errors[1][2]);

    assertEquals(1,errors[6][8]);
    assertEquals(1,errors[7][8]);


  }

}