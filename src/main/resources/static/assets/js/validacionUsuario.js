const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const selects = document.querySelectorAll('#formulario select');
const cancelButton = document.querySelectorAll('.cancel')
	 

const expresiones = {
	nombresUsuario: /^[A-Za-zÀ-ÿ]{1,10}[\s]{0,1}[A-Za-zÀ-ÿ]{1,10}$/, 
    apellidosUsuario: /^[A-Za-zÀ-ÿ]{1,10}[\s]{0,1}[A-Za-zÀ-ÿ]{1,10}$/,
	correoUsuario:  /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/ ,
}
		
const campos = {
	nombresUsuario: false,
	apellidosUsuario: false,
	correoUsuario: false,
	tipoUsuario: false,
}
		
const validarFormulario = (e) => {
	switch (e.target.name) {
		case "nombresUsuario":
			validarCampo(expresiones.nombresUsuario, e.target, 'nombresUsuario');
		break;
		case "apellidosUsuario":
			validarCampo(expresiones.apellidosUsuario, e.target, 'apellidosUsuario');
		break;
		case "correoUsuario":
			validarCampo(expresiones.correoUsuario, e.target, 'correoUsuario');
		break;
		case "tipoUsuario":
			validarSelect(e.target, 'tipoUsuario');
		break;
	}
}
		
const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}

const validarSelect = (select, campo) => {
	if (select.options[select.selectedIndex].textContent=='Seleccionar') {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}else{
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	}
}
		
inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});

selects.forEach((select) => {
	select.addEventListener('blur',validarFormulario)
	select.addEventListener('change',validarFormulario)
})

formulario.addEventListener('submit',(e)=>{
	e.preventDefault()

	if(campos.nombresUsuario && campos.apellidosUsuario && campos.correoUsuario && campos.tipoUsuario){
		formulario.submit()
	}else{
		inputs.forEach((input) => {
			input.addEventListener('click', validarFormulario);
			input.click()
			input.removeEventListener('click', validarFormulario)
		});

		selects.forEach((select) => {
			select.addEventListener('click', validarFormulario)
			select.click()
			select.removeEventListener('click', validarFormulario)
		});
	}
})

cancelButton.forEach((button) => {
    button.addEventListener('click',()=>{
        formulario.reset()
        document.querySelectorAll('.formulario__grupo-correcto').forEach((icono) => {
            icono.classList.remove('formulario__grupo-correcto');
        });
        document.querySelectorAll('.formulario__grupo-incorrecto').forEach((icono) => {
            icono.classList.remove('formulario__grupo-incorrecto');
        });
        document.querySelectorAll('.formulario__input-error').forEach((icono) => {
            icono.classList.remove('formulario__input-error-activo');
        });
    })
});
