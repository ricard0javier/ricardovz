/**
 * 
 */
"use strict";

$(function() {
	drawBingoBall('75');
});

function rgb2hex(rgb) {
	rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	return "#" + ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2)
			+ ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2)
			+ ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2);
}

function hexToRgb(hex) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16)
    } : null;
}

function canvas() {
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	ctx.fillStyle = "#FF0000";
	ctx.fillRect(0,0,150,75);	
}

function drawBingoBall(number) {
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
		
	ctx.beginPath();
	ctx.arc(65,40,30,0,2*Math.PI);
	ctx.stroke();
	
    var grd = ctx.createRadialGradient(50, 25, 3, 50, 25, 10);
    grd.addColorStop(0, '#8ED6FF');
    grd.addColorStop(1, '#004CB3');
	ctx.fillStyle = grd;
	ctx.fill();

	ctx.beginPath();
	ctx.arc(65,40,30,0,2*Math.PI);
	ctx.fillStyle = "green";
	ctx.stroke();
	
	ctx.font = "30px Arial";
	ctx.fillStyle = "black";
	ctx.fillText(number,50,50);
	
}
$("#favcolor").change(function(){
	var hex = $("#favcolor").val();
	var rgb = hexToRgb(hex).r + '-' + hexToRgb(hex).g + '-' + hexToRgb(hex).g;
	$("#colorHex").val(hex);
	$("#colorRGB").val(rgb);
});

$("#button1").click(function() {
	$("#demo").html("<h1>Hola Mundo</h1>");
});
