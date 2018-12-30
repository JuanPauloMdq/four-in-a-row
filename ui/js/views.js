var isVsComputer = false;
function hideAll(){
    $("#homeScreen").hide();
    $("#gameScreen").hide();
    $("#playerWonPopup").hide();
}

function showHomeScreen(){
  window.location.hash = 'home';
}

function startGame(){
  isVsComputer = false;
  if(window.location.hash == '#game'){
    displayGameScreen();
  } else {
    window.location.hash = 'game';  
  }
}

function startGameVsComputer(){
  isVsComputer = true;
  if(window.location.hash == '#game'){
    displayGameScreen();
  } else {
    window.location.hash = 'game';  
  }
}


function displayHomeScreen(){
  console.log("Displaying home");
  hideAll();
  $("#homeScreen").show();
}

function displayGameScreen(){
  console.log("Starting game");
  hideAll();
  newGame(isVsComputer);
  if(isVsComputer){
    $("#playerComputerIndicator").show();
    $("#playerBlueIndicator").hide();
  } else {
    $("#playerComputerIndicator").hide();
    $("#playerBlueIndicator").show();
  }
  $("#gameScreen").show();
}

function showPlayerWon(player){
  if(player == PLAYER_BLUE){
    if(isVsComputer){
      $("#playerWonMessage")[0].style.backgroundImage = 'url("./images/computer-won.png")';  
    }
    else {
      $("#playerWonMessage")[0].style.backgroundImage = 'url("./images/blue-won.png")';  
    }

  }
  if(player == PLAYER_RED){
    $("#playerWonMessage")[0].style.backgroundImage = 'url("./images/red-won.png")';
  }

  $("#playerWonPopup").fadeIn();
}

$(window).bind( 'hashchange', function() {
  if(window.location.hash == "" || window.location.hash == "#home"){
    displayHomeScreen();
  }
  if(window.location.hash == "#game"){
    displayGameScreen();
  }
});


  
