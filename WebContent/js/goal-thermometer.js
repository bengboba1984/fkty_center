/**
 * @author Lance Snider - lance@lancesnider.com
*/

//editable vars
var goalAmount = 50;//how much are you trying to get
var startNumber=30;
var dataLen = 3;
var j=0;
var divName='goal-thermometer';
//var currentAmount = 501;//how much do you currently have (if you want to define in js, not html)
var animationTime = 1000;//in milliseconds
var numberPrefix = "";//what comes before the number (set to "" if no prefix)
var numberSuffix = "'C";//what goes after the number
var tickMarkSegementCount = 5;//each segement adds 40px to the height
var widthOfNumbers = 50;//the width in px of the numbers on the left
var ds = [800,600,900];
//standard resolution images
var glassTopImg = "image/glassTop.png";
var glassBodyImg = "image/glassBody.png";
var redVerticalImg = "image/redVertical.png";
var tooltipFGImg = "image/tickShine.png";
var glassBottomImg = "image/glassBottom.png";
var tootipPointImg = "image/tooltipPoint.png";
var tooltipMiddleImg = "image/tooltipMiddle.png";
var tooltipButtImg = "image/tooltipButt.png";

//high res images
var glassTopImg2x = "image/glassTop2x.png";
var glassBodyImg2x = "image/glassBody2x.png";
var redVerticalImg2x = "image/redVertical2x.png";
var tooltipFGImg2x = "image/tickShine2x.png";
var glassBottomImg2x = "image/glassBottom2x.png";
var tootipPointImg2x = "image/tooltipPoint2x.png";
var tooltipMiddleImg2x = "image/tooltipMiddle2x.png";
var tooltipButtImg2x = "image/tooltipButt2x.png";

/////////////////////////////////////////
// ------ don't edit below here ------ //
/////////////////////////////////////////

var arrayOfImages;
var imgsLoaded = 0;
var tickHeight = 40;
var mercuryHeightEmpty = 0;
var numberStartY = 6;
var thermTopHeight = 13;
var thermBottomHeight = 51;
var tooltipOffset = 15; 
var heightOfBody;
var mercuryId;
var tooltipId;
var resolution2x = false;


//this checks if it's the high or normal resolution images
function determineImageSet(maxAmount,sNumber,len,datas,dName){
	goalAmount = maxAmount;
	ds =  datas;
	divName = dName;
	startNumber = sNumber;
	dataLen = len;
	resolution2x = window.devicePixelRatio == 2;//check if resolution2x
	if(resolution2x){	
		//switch the regular for 2x res graphics
		glassTopImg = glassTopImg2x;
		glassBodyImg = glassBodyImg2x;
		redVerticalImg = redVerticalImg2x;
		glassBottomImg = glassBottomImg2x;
		tootipPointImg = tootipPointImg2x;
		tooltipButtImg = tooltipButtImg2x;	
	}
	
	createGraphics(divName);
}

