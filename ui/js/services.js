var API_URL = "http://localhost:8090/game/";
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
  
  addCoin: function(gameId, position, callback){
    var response = {};
    //Can be used to generate the code for the tests
    //console.log('board.addCoin(' + position + ');');
    
    $.ajax({
  	  type: "GET",
  	  url: API_URL + "addCoin/" + gameId + "/" + position,
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
