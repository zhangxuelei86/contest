/*ajax获取信息*/
    $(function(){
    var str=window.location.search;
    var a = str.split("=");
	var id=a[1];
	$.ajax({
	url: "/getInfoById?id="+id,
	type: "get",
	dataType: "json",
	success: function (info) {
	    var list="";
	    list+="<h2 align='center'>"+info.infoTitle+"</h2><br><div style='position: absolute;text-align:left;width: 70%;height: auto;padding: 5px;left: 15%;'>"
		var img=info.imgList;
		var para=info.infoParaList;
		for(i=0;i<para.length;i++){
		list+="<span style='width:100%;height:auto;'>&emsp;&emsp;"+para[i].infoPara+"</span><br>";
		for(j=0;j<img.length;j++){
		if(img[j].serialNum==para[i].infoParaId){
		list+="<span style='text-align: center;display:block;'><img style='max-width:400px;max-height:400px' src='.."+img[j].imgPath+"'alt='图片'></span><br>";
		}
		}
		}
		list+="</div>"
		$("#content").append(list);
		$("#loading").toggle();
	}
	});
    })