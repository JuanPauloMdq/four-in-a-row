var BOARD_SIZE = 7;

var BOARD_EMPTY = 0;
var BOARD_BLUE_COIN = 1;
var BOARD_RED_COIN = 2;

var PLAYER_BLUE = 0;
var PLAYER_RED = 1;
var PLAYER_COMPUTER = 2;

var currentPlayerBlue = true;
var updatingBoard = false;
var updatingBackend = false;

var board = null; 
var currentGame = null;
var isGameVsComputer = false;

$(document).ready(function(){
    $('#boardContainer').mousemove(function(event) { 
      updateHand(this, event);
    });
  
     $('#boardContainer').mouseover(function(event) { 
       updateHand(this, event);
     });
     $('#boardContainer').mouseleave(function() {
       $("#blueHand").hide();
       $("#redHand").hide();
       $("#computerHand").hide();
     });
     
     $('#boardContainer').click(function() {
       if(!updatingBoard && !updatingBackend){
         addCoin(this, event);
       }
     });     
});
 
function newGame(vsComputer){
  isGameVsComputer = vsComputer;
  $( ".coin" ).remove();
 updatingBackend = true;
 apiServices.createGame(function(response){
   if(response.success){
     currentGame = response.data;
     currentPlayerBlue = response.data.gameState.currentPlayerBlue;
     updatingBackend = false;
   } else {
     // TODO: error
     updatingBackend = false;
   }
 });
 board = new Array(BOARD_SIZE);
 for (var i = 0; i < BOARD_SIZE; i++) {
   board[i] = new Array(BOARD_SIZE);
   
   for (var j = 0; j < BOARD_SIZE; j++) {
     board[i][j] = BOARD_EMPTY;
   }
 }
}
 
function coinCanBePlaced(position){
  return board[position][BOARD_SIZE - 1] == BOARD_EMPTY;
}

function placeCoin(position){
  for(var i=0; i<BOARD_SIZE; i++){
     if(board[position][i] == BOARD_EMPTY){
       board[position][i] = currentPlayerBlue ? BOARD_BLUE_COIN : BOARD_RED_COIN;

       return i;
     }
   }  
}

function addCoin(container, event){
  var position = getCurrentPosition(container, event);
  if(coinCanBePlaced(position)){
    playCoinSound();
    updatingBoard = true;
    updatingBackend = true;
    apiServices.addCoin(currentGame.gameId, position, function(response){
      if(response.success){
        // Check win
        if(response.data.gameState.gameEnded){
          playWinSound();
          showPlayerWon(response.data.gameState.winner);
        }
        
        updatingBackend = false;
      } else {
        // TODO: error
        updatingBackend = false;
      }
    });
     
    var newCoin = $('<div class="coin"></div>');
    if(currentPlayerBlue){
      newCoin.addClass("blueCoin");
    } else {
      newCoin.addClass("redCoin");
    }
    var verticalPosition = placeCoin(position);
    var verticalPositionPx = 507 - verticalPosition * 80 ;
    var fallingTime = 80 * (BOARD_SIZE - verticalPosition);
    $('#boardContainer').prepend(newCoin);
    newCoin[0].style.left = ((position * 80) + 27) + "px"
    newCoin[0].style.top = "0px"
    newCoin[0].style.position = "absolute";
   
    
    newCoin.animate({top: verticalPositionPx + "px"}, fallingTime, 'swing', function(){
      updatingBoard = false;
    });
    $("#blueHand").hide();
    $("#redHand").hide();
    $("#computerHand").hide();
    
    currentPlayerBlue = !currentPlayerBlue;
  }
}
 
function getCurrentPosition(container, event){
 var position = event.pageX - $(container).offset().left - 20;
 var position = Math.floor(position / 80);
 
 return Math.max(Math.min(position, BOARD_SIZE-1), 0);
}
 
function updateHand(container, event){
 if(!updatingBoard){
   var left = getCurrentPosition(container, event) * 80;

   if(currentPlayerBlue){
     if(isGameVsComputer){
       $("#computerHand").show();
       $("#computerHand")[0].style.marginLeft = (left+25) + "px";       
     } else {
       $("#blueHand").show();
       $("#blueHand")[0].style.marginLeft = (left+31) + "px";
     }
   } else {
     $("#redHand").show();
     $("#redHand")[0].style.marginLeft = (left-75) + "px";
   }       
 }
}

function playCoinSound(){
  var audio = new Audio('./sound/coin.wav');
  audio.play();
}

function playWinSound(){
  var audio = new Audio('./sound/win-sound.wav');
  audio.play();
}