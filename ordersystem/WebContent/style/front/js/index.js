/* 
* @Author: Marte
* @Date:   2016-03-17 09:25:20
* @Last Modified by:   Marte
* @Last Modified time: 2016-03-17 15:17:57
*/

$(function(e) {
    // =======banner============
     var num = 0;
     var timer = null;
     $('ol li').click(function(e) {
        /*$(this).addClass('current').siblings().removeClass('current')*/
        
        /*$('.banner ul li a:first img').attr('src','img/'+$(this).index()+'.jpg').hide().fadeIn(600)*/
    });
     
     
     function autoplay(){
        num++;
        if(num>4){
            num=0;  
        }
        /*$('.banner ul li a:first img').attr('src','img/'+num+'.jpg').hide().fadeIn(600);
        $('ol li').eq(num).addClass('current').siblings().removeClass('current')*/ 
    }
     
     timer = setInterval(autoplay,2000);
    $('.leftBtn').click(function(e) {
        num--;
        if(num<0){
            num=4;  
        }
        $('.banner ul li a:first img').attr('src','img/'+num+'.jpg').hide().fadeIn(300);
        $('ol li').eq(num).addClass('current').siblings().removeClass('current');
    }); 
    $('.rightBtn').click(function(e) {
        autoplay();
    });
    $('.banner').hover(function(){
        clearInterval(timer)
        $('.banner span').show()
    },function(){
        timer = setInterval(autoplay,2000)
        $('.banner span').hide()
    })
    // =============nav============
    $('.nav_l li').mouseenter(function(event) {
         $(this).css('background','#ccf')
         $(this).children('div').show();
    }).mouseleave(function(event) {
         $(this).css('background','none');
         $(this).children('div').hide();
    });
    $('.hidee>div').mouseenter(function(event) {
        $(this).css('background','#ccf')
    }).mouseleave(function(event) {
        $(this).css('background','#ffc')
    });
    // =========分页==============
    $('.paging a').click(function(event) {
            $(this).css('background','#ccf').siblings().css('background','#fcf')
    });
    // =======二维码生成========
    $('.btn span').click(function(event) {
        $('.nav_picl img').attr('src','img/erweima.jpg')
    });
    
});