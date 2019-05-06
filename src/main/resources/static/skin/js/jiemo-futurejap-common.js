$(function(){
	'use strict';
	//下拉框赋值
	$(document).on('click','.btn-group .dropdown-menu li',function(){
		var tval = $(this).find('a').text();
		$(this).parents('.btn-group').eq(0).find('button font').text(tval);
		$(this).parents('.btn-group').eq(0).addClass('active');
	});
	//导航监听滚动条改变样式
	$(document).on('scroll',function(){
		var dTop = $(document).scrollTop();
		var wh = $(window).height();
		if(dTop>=200){
            $('body').css('padding-top', '6.6rem');
            $('.jiemo-futurejap-header-1,.jiemo-futurejap-header-2').addClass('active');
			$('.header-nav-second').addClass('scroll');
			$('.jiemo-futurejap-header-2 .img-logo1').hide();
			$('.jiemo-futurejap-header-2 .img-logo2').show();
		}else{
            $('body').css('padding-top', '0');
            $('.jiemo-futurejap-header-1,.jiemo-futurejap-header-2').removeClass('active');
			$('.header-nav-second').removeClass('scroll');
			$('.jiemo-futurejap-header-2 .img-logo1').show();
			$('.jiemo-futurejap-header-2 .img-logo2').hide();
        }
		if (dTop>wh) {
			$('.common-right-aside a.jiemo-futurejap-defaultbt').addClass('active');
		}else{
			$('.common-right-aside a.jiemo-futurejap-defaultbt').removeClass('active');
		}
	});
	//底部地址切换
	$(document).on('mouseover','.jiemo-futurejap-footer .left ul li',function(){
		var tval = $(this).attr('data-add');
		$(this).addClass('active').siblings().removeClass('active');
		$(this).parents('.left').find('.address[data-address='+tval+']').addClass('active').siblings().removeClass('active');
	});
	//二级导航效果
	$(document).on('mouseover','.jiemo-futurejap-header .col-md-2 a',function(){
		var tval = $(this).parent().attr('data-nav');
		$(this).parents('.jiemo-futurejap-header').eq(0).find('a').removeClass('active');
		if(typeof($(this).parent().attr("data-nav"))!=="undefined"){
			$(this).addClass('active');
		}else{
			$(".header-nav-second").removeClass('active');
		}
		$(this).parents('.jiemo-futurejap-header').find('.header-nav-second[data-nav-second='+tval+']').addClass('active').siblings('.header-nav-second').removeClass('active');
	});
	$(document).on('mouseleave','.jiemo-futurejap-header .header-nav-second',function(){
		$(this).removeClass('active');
		$(this).parents('.jiemo-futurejap-header').eq(0).find('.jiemo-futurejap-header-2 a, .jiemo-futurejap-header-3 a').removeClass('active');
	});
	$(document).on('click',function(e){
		if($(e.target).parents(".header-nav-second").length === 0)
        {
            $(".header-nav-second").removeClass('active');
			$(".jiemo-futurejap-header .col-md-2 a").removeClass('active');
        }
	});

	//注册登录输入框获取焦点事件
	$(document).on('focus','.jiemo-futurejap-loginbg .content-area .right .line-area .input-area input',function(){
		$(this).parent().find('em').removeClass('active');
	});
	//记住密码toggle
	$(document).on('click','.jiemo-futurejap-loginbg .content-area .right .checkbox-area',function(){
		$(this).find('i').toggleClass('active');
	});
	//注册登录关闭
	$(document).on('click','.jiemo-futurejap-loginbg .content-area .right .close-window',function(){
	    var $area = $('.input-area');
	    $('input', $area).val('');
	    $('i, em', $area).removeClass('active');
		$('.jiemo-futurejap-loginbg').removeClass('active');
	});
	//登录注册滑动
	$(document).on('click','.jiemo-futurejap-loginbg .content-area .right .btn-tab',function(){
		var tval = $(this).attr('data-v');
		if(tval==2){
			$(this).parents('.right').eq(0).find('.login-tabarea').removeClass('active');
		}else if(tval==1){
			$(this).parents('.right').eq(0).find('.login-tabarea').addClass('active');
		}
	});
	//返回顶部点击
	$(document).on('click touchstart','.jiemo-futurejap-defaultbt',function(){
		$('html,body').animate({scrollTop:0},1000);
	});
});