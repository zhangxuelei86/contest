    /*ajax测试*/
    $.ajax({
	url: "/listAllInfo",
	type: "GET",
	dataType: "json",
	success: function (json) {
	    var list="";
		$.each(json,function(i,info){
		if(info.infoType=="文化"){
		var img=info.imgList;
		var a;
		for(i=0;i<img.length;i++){
		if(img[i].serialNum==0){
		a=img[i];
		}
		}
		list+="<div style='display:inline-block;float:left;padding:5px;'><table border='solid'><tr><td align=center><img style='max-width:300px;max-height:300px' src='.."+a.imgPath+"' alt='图片'></td></tr>"+"<tr><td align='center'>"+"<a href='./content.html?Id="+info.infoId+"'>"+info.infoTitle+"</a></td></tr></table></div>";
		}
		})
		$("#content").append(list);
		$("#loading").toggle();
	}
    });