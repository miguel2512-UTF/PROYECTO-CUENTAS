const botonMenu = document.querySelector('.boton-menu');
const menu = document.querySelector('.menu-nav');

console.log(menu)
console.log(botonMenu)

botonMenu.addEventListener('click', ()=>{
    menu.classList.toggle("menu-spread")
})

window.addEventListener('click', e=>{
    if (menu.classList.contains('menu-spread') && e.target && e.target != menu && e.target != botonMenu) {
        menu.classList.toggle("menu-spread")
    }
})