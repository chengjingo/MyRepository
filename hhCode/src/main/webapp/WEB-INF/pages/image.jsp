<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!-- 
  <head>
	<script>
    	var image = '';
    	function selectImage(file) {
	        if (!file.files || !file.files[0]) {
	            return;
	        }
	        var reader = new FileReader();
	        reader.onload = function (evt) {
				//将图片显示在id为imagedisplay的img
				document.getElementById('imagedisplay').src = evt.target.result;
				// 将图片的base64数据存在id为imagedata的一个文本框
				document.getElementById('imagedata').value = evt.target.result.toString();
	        }
	        reader.readAsDataURL(file.files[0]);
		}
</script>
  </head>
  
  <body>
	<div class="field">
    <input type="text" id="imagedata" name="imagedata" class="input tips" style="width:25%; float:left;" data-toggle="hover" data-place="right" readonly="readonly" />
    <input type="file" onchange="selectImage(this);" id="image" name="image" class="button bg-blue margin-left" value="+ 浏览上传" style="float:left;" >
    <img id="imagedisplay" src="" class="img-news" alt="图片尺寸：205*140"style="margin-left:50px;" />
	</div>
	
    <form>  
      <div class="filebox">  
        <input type="file" id="upload" name="bean.file" class="file" onchange="validFile();"/>  
      </div>  
      <label for="upload" id="uploadError" class="error"></lable>  
    </form>  
  </body>
	<head> 
	<title>图片上传预览</title> 
	<script> 
		function PreviewImage(imgFile){
			var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
			if(!pattern.test(imgFile.value)){ 
				alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
				imgFile.focus(); 
			}else{ 
				var path;
				//IE
				if(document.all){ 
					imgFile.select(); 
					path = document.selection.createRange().text; 
					document.getElementById("imgPreview").innerHTML=""; 
					document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
				}else{ //FF
					path = URL.createObjectURL(imgFile.files[0]);
					document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
				} 
			} 
		} 
	</script> 
	</head> 
	<body> 
		<center>
			<input type="file" onchange='PreviewImage(this)' /> 
			<div id="imgPreview" style='width:500px; height:400px;'> 
				<img src=""/> 
			</div> 
		</center>
	</body>
 -->
 	<head>
 		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.js"></script>
 		<script type="text/javascript" language="javascript">
 			function testUpload(){
 				alert("1");
		    	var form = new FormData(document.getElementById("test"));  
			    $.ajax({  
			        url : './upload/file.action',
			        data : form,  
			        type : 'post',  
			        processData:false,  
			        contentType:false,  
			        success : function(data){  
			            alert("成功")  
			        },  
			        error : function(data){  
			        }  
			    }); 
 			}
 		</script>
 	</head>
 	<body>
 	    <form id="test" method="post" enctype="multipart/form-data">    
        	选择文件:<input data-role="none" type="file" name="file" width="120px">    
        	<button data-role="none" onclick="testUpload();">测试</button>  
    	</form>  
 	</body>
</html>
