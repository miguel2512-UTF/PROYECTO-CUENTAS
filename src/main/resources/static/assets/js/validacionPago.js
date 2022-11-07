//Validacion para REGISTRAR
const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const selects = document.querySelectorAll('#formulario select');
const cancelButton = document.querySelectorAll('#modal-add .cancel')
  
const expresiones = {
	fechaPago: /^(\d{4})(\/|-)(0[1-9]|1[0-2])\2([0-2][0-9]|3[0-1])$/,
	totalPago: /^\d{1,20}$/,
	
	
} 

const campos = {
	prestamo: false,
	fechaPago: false,
	totalPago: false,
}

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "prestamo":
			validarSelect(e.target, 'prestamo');
		break;
		case "fechaPago":
			validarCampo(expresiones.fechaPago, e.target, 'fechaPago');
		break;
		case "totalPago": 
			validarCampo(expresiones.totalPago, e.target, 'totalPago')
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

	if(campos.fechaPago && campos.totalPago && campos.prestamo ){
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

	if(campos.contrasenaUsuario && campos.correoUsuario && campos.tipoUsuario && campos.estadoUsuario){
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