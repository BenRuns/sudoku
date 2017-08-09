package com.sudoku.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sudoku.domain.Sudoku;
import com.sudoku.domain.SudokuWithErrors;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class SudokuController {


   @RequestMapping(path="/api/is-change-valid", produces = "application/json; charset=UTF-8")
   public HashMap<String, Boolean> isChangeValid(
      @RequestParam(value="row", required= true) int row,
      @RequestParam(value="col", required=true) int col,
      @RequestParam(value="replacement", required=true) int replacement,
      @RequestParam(value="grid", required=true) String stringGrid
    ){
      Sudoku sudoku = new Sudoku( hackyParse(stringGrid, 9));
      HashMap<String, Boolean> rval = new HashMap<String, Boolean>();
      rval.put("isChangeValid",sudoku.changeIsValid(row,col,replacement));
      return rval;
   }
   
   @RequestMapping(path="/api/get-errors", produces = "application/json; charset=UTF-8")
   public int[][] getErrors(
      @RequestParam(value="grid", required=true) String stringGrid
    ){
      SudokuWithErrors sudoku = new SudokuWithErrors( hackyParse(stringGrid, 9));
      sudoku.generateErrors();
      return sudoku.getErrors();
   }
   
   private int[][] hackyParse(String gridJSON, int gridSize){
     //TODO: replace with something built in
     int[][] rval = new int[gridSize][gridSize];
     int i = 0;
     int j = 0;
     for(int h = 0; h < gridJSON.length(); h++){
       String comparer = Character.toString(gridJSON.charAt(h));
       if(comparer.equals("]")){
         i ++;
         j = 0;
       } else if(comparer.matches("\\d+")){
         rval[i][j] = Integer.parseInt(comparer);
         j++;
       }
     }
     return rval;
   }
}


