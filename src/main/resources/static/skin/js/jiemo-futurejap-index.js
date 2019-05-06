$(function(){
	'use strict';
	//方案tab切换
	$(document).on('mouseover','.jiemo-futurejap-case .col-md-2',function(){
		var tval = $(this).attr('data-current');
		$(this).addClass('current').siblings().removeClass('current');
		$(this).parents('.jiemo-futurejap-case').find('.tab-content[data-tab='+tval+']').addClass('current').siblings().removeClass('current');
	});
	
	$(document).on('click','.jiemo-index-bottom-gg em',function(){
		$('.jiemo-index-bottom-gg').fadeOut();
	});
  var player = videojs('jiemo-futurejap_video_1',{
    muted: true,
    controls : true,
    loop : true,
	});
	if(typeof(Storage) !== "undefined") {
		if(!sessionStorage.alerted) {
			sessionStorage.alerted = true;
			setTimeout(function(){
				$('.utop_box').show()
			},3000)
		}
	} else {
		alert("Your browser does not support HTML5 storage");
	}
	
	$('.utop_box .close').click(function(){
		$('.utop_box').hide()
	})
	$('.info').click(function(){
		$('.utop_box').hide()
	})
});