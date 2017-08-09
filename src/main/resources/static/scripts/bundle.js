// register the grid component
(function(){
  new Vue({
    el: '#sudoku',
    data: {
      grid: createNewBoard()
    },
    computed: {
      processedGrid: function () {
        return this.grid;
      }
    },
    methods: {
      updateErrors: function(){
        let that = this;
        $.ajax({
          url:'/api/get-errors',
          contentType:'application/json',
          data:{'grid': JSON.stringify(that.grid.map(row => row.map(val=> val.val || 0 ))) }
        }).done(function(data){
            for(var i = 0; i < 9; i++){
              for(var j= 0; j < 9; j++){
                that.grid[i][j].hasError = data[i][j];
              }
            }
        });
      }
    }
  });


function createNewBoard(){
  let example = [
    [0,0,1, 0,0,0, 0,0,6 ],
    [9,0,7, 0,5,6, 0,0,0 ],
    [2,0,0, 0,0,1, 5,0,0 ],

    [0,5,0, 0,3,0, 0,1,0 ],
    [7,0,0, 1,0,4, 0,0,3 ],
    [0,2,0, 0,8,0, 0,6,0 ],

    [0,0,5, 7,0,0, 0,0,1 ],
    [0,0,0, 2,9,0, 6,0,7 ],
    [3,0,0, 0,0,0, 9,0,0 ]
  ];

  let board = [];
  for(let i = 0; i< 9; i++){
    board[i] =[];
    for (let j = 0;j < 9; j++){
      var val = example[i][j];
      board[i][j] = { hasError:0, val:val, disabled:val }
    }
  }
  return board;
}


})();


