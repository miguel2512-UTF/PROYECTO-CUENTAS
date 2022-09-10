const openModal = document.querySelector('.terminos');
const modal = document.querySelector('.ventana');
const closeModal = document.querySelector('.cerrar');

openModal.addEventListener('click', (e)=>{
    e.preventDefault();
    modal.classList.add('uwu');
});

closeModal.addEventListener('click', (e)=>{
    e.preventDefault();
    modal.classList.remove('uwu');
});