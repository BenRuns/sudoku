package com.sudoku.domain;
import java.util.ArrayList;

/**
 * @author ben
 *
 */
/**
 * @author ben
 *
 */
/**
 * @author ben
 *
 */
/**
 * @author ben
 *
 */
public class SudokuWithErrors extends Sudoku{

  private int[][] errors;


  /**
   * @param grid 2d grid populated with value 1-9 and zeros for emptys
   */
  public SudokuWithErrors(int[][] grid) {
    super(grid);
    this.errors = new int[grid[0].length][grid[1].length];
  }


  /**
   *  Mutates the error int[][] grid.
   */
  public void generateErrors(){
    for(int i = 0; i < this.grid[0].length; i++){
      generateRelatedRowAndColErrors(i, i);
    }

    for(int i = 0; i < this.grid[0].length; i += this.regionSize){
      for(int j = 0; j < this.grid[0].length; j += this.regionSize){
        generateRegionErrors(i, j);
      }
    }
  }


  /**
   * @return 2d array of errors marked by the value 1
   */
  public int[][] getErrors(){
    return this.errors;
  }


 

  /**
   * @param row
   * @param col
   * Generates all the errors in a row an colum
   */
  public void generateRelatedRowAndColErrors(int row, int col){

    ArrayList<ArrayList<Integer>> rowOccurrences = new ArrayList<ArrayList<Integer>>(this.grid.length);
    loadArrayList(rowOccurrences);
    ArrayList<ArrayList<Integer>> colOccurrences = new ArrayList<ArrayList<Integer>>(this.grid.length);
    loadArrayList(colOccurrences);

    for(int i = 0; i < this.grid.length; i++){
        int rowVal = this.grid[row][i];
        int colVal = this.grid[i][col];
        updateOccurrences(rowVal,i, rowOccurrences);
        updateOccurrences(colVal,i, colOccurrences);
    }

    for(int i = 0; i < this.grid.length; i ++){
      ArrayList<Integer> colLocations = colOccurrences.get(i);
      ArrayList<Integer> rowLocations = rowOccurrences.get(i);
      if (colLocations.size() > 1) {
        addColErrors(col, colLocations);
      }
      if (rowLocations.size() > 1) {
        addRowErrors(row, rowLocations);
      }
    }

  }

  
  /**
   * @param row
   * @param col
   * generates all the errors in  region
   */
  public void generateRegionErrors(int row, int col){
    ArrayList<ArrayList<Integer>> occurrences = new ArrayList<ArrayList<Integer>>();
    loadArrayList(occurrences);

    int rowStart = row - row % this.regionSize;
    int colStart = col - col % this.regionSize;
    for(int i = 0; i < this.regionSize; i++){
      for(int j = 0; j  < this.regionSize; j ++){
        int val = this.grid[rowStart + i][colStart + j];
        if (val != 0) {
          ArrayList<Integer> locations = occurrences.get(val-1);

          //order matters
          locations.add(rowStart + i);
          locations.add(colStart + j);
        }
      }
    }

    for(int i = 0; i < this.regionSize * (this.grid[0].length / this.regionSize); i++){
      ArrayList<Integer> locations = occurrences.get(i);
      if (locations.size() > 2){
        for(int j = 0; j < locations.size(); j+=2){
          int errorRow = locations.get(j);
          int errorCol = locations.get(j+1);

          this.errors[errorRow][errorCol] = 1;
        }
      }

    }

  }



  private void updateOccurrences(int val, int loc,ArrayList<ArrayList<Integer>> occurrences ){
    if (val == 0) { return;  }
    ArrayList<Integer> locations = occurrences.get(val - 1);
    locations.add(loc);
  }

  private void addColErrors(int col,ArrayList<Integer> locations ){
    for(int i = 0; i < locations.size(); i++ ){
      int row = locations.get(i);
      this.errors[row][col] = 1;
    }
  }

  private void addRowErrors(int row,ArrayList<Integer> locations ){
    for(int i = 0; i < locations.size(); i++ ){
      int col = locations.get(i);
      this.errors[row][col] = 1;
    }
  }

  private void loadArrayList(ArrayList<ArrayList<Integer>> arrlst){
    for(int i = 0; i < this.grid[0].length; i ++){
      arrlst.add(new ArrayList<Integer>());
    }

  }



}

