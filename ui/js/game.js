var currentPlayerA = true;

$(document).ready(function(){
      $('#boardContainer').mousemove(function(event) { 
          updateHand(this, event);
      });
    
       $('#boardContainer').mouseover(function(event) { 
           updateHand(this, event);
       });
       $('#utilities').mouseout(function() {
           $("#blueHand").hide();
           $("#redHand").hide();
       });
   });
   
   
   function updateHand(container, event){
     var left = event.pageX - $(container).offset().left - 20;
     var left = left - (left % 80);
     if(currentPlayerA){
       
       $("#blueHand").show();
       $("#blueHand")[0].style.marginLeft = (left+25) + "px";
     } else {
       
       $("#redHand").show();
       $("#redHand")[0].style.marginLeft = (left-75) + "px";
     }
   }