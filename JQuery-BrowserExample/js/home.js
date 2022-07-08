$(document).ready(function () {
   
    
    $("#akronInfoDiv").hide();
    $("#minneapolisInfoDiv").hide();
    $("#louisvilleInfoDiv").hide();


    $("#akronButton").on("click", function () {

        $("#mainInfoDiv").hide();
        $("#akronInfoDiv").show();
        $("#minneapolisInfoDiv").hide();
        $("#louisvilleInfoDiv").hide();

        $("#akronWeather").hide();

        $("#akronWeatherButton").on("click", function () {
            $("#akronWeather").toggle();
        })

    })

    $("#minneapolisButton").on("click", function () {

        $("#mainInfoDiv").hide();
        $("#akronInfoDiv").hide();
        $("#minneapolisInfoDiv").show();
        $("#louisvilleInfoDiv").hide();

        $("#minneapolisWeather").hide();

        $("#minneapolisWeatherButton").on("click", function () {
            $("#minneapolisWeather").toggle();
        })

    })

    $("#louisvilleButton").on("click", function () {

        $("#mainInfoDiv").hide();
        $("#akronInfoDiv").hide();
        $("#minneapolisInfoDiv").hide();
        $("#louisvilleInfoDiv").show();

        $("#louisvilleWeather").hide();

        $("#louisvilleWeatherButton").on("click", function () {
            $("#louisvilleWeather").toggle();
        })

    })

    $("#mainButton").on("click", function () {

        $("#mainInfoDiv").show();
        $("#akronInfoDiv").hide();
        $("#minneapolisInfoDiv").hide();
        $("#louisvilleInfoDiv").hide();

    })




    
    $("tr").hover(
        // in callback
        function () {
            $(this).css("background-color", "WhiteSmoke");
        },
        // out callback
        function () {
            $(this).css("background-color", "");
        }
    );





});