/**
 * Created by Zheny Chaichits on 28.03.2016.
 */
var circle = document.querySelector('.material-btn');
var link = document.querySelector('.material-content').querySelectorAll('li');
var ham = document.querySelector('.material-hamburger');
var menu = document.querySelector('.material-menu-wrapper');
var main = document.querySelector('main');
var html = document.querySelector('html');
var win = window;

menu.style.zIndex= 1;

var opened = false;
function openMenu(event) {
    opened = true;
    circle.classList.toggle('active');
    ham.classList.toggle('material-close');
    main.classList.toggle('active');
    menu.classList.toggle('active');
    menu.style.zIndex= 2;
    for (var i = 0; i < link.length; i++) {
        link[i].classList.toggle('active');
    }
    event.preventDefault();
    event.stopImmediatePropagation();
}


function closeMenu() {
    opened = false;
    if (circle.classList.contains('active')) {
        circle.classList.remove('active');
        for (var i = 0; i < link.length; i++) {
            link[i].classList.toggle('active');
        }
        ham.classList.remove('material-close');
        main.classList.remove('active');
        menu.classList.remove('active');
        menu.style.zIndex= 1;
    }
}

circle.addEventListener('click', openMenu, false);

win.addEventListener('click', closeMenu, false);
