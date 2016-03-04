/**
 * Created by Zheny Chaichits on 21.02.2016.
 */

$(document).ready(function () {

    $('body').bind('mousewheel', function (e) {
        e.preventDefault();
        e.stopPropagation();
    });
});

$('.scroll').onepage_scroll({
    sectionContainer: "section",
    easing: "ease",                  // "ease", "linear", "ease-in"
    animationTime: 800,
    pagination: false,
    updateURL: false,
    beforeMove: function (index) {
    },
    afterMove: function (index) {
    },
    loop: false,
    keyboard: false,
    responsiveFallback: 1000,
    direction: "vertical"
});

$(document).keydown(function (objEvent) {
    if (objEvent.keyCode == 9) {
        objEvent.preventDefault();
    }
});

if (matchMedia) {
    var mq = window.matchMedia("(min-width: 1000px)");
    mq.addListener(switchScrollability);
    switchScrollability(mq);
}

function switchScrollability(mq) {
    $('.scroll').moveTo(1);
    if (mq.matches) {
        $('body').bind('mousewheel', function (e) {
            e.preventDefault();
            e.stopPropagation();
        });
    } else {
        $('body').unbind('mousewheel');
    }
}
