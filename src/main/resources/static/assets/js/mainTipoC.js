$(document).ready(function(){

    $('.nBtn, .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).attr('id');
        //for update user
        if (text == 'editar') {
            $.get(href, function (tipocompromiso, status) {
                $('#modal-edit #id').val(tipocompromiso.id);
                $('#modal-edit #nombre').val(tipocompromiso.nombre);
                $('#modal-edit #descripcion').val(tipocompromiso.descripcion);
                $('#modal-edit #estado').val(tipocompromiso.estado);
                $('#modal-edit #estado').text(tipocompromiso.estado);
                $('#modal-edit #estado').attr('selected','');
            });
            $("#modal-edit").modal();
        } /*else {
            //for creating user
            $('#modal-add #idUsuario').val('');
            $('#modal-add #nombresUsuario').val('');
            $('#modal-add #apellidosUsuario').val('');
            $('#modal-add #contrasenaUsuario').val('');
            $('#modal-add #correoUsuario').val('');
            $('#modal-add #tipo').text('Seleccionar');
            $('#modal-add #tipo').attr('selected','');
            $('#modal-add #tipo').val('');
            $('#modal-add #estadoU').text('');
            $('#modal-add #estadoU').val('');
            $('#modal-add').modal();
        }*/
    });
    //for delete user
    $('.table .delBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#removeModalCenter #delRef').attr('href', href);
        $('#removeModalCenter').modal();
    });
});