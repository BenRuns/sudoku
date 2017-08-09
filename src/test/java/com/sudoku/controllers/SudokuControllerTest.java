/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sudoku.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@WebMvcTest(SudokuController.class)
public class SudokuControllerTest {


	@Autowired
	private MockMvc mvc;

	@Autowired
	private ApplicationContext applicationContext;


	@Test
	public void checkIsValid() throws Exception {
	  
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
	  
	   
		this.mvc.perform(
		    get("/api/is-change-valid")
		     .accept(MediaType.APPLICATION_JSON)
		     .param("row","3" )
		     .param("col", "1")
		     .param("replacement", "5")
		     .param("grid", Arrays.deepToString(grid))
		     )
				.andExpect(status().isOk()).andExpect(content().json("{ \"isChangeValid\":true }"));
	}

  @Test
  public void getErrors() throws Exception {
    
     int[][] grid =  {

         { 0,5,0, 0,0,0, 0,0,0 },
         { 0,2,0, 0,0,0, 0,2,1 },
         { 0,0,5, 0,0,0, 0,0,0 },

         { 0,0,0, 0,2,0, 0,0,0 },
         { 0,4,0, 0,2,0, 0,0,7 },
         { 0,0,0, 0,0,0, 7,0,0 },

         { 0,0,0, 0,0,0, 9,0,7 },
         { 0,5,0, 0,0,0, 6,0,6 },
         { 0,0,0, 0,0,0, 0,8,5 }
     };
    
     
    this.mvc.perform(
        get("/api/get-errors")
         .accept(MediaType.APPLICATION_JSON)
         .param("grid", Arrays.deepToString(grid))
         )
        .andExpect(status().isOk())
        .andExpect(content()
        .json("["+
         "[0,1,0,0,0,0,0,0,0],"+
         "[0,1,0,0,0,0,0,1,0],"+
         "[0,0,1,0,0,0,0,0,0],"+
         "[0,0,0,0,1,0,0,0,0],"+
         "[0,0,0,0,1,0,0,0,1],"+
         "[0,0,0,0,0,0,1,0,0],"+
         "[0,0,0,0,0,0,0,0,1],"+
         "[0,1,0,0,0,0,1,0,1],"+
         "[0,0,0,0,0,0,0,0,0]]"
         ));
  }

}