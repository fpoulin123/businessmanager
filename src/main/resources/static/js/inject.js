function injectNavbar(){
	$.get("./navbar", function (data, status) {
		$("#navbar").html(data);
	});
}

function setActiveLink(link){
		var linkId = "#" + link;
		
		$(".active").each(function(){
			$(this).removeClass("active");
			
		});
		
		$(linkId).addClass("active");
		
	}






 