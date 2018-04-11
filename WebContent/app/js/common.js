// JavaScript Document

// JavaScript Document

$(window).load(function(){
$(window).on("scroll", function() {
    if($(window).scrollTop() > 50) {
    	//alert("scroll");
        $(".header").addClass("active");
		$(".logo").addClass("active");
    } else {
        //remove the background property so it comes transparent again (defined in your css)
       $(".header").removeClass("active");
	    $(".logo").removeClass("active");
    }
});

$(window).scroll(function() {    
	var scroll = $(window).scrollTop();
	if (scroll >= 50) {
        $("#menu").addClass("menutopscroll");
		$("#menu").removeClass("menutop");
    }
	else {
        //remove the background property so it comes transparent again (defined in your css)
		$("#menu").addClass("menutop");
		$("#menu").removeClass("menutopscroll");
	    
    }
});

 /* $("a[href='#top']").click(function() {
     $("html, body").animate({ scrollTop: 5000 }, "slow");
     return false;
  });*/
  
	 /*$( ".loginClick").click(function(){
        $("#loginDiv").show();
    });
	$( ".hideBox").click(function(){
        $("#loginDiv").hide();
    });*/
	
	
});//]]> 

/////////////////// login drop down menu/////////////////////
	function DropDown(el) {
				this.dd = el;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						event.stopPropagation();
					});	
				}
			}

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-5').removeClass('active');
				});

			});
			
/////////////////// toggle Menu/////////////////////
			
/*$(document).ready(function () {
	
	$('#menu-toggle').click(function(){
		if($('#menu').hasClass('open')){
			$('#menu').removeClass('open');
			$('#menu-toggle').removeClass('open');
		}else{
			$('#menu').addClass('open');
			$('#menu-toggle').addClass('open');
		}
	});
});*/


/******************accordian function start************************/
/*$(window).load(function(){

	  
		 
	 $('.accordionStepHead').mouseover(function() { //for mouseover
	 
        $(this).addClass('over');
    }).mouseout(function() {
        $(this).removeClass('over');                                        
    });
	$('.firstHead').addClass('on');
    $('.accordionContent').hide(); // intional accordian hide
	$('.firstContent').show(); // first content show
	
	
	
    $('.openTabLink').click(function() { // on next button the Next accordian open
	    $(this).parents('.accordionContent').prev('.accordionStepHead').addClass('pointer');
		  $('.accordionStepHead').removeClass('on');
         $('.accordionContent').slideUp('normal');
		if($('.accordionStepHead').next().is(':hidden') == true) {
            $(this).parents('.accordionContent').next('.accordionStepHead').addClass('on');
            $(this).parents('.accordionContent').next('.accordionStepHead').next().slideDown('normal');

         }
		 return false;           
        });
		
	 $('.openLast').click(function() { 
		// alert("My Name is asitava");
		 $(".accordionStepHead").addClass('pointer');
	 });
	 
	 
		 $('.accordionStepHead').click(function() {
			 if($(this).hasClass( "pointer" )) {
				$('.accordionStepHead').removeClass('on');
				 $('.accordionContent').slideUp('normal');
				
				if($(this).next().is(':hidden') == true) {
					//alert("true");
					$(this).addClass('on');
					$(this).next().slideDown('normal');
				 } 
		 }  
     })
});*/

/*swap Naav Toggle*/
$(function () {
    $(".downloadCont > div:gt(0)").hide();
	
    $(".downButt a").on("click", function (e) {
		$('.downButt a').removeClass('selected');
   		 $(this).addClass('selected');
        var href = $(this).attr("href");
        $(".downloadCont > " + href).show();
        $(".downloadCont > :not(" + href + ")").hide();
	});
	
});

/////////////////////menu add-class//////////////









