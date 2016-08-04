
$(document).ready(function () {
    $('#login').show().animate({opacity: 1}, 2000);
    $('.logo').show().animate({opacity: 1, top: '40%'}, 800, function () {
        $('.logo').show().delay(1200).animate({opacity: 1, top: '1%'}, 300, function () {
            $('.formLogin').animate({opacity: 1, left: '0'}, 300);
            $('.userbox').animate({opacity: 0}, 200).hide();
        });
    });
    $(".on_off_checkbox").iphoneStyle();
    $('.tip a ').tipsy({gravity: 'sw'});
    $('.tip input').tipsy({trigger: 'focus', gravity: 'w'});
});
$('#alertMessage').click(function () {
    hideTop();
});
function showError(str, delay) {
    if (delay) {
        $('#alertMessage').removeClass('success info warning').addClass('error').html(str).stop(true, true).show().animate({opacity: 1, right: '10'}, 500, function () {
            $(this).delay(delay).animate({opacity: 0, right: '-20'}, 500, function () {
                $(this).hide();
            });
        });
        return false;
    }
    $('#alertMessage').addClass('error').html(str).stop(true, true).show().animate({opacity: 1, right: '10'}, 500);
}
function showSuccess(str, delay) {
    if (delay) {
        $('#alertMessage').removeClass('error info warning').addClass('success').html(str).stop(true, true).show().animate({opacity: 1, right: '10'}, 500, function () {
            $(this).delay(delay).animate({opacity: 0, right: '-20'}, 500, function () {
                $(this).hide();
            });
        });
        return false;
    }
    $('#alertMessage').addClass('success').html(str).stop(true, true).show().animate({opacity: 1, right: '10'}, 500);
}
function hideTop() {
    $('#alertMessage').animate({opacity: 0, right: '-20'}, 500, function () {
        $(this).hide();
    });
}
function loading(name, overlay) {
    $('body').append('<div id="overlay"></div><div id="preloader">' + name + '..</div>');
    if (overlay === 1) {
        $('#overlay').css('opacity', 0.1).fadeIn(function () {
            $('#preloader').fadeIn();
        });
        return  false;
    }
    $('#preloader').fadeIn();
}
function unloading() {
    $('#preloader').fadeOut('fast', function () {
        $('#overlay').fadeOut();
    });
}


var checkLogin = function () {
    var user = new Object();
    user.username = $("#username").val();
    user.password = $("#password").val();
    $.ajax({
        url: "checkLogin",
        type: "POST",
        data: {json: JSON.stringify(user)},
        success: function (response) {
            var result = $.trim(response);
            if (result === "true") {
                $("#login").animate({opacity: 1, top: '49%'}, 200, function () {
                    $('.userbox').show().animate({opacity: 1}, 500);
                    $("#login").animate({opacity: 0, top: '60%'}, 500, function () {
                        $(this).fadeOut(100, function () {
                            $(".text_success").slideDown();
                            $("#successLogin").animate({opacity: 1, height: "200px"}, 500);
                        });
                    });
                });
                var urlRedirect = getParameter('url');
                if(urlRedirect === '')
                    urlRedirect = 'index.htm';
                setTimeout("window.location.href='" + urlRedirect + "'", 3000);
            } else {
                showErrorUsernamePassword();
            };
        }
    });
};

var getParameter = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^#]*)').exec(window.location.href);
    if (results === null) {
        return '';
    } else {
        return results[1] || 0;
    }
};

