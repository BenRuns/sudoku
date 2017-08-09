package com.sudoku.domain;

/**
 * @author ben
 *
 */
public class Sudoku {

  protected int regionSize;
  protected int[][] grid;


  /**
   * @param grid existing grid. Empty locations should have zeros and not null
   */
  public Sudoku(int[][] grid){
    this.regionSize = 3;
    this.grid = grid;
  }


  /**
   * @param row  y value
   * @param col  x value
   * @param replacement   value to put in grid
   * @return boolean that confirms if replacing is legal
   */
  public boolean changeIsValid(int row, int col, int replacement){

    for (int i = 0; i < this.grid[0].length; i++) {
        if(row != i || col != i){
          if(this.grid[row][i] == replacement || this.grid[i][row] == replacement){
            return false;
          }
        }
    }
    int regionRowStart = row - row % this.regionSize;
    int regionColStart = col - col % this.regionSize;
    for (int i = 0; i < this.regionSize; i++) {
      for (int j = 0; j < this.regionSize; j++) {

        int currentRow = regionRowStart + i;
        int currentCol = regionColStart + j;
        if (currentRow != row || currentCol != col){
          if(this.grid[currentRow][currentCol] == replacement){
            return false;
          }
        }
      }
    }
    return true;
  }

  public void replace(int row, int col, int val){
    this.grid[row][col] = val;
  }
}

