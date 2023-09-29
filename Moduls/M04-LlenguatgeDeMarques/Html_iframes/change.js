var inc = document.getElementById('inicio');
var gry = document.getElementById('galery');
var enl = document.getElementById('enlla');
var nti = document.getElementById('notici');

function iniciv() {
    
    enl.style.display = 'none';
    nti.style.display = 'none';
    gry.style.display = 'none';
    inc.style.display = 'block';
}

function galeryv() {
    inc.style.display = 'none';
    enl.style.display = 'none';
    nti.style.display = 'none';
    gry.style.display = 'block';
}

function enllv() {
    nti.style.display = 'none';
    gry.style.display = 'none';
    inc.style.display = 'none';
    enl.style.display = 'block';
}

function noticiv() {
    gry.style.display = 'none';
    inc.style.display = 'none';
    enl.style.display = 'none';
    nti.style.display = 'block';
    
}