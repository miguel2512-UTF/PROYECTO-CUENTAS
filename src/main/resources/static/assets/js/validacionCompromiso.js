// VALIDACIÓN REGISTRAR

const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const selects = document.querySelectorAll('#formulario select');
const cancelButton = document.querySelectorAll('#modal-add .cancel')
	 

const expresiones = {
	numeroFac:  /^[0-9]{5,10}$/, 
    nombreEm: /^[A-Za-zÀ-ÿ]{1,10}[\s]{0,1}[A-Za-zÀ-ÿ]{1,10}$/,
    total: /^[0-9]{3,10}$/,
}
		
const campos = {
	numeroFac: false,
	nombreEm: false,
	fecha: false,
    fechaS: false,
    metodo:false,
    total: false,
    usuario: false,
	tipocompromiso: false,
	estadoCom: false,
}
		
const validarFormulario = (e) => {
	switch (e.target.name) {
		case "numeroFac":
			validarCampo(expresiones.numeroFac, e.target, 'numeroFac');
		break;
		case "nombreEm":
			validarCampo(expresiones.nombreEm, e.target, 'nombreEm');
		break;
		case "fecha":
			validarCampo(expresiones.fecha, e.target, 'fecha');
		break;
		case "fechaS":
			validarCampo(expresiones.fecha, e.target, 'fechaS');
		break;
        case "metodo":
			validarSelect(e.target, 'metodo');
		break;
        case "total":
			validarCampo(expresiones.total, e.target, 'total');
		break;
        case "usuario":
			validarSelect(e.target, 'usuario');
		break;
        case "tipocompromiso":
			validarSelect(e.target, 'tipocompromiso');
		break;
        case "estadoCom":
			validarSelect(e.target, 'estadoCom');
		break;
	}
}
		
const validarCampo = (expresion, input, campo) => {
	let form=input.parentElement.parentElement.parentElement.parentElement
	if(expresion.test(input.value)){
		if (form.id=='formulario') {
			document.querySelector(`#formulario #grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
			document.querySelector(`#formulario #grupo__${campo}`).classList.add('formulario__grupo-correcto');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.add('fa-check-circle');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.remove('fa-times-circle');
			document.querySelector(`#formulario #grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
			campos[campo] = true;
		}else if (form.id=='formularioEdit') {
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.add('formulario__grupo-correcto');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.add('fa-check-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.remove('fa-times-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
			campos[campo] = true;
		}
	} else {
		if (form.id=='formulario') {
			document.querySelector(`#formulario #grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
			document.querySelector(`#formulario #grupo__${campo}`).classList.remove('formulario__grupo-correcto');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.add('fa-times-circle');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.remove('fa-check-circle');
			document.querySelector(`#formulario #grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
			campos[campo] = false;
		}else if (form.id=='formularioEdit') {
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.remove('formulario__grupo-correcto');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.add('fa-times-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.remove('fa-check-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
			campos[campo] = false;
		}
	}
}

const validarSelect = (select, campo) => {
	let form=select.parentElement.parentElement.parentElement.parentElement
	if (select.options[select.selectedIndex].textContent=='Seleccionar') {
		if (form.id=='formulario') {
			document.querySelector(`#formulario #grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
			document.querySelector(`#formulario #grupo__${campo}`).classList.remove('formulario__grupo-correcto');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.add('fa-times-circle');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.remove('fa-check-circle');
			document.querySelector(`#formulario #grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
			campos[campo] = false;	
		}else if (form.id=='formularioEdit') {
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.remove('formulario__grupo-correcto');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.add('fa-times-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.remove('fa-check-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
			campos[campo] = false;
		}
	}else{
		if (form.id=='formulario') {
			document.querySelector(`#formulario #grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
			document.querySelector(`#formulario #grupo__${campo}`).classList.add('formulario__grupo-correcto');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.add('fa-check-circle');
			document.querySelector(`#formulario #grupo__${campo} i`).classList.remove('fa-times-circle');
			document.querySelector(`#formulario #grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
			campos[campo] = true;
		}else if (form.id=='formularioEdit') {
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
			document.querySelector(`#formularioEdit #grupo__${campo}`).classList.add('formulario__grupo-correcto');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.add('fa-check-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} i`).classList.remove('fa-times-circle');
			document.querySelector(`#formularioEdit #grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
			campos[campo] = true;
		}
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

	if(campos.numeroFac && campos.nombreEm && campos.metodo && campos.total && campos.usuario && campos.tipocompromiso && campos.estadoCom){
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