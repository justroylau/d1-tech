/*'use strict';

*//**
 * HomeController
 */
var winHeight = $(window).innerHeight();
var HomeController = function($scope,$http,$location,$timeout,$cookies,$sce) {
	//console.log("home");
	$scope.htmlHeight = winHeight;
	//console.log("$scope.userid:"+$scope.userid);
	//分页两次请求
	$scope.reload = true;
	$scope.loginState = false;
	$scope.articleShow = false;
	$scope.leftShow = true;
	$scope.hello = "hello Wrold"; 
	$scope.itemMinus = "glyphicon-minus";
	$scope.itemPlus = "glyphicon-plus";
	$scope.articles = {
		title:'',
		classid:'',
		contents:'',
	}
	$scope.pageInfo = {
			pageNum:'1',
			pageSize:'10',
			classid:'',
			Keyword:'',
	};
	
	$scope.paginationConf = {
            currentPage: 1,
            totalItems: 10,
            itemsPerPage: 20,  //每页显示
            pagesLength: 15,
            perPageOptions: [10, 20, 30, 40, 50],
            onChange: function(){
            	//alert(1);
           		 if(!$scope.reload) {
                      return;
                  }
            	 $scope.pageInfo.pageSize = $scope.paginationConf.itemsPerPage
            	 $scope.pageInfo.pageNum = $scope.paginationConf.currentPage;
            	 $scope.ByLikeArticles();
            	 $scope.reload = false;
            	 setTimeout(function() {
                         $scope.reload = true;
                 }, 200);
            }
	 };
	$scope.init = function(){
		$scope.findSentence();
		$scope.findclassArray();
		$("#loadingModal").modal('show');
		$scope.isLogin();
		
		//$scope.getByArticles();
	}
	$scope.findSentence = function(){
		$http({
	        method: "GET",
	        url : "findSentence",
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  if (data.code == 0) {
	    		  $scope.sentenceInfo = data.data.sentences;
				}
	      }).error(function(data) {
	    	  		
	      });
		
	}
	/* 搜索回车功能 */
	$scope.searchInput = function(e){
	var keycode = window.event ? e.keyCode : e.which;//获取按键编码
        if (keycode == 13) {
        	$scope.uname = "";
			$scope.upwd = "";
			$scope.checkupwd = "";
            if($scope.searchContent == "login"){
              		if($scope.userid == ""){
              			$("#upwdId").css("border", "1px solid #ccc");
						$('#loginModal').modal('show');
					}else{
						$scope.showMsg("已经登入!");
					}
			}else if($scope.searchContent == "input"){
				if($scope.userid != ""){
					$scope.articles.title = ""
						
					$scope.articles.id = "";
	            	$scope.updateState = false;
	            	$('#articleModal').modal('show');
	            	$timeout(function(){
		            		ue = UE.getEditor('editor');
			            	ue.ready(function() {//编辑器初始化完成再赋值
			        			ue.setContent("");  //赋值给UEditor
			        		});
		    	     },200);
	            	
				}else{
					$scope.showMsg("请先登入!");
				}
				
			}else if($scope.searchContent == "logout"){
				$scope.logout();
			}else if($scope.searchContent == "password"){
				if($scope.userid != ""){
					$('#userModal').modal('show');
					$scope.userHandState = "修改密码"
				}else{
					$scope.showMsg("请先登入!");
				}
				
			}else if($scope.searchContent == "register"){
				if($scope.userid != ""){
					$('#userModal').modal('show');
					$scope.userHandState = "注册账号"
				}else{
					$scope.showMsg("请先登入!");
				}
				
			}else{
				$scope.findByLikeBtn();
			}
        }
	}
	
	//登陆回车
	$scope.loginKeyup = function(e){
		var keycode = window.event ? e.keyCode : e.which;//获取按键编码
        if (keycode == 13) {
        	$scope.loginBtn();
        }
		
	}
	
	//登出
	$scope.logout = function(){
		$http({
	        method: "GET",
	        url : "Logout",
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  if (data.code == 0) {
	    		  $scope.userid = "";
	    		  $scope.users ="";
	    		  $scope.showMsg("登出成功!");
	    		  $scope.pageInfo.Keyword = ""; 
	    		  $scope.pageInfo.classid = "";
	    		  $scope.ByLikeArticles();
				}
	      }).error(function(data) {
	    	  		
	      });
		
	}
	/* 是否登陆 */
	$scope.isLogin = function(){
		$http({
	        method: "GET",
	        url : "isLogin",
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	     	  //console.log(data);
	    	  if (data.code == 0) {
	    		    $scope.users = data.data;
		    		$scope.userid =  data.data.id;
		    		$scope.alias = data.data.alias;
				}else{
					$scope.userid = "";
				}
	      }).error(function(data) {
	    	  		
	      });
	}
	
	//搜索功能
	$scope.findByLikeBtn = function(){
		$("#loadingModal").modal('show');
	 	//alert(2);
		$scope.articleShow = false;
		$scope.pageInfo.classid = "";
		$scope.pageInfo.Keyword = $scope.searchContent;
		$http({
	        method: "POST",
	        url : "findByLike",
	        data:$.param($scope.pageInfo),
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  if (data.code == 0) {
	    		  $scope.articleTitles = data.data.list;
		    	  //console.log($scope.articleTitles);
		    	  $scope.paginationConf.totalItems = data.data.total;
		    	  $('#loadingModal').modal('hide');
		    	  
				}
	      }).error(function(data) {
	    	  		
	      });
	}
	
	//获取分类列表
	$scope.findclassArray = function(){
		$http({
	        method: "GET",
	        url : "classArray",
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  //console.log(data);
	    	  if (data.code == 0) {
	    		  $scope.classArray = data.data;
	    		  //console.log( $scope.classArray);
	    		  $scope.superClasss = [parseInt($scope.classArray[$scope.classArray.length-1].id)];
	    		  for(var tempI in $scope.classArray){
	    			  if($scope.classArray[tempI].superid == '0'){
	    				  $scope.superClasss[parseInt($scope.classArray[tempI].id)-1] = $scope.classArray[tempI];
	    			  }
	    			  if($scope.classArray[tempI].superid != '0' && $scope.articles.classid ==""){
	    				  $scope.articles.classid = $scope.classArray[tempI].id+"";
	    				 // console.log($scope.articles.classid);
	    			  }
	    			  
	    		  }
				}
	      }).error(function(data) {
	    	  	
	      });
	}
	
	

	//登陆
	$scope.loginBtn = function(){
		$http({
	        method: "POST",
	        url : "login",
	        data:$.param({'uname':$scope.uname,'upwd':$scope.upwd}),
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  //console.log(data);
	    	  if (data.code == 0) {
	    		  $scope.users = data.data;
	    		  $scope.userid = data.data.id;
	    		  $scope.alias = data.data.alias;
	    		  $('#loginModal').modal('hide');
	    		  $scope.showMsg("登入成功!");
	    		  $scope.uname = "";
		  		  $scope.upwd = "";
		  		  $scope.checkupwd = "";
		  		  $scope.pageInfo.Keyword = ""; 
	    		  $scope.pageInfo.classid = "";
		  		  $scope.ByLikeArticles();
				}else{
					$scope.showMsg(data.msg);
				}
	      }).error(function(data) {
	    	  		
	      });
	}
	
	
	$scope.openBtn = function(classId){
		//console.log($scope.classArray);
		for(tempI in $scope.superClasss){
			if($scope.superClasss[tempI].id == classId){
				$scope.superClasss[tempI].touchState = !$scope.superClasss[tempI].touchState;
				break;
			}
		}
		$scope.checkopen = classId;
	}
	//点击类别
	$scope.checkClassBtn= function(classId){
		$("#loadingModal").modal('show');
		$scope.articleShow = false;
		//console.log(classId);
		$scope.pageInfo.Keyword = ""; 
		$scope.pageInfo.classid = classId;
		$scope.ByLikeArticles();
		
	}
	
	//查询文章列表
	$scope.ByLikeArticles= function(){
		$http({
	        method: "POST",
	        url : "findByLike",
	        data:$.param($scope.pageInfo),
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  if (data.code == 0) {
	    	  	  //console.log(data);
	    		  $scope.articleTitles = data.data.list;
		    	  //console.log($scope.articleTitles);
		    	  $scope.paginationConf.totalItems = data.data.total;
		    	  $('#loadingModal').modal('hide');
				}
	      }).error(function(data) {
	    	  		
	      });
	}
	
	//点击文章名称
	$scope.ArticleDetailIdBtn = function(id){
		$("#loadingModal").modal('show');
		$scope.ArticleId = id;
		$scope.addSee();
		$scope.getArticleDetail();
	}
	
	$scope.addSee = function(){
		$http({
	        method: "POST",
	        url : "addSee",
	        data:$.param({id:$scope.ArticleId}),
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  if (data.code == 0) {
	    		  //$scope.articleDetail = data.data;
		    	 // console.log($scope.articleDetail);
		    	 // $scope.articleShow = true;
		    	 // $('#loadingModal').modal('hide');
				}
	      }).error(function(data) {
	    	  		
	      });
	}
	
	//获取文章详情
	$scope.getArticleDetail = function(){
	$http({
	        method: "POST",
	        url : "findByArticleId",
	        data:$.param({id:$scope.ArticleId}),
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  //console.log(data);
	    	  if (data.code == 0) {
	    		  $scope.articleDetail = data.data;
	    		  $scope.articleDetail.contents = $sce.trustAsHtml($scope.articleDetail.contents);
		    	  //console.log($scope.articleDetail);
		    	  $scope.articleShow = true;
		    	  $('#loadingModal').modal('hide');
				}
	      }).error(function(data) {
	    	  		
	      });
	}
	
	/* 提交文章 */
	$scope.insertArticleBtn = function(){
		//console.log(UE.getEditor('editor').getContent());
		if(UE.getEditor('editor').getContent() == ''){
			$scope.articles.contents = "";
		}else{
			$scope.articles.contents = UE.getEditor('editor').getContent();
		}
		if($scope.articles.title == "" || $scope.articles.classid == "" || $scope.articles.contents == ""){
			$scope.showMsg("请注意必填项!");
		}else{
			 $http({
		        method: "POST",
		        url : "insertArticle",
		        data:$.param($scope.articles),
		        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
		      }).success(function(data) {
		    	  //console.log(data);
		    	  if (data.code == 0) {
		    	  		 $scope.showMsg("提交成功!");
		    		 	 $('#articleModal').modal('hide');
		    		 	 $scope.ByLikeArticles();
					}else{
						$scope.showMsg(data.msg);
					}
		      }).error(function(data) {
		    	  		
		      }); 
	      }
	}
	
	//修改文章按钮
	$scope.updateArticleBtn = function(id){
		$scope.updateState = true;
		$http({
	        method: "POST",
	        url : "findByArticleId",
	        data:$.param({id:id}),
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  if (data.code == 0) {
	    		  $scope.upArticleDetail = data.data;
	    		  $scope.articles.title = $scope.upArticleDetail.title;
	    		  $scope.articles.classid = $scope.upArticleDetail.classid.toString();
	    		  $scope.articles.id = $scope.upArticleDetail.id;
	    		  //console.log( $scope.upArticleDetail);
	    		  $('#articleModal').modal('show');
	    		  
	    		  $timeout(function(){
	    			  ue = UE.getEditor('editor');
		            	ue.ready(function() {//编辑器初始化完成再赋值
		            		ue.setContent($scope.upArticleDetail.contents);
		        		});
	    	     	 },200);
	    		  
				}
	      }).error(function(data) {
	    	  		
	      });
	}
	//修改文章
	$scope.updateArticle = function(){
		if(UE.getEditor('editor').getContent() == ''){
			$scope.articles.contents = "";
		}else{
			$scope.articles.contents = UE.getEditor('editor').getContent();
		}
		if($scope.articles.title == "" || $scope.articles.classid == "" || $scope.articles.contents == ""){
			$scope.showMsg("请注意必填项!");
		}else{
			$http({
		        method: "POST",
		        url : "updateArticle",
		        data:$.param($scope.articles),
		        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
		      }).success(function(data) {
		    	  //console.log(data);
		    	  if (data.code == 0) {
		    		  $scope.showMsg("修改成功!");
		    		  $('#articleModal').modal('hide');
		    		  $scope.ByLikeArticles();
		    		  $scope.updateState = false;
					}else{
						$scope.showMsg(data.msg);
					}
		      }).error(function(data) {
		    	  		
		      });
		}
		
	}
	//删除文章按钮
	$scope.deleteArticleBtn = function(id){
		$scope.ArticleId = id;
		$('#checkModal').modal('show');
		$scope.deleteState = true;
	}
	//删除文章
	$scope.deleteArticle = function(){
		$http({
        method: "POST",
        url : "deleteArticle",
        data:$.param({id:$scope.ArticleId}),
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
      }).success(function(data) {
    	  //console.log(data);
    	  if (data.code == 0) {
    		  $scope.showMsg("删除成功!");
    		  $scope.ByLikeArticles();
    		  $scope.deleteState = false;
    		  $scope.ArticleId = "";
    		  $('#checkModal').modal('hide');
			}
      }).error(function(data) {
    	  		
      });
	}
	//修改密码
	$scope.updateUserBtn = function(){
		if($scope.uname =="" && $scope.upwd == ""){
			 $scope.showMsg("请确认用户名或密码!");
		}else if($scope.upwd != $scope.checkupwd){
			$scope.showMsg("确认密码不一致!");
		}else{
			$http({
		        method: "POST",
		        url : "updatepwd",
		        data:$.param({'uname':$scope.uname,'upwd':$scope.upwd}),
		        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
		      }).success(function(data) {
		    	  //console.log(data);
		    	  if (data.code == 0) {
		    		  $scope.showMsg("修改成功!");
		    		  $('#userModal').modal('hide');
					}else{
						$scope.showMsg(data.msg);
					}
		      }).error(function(data) {
		    	  		
		      });
		}
	}
	//注册账号
	$scope.registerUserBtn = function(){
		if($scope.uname =="" && $scope.upwd == ""){
			 $scope.showMsg("请确认用户名或密码!");
		}else if($scope.upwd != $scope.checkupwd){
			$scope.showMsg("确认密码不一致!");
		}else{
			$http({
		        method: "POST",
		        url : "insertSelective",
		        data:$.param({'uname':$scope.uname,'upwd':$scope.upwd}),
		        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
		      }).success(function(data) {
		    	  //console.log(data);
		    	  if (data.code == 0) {
		    		  $scope.showMsg("注册成功!");
		    		  $('#userModal').modal('hide');
					}else{
						$scope.showMsg(data.msg);
					}
		      }).error(function(data) {
		    	  		
		      });
		}
	}
	//修改个人资料按钮
	$scope.setPersonBtn = function(){
		$scope.isLogin();
		$('#personModal').modal('show');
	}
	//修改个人资料
	$scope.setPerson = function(){
		$http({
	        method: "POST",
	        url : "updateUser",
	        data:$.param($scope.users),
	        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
	      }).success(function(data) {
	    	  if (data.code == 0) {
	    		  $scope.showMsg("修改成功!");
	    		  $('#personModal').modal('hide');
	    		  $scope.isLogin();
				}else{
					$scope.showMsg(data.msg);
				}
	      }).error(function(data) {
	      });
	}
	
	$scope.checkBtn = function(){
		if($scope.deleteState){
			$scope.deleteArticle();
		}
	}
	
	
	/* 修改字体 */
	$scope.setFontSize = function(){
		if(!$scope.setFontState){
			$("#bodyId").css("background-color","#1c1f2b");
			$(".setFontState").css("background-color","#1c1f2b");
			$("#bodyId").css("color","#ddd");
			$(".page-list input").css("color","#000");
			$(".page-list select").css("color","#000");
		}else{
			$("#bodyId").css("background-color","#ffffff");
			$(".setFontState").css("background-color","#ffffff");
			$("#bodyId").css("color","#000");
			$(".page-list input").css("color","#000");
			$(".page-list select").css("color","#000");
			
			
		}
		$scope.setFontState = !$scope.setFontState;
	}
	/* 隐藏左边块 */
	$scope.hideleftBtn = function(){
		//console.log(12);
		$scope.leftShow = !$scope.leftShow;
		if(!$scope.leftShow){
			$("#right-id").width("100%");
			$(".article-relevant").width("99.6%");
			
		}else{
			$("#right-id").width("85%");
			$(".article-relevant").width("84.6%");
		}
	}
		
	$scope.showMsg = function(Msg){
		$scope.massgeModel = Msg;
		$('#messageModal').modal('show');
		$timeout(function(){
          $('#messageModal').modal('hide')
     	 },1200);
		
	}
		
	
	$scope.init();
	
};
