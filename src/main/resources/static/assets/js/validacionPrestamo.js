//Validación para REGISTRAR
const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const selects = document.querySelectorAll('#formulario select');
const cancelButton = document.querySelectorAll('#modal-add .cancel')

const expresiones = {
	documentoPrestamista: /^\d{8,10}$/, 
	nombrePrestamista: /^[a-zA-ZÀ-ÿ\s]{4,25}$/, 
	fechaPagoOportuno: /^(\d{4})(\/|-)(0[1-9]|1[0-2])\2([0-2][0-9]|3[0-1])$/, 
	valorPrestamo: /^\d{5,20}$/,
	tasaPrestamo: /([0-9\s]{1,4})$/, //tasaPrestamo de prestamo
	numeroCuotas: /^\d{1,2}$/, //Cantidad de Numero Cuotas
} 

const campos = {
	documentoPrestamista: false,
	nombrePrestamista: false,
	fechaPagoOportuno: false,
	valorPrestamo: false, 
	tasaPrestamo: false,
	numeroCuotas: false,
	estadoPrestamo: false,
	periodoCuota: false,
}

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "documentoPrestamista":
			validarCampo(expresiones.documentoPrestamista, e.target, 'documentoPrestamista');
		break;
		case "nombrePrestamista":
			validarCampo(expresiones.nombrePrestamista, e.target, 'nombrePrestamista');
		break;
		case "fechaPagoOportuno":
			validarCampo(expresiones.fechaPagoOportuno, e.target, 'fechaPagoOportuno');
		break;
		case "valorPrestamo":
			validarCampo(expresiones.valorPrestamo, e.target, 'valorPrestamo')
		break;
		case "tasaPrestamo": 
			validarCampo(expresiones.tasaPrestamo , e.target, 'tasaPrestamo')
		break;
		case "numeroCuotas":
			validarCampo(expresiones.numeroCuotas, e.target, 'numeroCuotas')
		break;
		case "estadoPrestamo":
			validarSelect(e.target, 'estadoPrestamo')
		break;
		case "periodoCuota":
			validarSelect(e.target, 'periodoCuota')
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

	if(campos.documentoPrestamista && campos.nombrePrestamista && campos.fechaPagoOportuno && campos.valorPrestamo && campos.tasaPrestamo && campos.periodoCuota && campos.numeroCuotas ){
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


//Validacion para EDITAR

const formularioEditar = document.getElementById('formularioEdit');
const inputsEdit = document.querySelectorAll('#formularioEdit input');
const selectsEdit = document.querySelectorAll('#formularioEdit select');
const cancelButtonEdit = document.querySelectorAll('#modal-edit .cancel')
const editButton = document.getElementById('Editar')

inputsEdit.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});

selectsEdit.forEach((select) => {
	select.addEventListener('blur',validarFormulario)
	select.addEventListener('change',validarFormulario)
})

formularioEditar.addEventListener('submit',(e)=>{
	e.preventDefault()

	if(campos.documentoPrestamista && campos.nombrePrestamista && campos.fechaPagoOportuno && campos.valorPrestamo && campos.tasaPrestamo && campos.periodoCuota && campos.numeroCuotas && estadoPrestamo){
		formularioEditar.submit()
	}else{
		inputsEdit.forEach((input) => {
			input.addEventListener('click', validarFormulario);
			input.click()
			input.removeEventListener('click', validarFormulario)
		});

		selectsEdit.forEach((select) => {
			select.addEventListener('click', validarFormulario)
			select.click()
			select.removeEventListener('click', validarFormulario)
		});
	}
})

cancelButtonEdit.forEach((button) => {
    button.addEventListener('click',()=>{
        formularioEditar.reset()
        document.querySelectorAll('#formularioEdit .formulario__grupo-correcto').forEach((icono) => {
            icono.classList.remove('formulario__grupo-correcto');
        });
        document.querySelectorAll('#formularioEdit .formulario__grupo-incorrecto').forEach((icono) => {
            icono.classList.remove('formulario__grupo-incorrecto');
        });
        document.querySelectorAll('#formularioEdit .formulario__input-error').forEach((icono) => {
            icono.classList.remove('formulario__input-error-activo');
        });
    })
});

editButton.addEventListener('click',()=>{
	inputsEdit.forEach((input) => {
		input.addEventListener('click', validarFormulario);
		input.click()
		input.removeEventListener('click', validarFormulario)
	});

	selectsEdit.forEach((select) => {
		select.addEventListener('click', validarFormulario)
		select.click()
		select.removeEventListener('click', validarFormulario)
	});
})