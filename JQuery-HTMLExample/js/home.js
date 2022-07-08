$(document).ready(function () {
    

    $("H1").addClass('text-center');

    $("H2").addClass('text-center');


    $("#myBanner").removeClass('myBannerHeading');
    $("#myBanner").addClass('page-header');

    $("#yellowHeading").text("Yellow Team")

    $('#orangeTeamList').css({'background-color': 'orange' });
    $('#blueTeamList').css({'background-color': 'blue' });
    $('#redTeamList').css({'background-color': 'red' });
    $('#yellowTeamList').css({'background-color': 'yellow' });

    
    $('#yellowTeamList').append('Joseph Banks<br />');
    $('#yellowTeamList').append('Simon Jones<br />');

    $("#oops").hide();

    $('#footerPlaceholder').remove();

    $('#footer').append('<p>Jasen Ratnam --  jasenratnam@hotmail.com</p>');
    $("#footer").addClass('text-center');
    $("#footer").css("fontSize", "24px");
    $("#footer").css("font-family", "Courier");


});