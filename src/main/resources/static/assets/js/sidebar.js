let btn = document.querySelector("#btn");
let sidebar = document.querySelector(".sidebar");
let searchBtn = document.querySelector(".bx-search");
let logo_contenido = document.querySelector(".logo_contenido");
let perfil_contenido = document.querySelector(".perfil");

btn.onclick = function () {
    sidebar.classList.toggle("active");
}
searchBtn.onclick = function () {
    sidebar.classList.toggle("active");
}