//visually create the thermometer
function createGraphics(divName){
	//add the html
	$("#"+divName).html(
		"<div id='therm-numbers'>" + 
		"</div>" + 
		"<div id='therm-graphics'>" + 
			"<img id='therm-top' src='"+glassTopImg+"'></img>" + 
			"<img id='therm-body-bg' src='"+glassBodyImg+"' ></img>" + 
			"<img id='therm-body-mercury' src='"+redVerticalImg+"'></img>" + 
			"<div id='therm-body-fore'></div>" + 
			"<img id='therm-bottom' src='"+glassBottomImg+"'></img>" + 
			"<div id='therm-tooltip'>" + 
				"<img class='tip-left' src='"+tootipPointImg+"'></img>" + 
				"<div class='tip-middle'><p>'C 0</p></div>" + 
				"<img class='tip-right' src='"+tooltipButtImg+"'></img>" + 
			"</div>" + 
		"</div>"
	);
	
	//preload and add the background images
	$('<img/>').attr('src', tooltipFGImg).load(function(){
		$(this).remove();
		$("#therm-body-fore").css("background-image", "url('"+tooltipFGImg+"')");
		checkIfAllImagesLoaded();
	});
	
	$('<img/>').attr('src', tooltipMiddleImg).load(function(){
		$(this).remove();
		$("#therm-tooltip .tip-middle").css("background-image", "url('" + tooltipMiddleImg + "')");
		checkIfAllImagesLoaded();
	});
	
	//adjust the css
	heightOfBody = tickMarkSegementCount * tickHeight;
	$("#therm-graphics").css("left", widthOfNumbers)
	$("#therm-body-bg").css("height", heightOfBody);
	$("#"+divName).css("height",  heightOfBody + thermTopHeight + thermBottomHeight);
	$("#therm-body-fore").css("height", heightOfBody);
	$("#therm-bottom").css("top", heightOfBody + thermTopHeight);
	mercuryId = $("#therm-body-mercury");
	mercuryId.css("top", heightOfBody + thermTopHeight);
	tooltipId = $("#therm-tooltip");
	tooltipId.css("top", heightOfBody + thermTopHeight - tooltipOffset);
	
	//add the numbers to the left
	var numbersDiv = $("#therm-numbers");
	var countPerTick = goalAmount/tickMarkSegementCount;
	var commaSepCountPerTick = commaSeparateNumber(countPerTick);
	
	//add the number
	for ( var i = 0; i < tickMarkSegementCount+1; i++ ) {
		
		var yPos = tickHeight * i + numberStartY;
		var style = $("<style>.pos" + i + " { top: " + yPos + "px; width:"+widthOfNumbers+"px }</style>");
		$("html > head").append(style);
		var dollarText = commaSeparateNumber(startNumber - countPerTick * i);
		$( numbersDiv ).append( "<div class='therm-number pos" + i + "'>" +dollarText+ "</div>" );
		
	}
	
	//check that the images are loaded before anything
	arrayOfImages = new Array( "#therm-top", "#therm-body-bg", "#therm-body-mercury", "#therm-bottom", ".tip-left", ".tip-right");
	
			
//	window.setInterval(preload(arrayOfImages),200);
	preload(arrayOfImages);
};

//check if each image is preloaded
function preload(arrayOfImages) {
	
	for(i=0;i<arrayOfImages.length;i++){
		$(arrayOfImages[i]).load(function() {   checkIfAllImagesLoaded();  });
	}
    
}

//check that all the images are preloaded
function checkIfAllImagesLoaded(){
	imgsLoaded++;
	if(imgsLoaded == arrayOfImages.length){
		
		setInterval(function(){$("#"+divName).fadeTo(500, 1, function(){
			
			animateThermometer(ds[j++%dataLen]);
			
		});},2000);
		
	}
}


//animate the thermometer
function animateThermometer(data){
	var percentageComplete =1- ((startNumber-data)/goalAmount); // Math.abs(data)/goalAmount;
	var mercuryHeight = Math.round(heightOfBody * percentageComplete); 
	//alert('animateThermometer'+Math.round(heightOfBody * percentageComplete));
	var newMercuryTop = heightOfBody + thermTopHeight - mercuryHeight;
	
	mercuryId.animate({height:mercuryHeight +1, top:newMercuryTop }, animationTime);
	tooltipId.animate({top:newMercuryTop - tooltipOffset}, {duration:animationTime});
	//alert('currentAmount'+currentAmount);
	var tooltipTxt = $("#therm-tooltip .tip-middle p");
	
	//change the tooltip number as it moves
	$({tipAmount: 0}).animate({tipAmount: data}, {
		duration:animationTime,
		step:function(){
			tooltipTxt.html(data);
		}
	});
	
	
}

//format the numbers with $ and commas
function commaSeparateNumber(val){
	val = Math.round(val);
    while (/(\d+)(\d{3})/.test(val.toString())){
      val = val.toString().replace(/(\d+)(\d{3})/, "'C1"+","+"'C2");
    }
    return numberPrefix + val + numberSuffix;
}
