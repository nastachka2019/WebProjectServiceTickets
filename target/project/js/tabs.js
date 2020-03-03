$(function(){
    $('li a').click(function () {

        $('li').removeClass('active');

        $(this).closest('li').addClass('active');
    });
});