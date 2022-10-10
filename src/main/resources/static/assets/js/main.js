$(document).ready(function(){

    $('.nBtn, .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).attr('id');
        //for update user
        if (text == 'editar') {
            $.get(href, function (user, status) {
                $('#modal-edit #idUsuario').val(user.idUsuario);
                $('#modal-edit #nombresUsuario').val(user.nombresUsuario);
                $('#modal-edit #apellidosUsuario').val(user.apellidosUsuario);
                $('#modal-edit #nombrecompleto').val(`${user.nombresUsuario} ${user.apellidosUsuario}`);
                $('#modal-edit #contrasenaUsuario').val(user.contrasenaUsuario);
                $('#modal-edit #correoUsuario').val(user.correoUsuario);
                $('#modal-edit #tipo').text(user.tipoUsuario);
                $('#modal-edit #tipo').val(user.tipoUsuario);
                $('#modal-edit #estadoU').text(user.estadoUsuario);
                $('#modal-edit #estadoU').val(user.estadoUsuario);
            });
            $("#modal-edit").modal();
        } else {
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
        }
    });
    //for delete user
    $('.table .delBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#removeModalCenter #delRef').attr('href', href);
        $('#removeModalCenter').modal();
    });
});