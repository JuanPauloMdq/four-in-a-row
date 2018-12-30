var API_URL = "http://" + window.location.hostname +":8090/game/";


var apiServices = {
  createGame: function(callback){
    var response = {};
    $.ajax({
  	  type: "POST",
  	  url: API_URL + "create",
  	  data: "",
  	  cache: false,
  	  headers: {
  		'Content-Type': 'application/json'
  	  }
  	})
  	.done(function(result) {
      response.success = true;
      response.data = result;
      callback(response);
  	})
  	.fail(function(status) {
      response.success = false;
      response.data = status;
      callback(response);
  	});
  },
  
  addCoin: function(boardId, position, callback){
    var response = {};
    //Can be used to generate the code for the tests
    //console.log('board.addCoin(' + position + ');');
    
    $.ajax({
  	  type: "GET",
  	  url: API_URL + "addCoin/" + boardId + "/" + position,
  	  cache: false,
  	  headers: {
  		'Content-Type': 'application/json'
  	  }
  	})
  	.done(function(result) {
      response.success = true;
      response.data = result;
      callback(response);
  	})
  	.fail(function(status) {
      response.success = false;
      response.data = status;
      callback(response);
  	});
  },
  
  computerMovement: function(boardId, callback){
    var response = {};
    
    $.ajax({
  	  type: "GET",
  	  url: API_URL + "computer/" + boardId,
  	  cache: false,
  	  headers: {
  		'Content-Type': 'application/json'
  	  }
  	})
  	.done(function(result) {
      response.success = true;
      response.data = result;
      callback(response);
  	})
  	.fail(function(status) {
      response.success = false;
      response.data = status;
      callback(response);
  	});
  }
}
