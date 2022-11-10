function toggle(source) {
    checkboxes = document.getElementsByName('col');

    for(var i=0, n=checkboxes.length;i<n;i++) {
        checkboxes[i].checked = source.checked;
        checkboxes[i].click()
    }
}	

checkboxes = document.getElementsByName('col');
checkboxes.forEach((check)=>{
    check.click()
})	

botonToggle = document.querySelectorAll('.MBtn')
botonToggle.forEach(boton => {
    boton.addEventListener('click', columnaToggle)
    boton.click()
})	

function columnaToggle(e) {
    if (e.target.checked == false) {
        document.querySelectorAll(`.${e.target.id}`).forEach((element)=>{
            element.setAttribute('hidden','')
        })
    }else{
        document.querySelectorAll(`.${e.target.id}`).forEach((element)=>{
            element.removeAttribute('hidden','')
        })
    }
}