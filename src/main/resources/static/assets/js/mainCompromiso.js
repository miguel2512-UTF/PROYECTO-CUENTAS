$(document).ready(function(){

    $('.nBtn, .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).attr('id');
        //for update user
        if (text == 'editar') {
            $.get(href, function (compromiso, status) {
                $('#modal-edit #idCom').val(compromiso.idCom);
                $('#modal-edit #numeroFac').val(compromiso.numeroFac);
                $('#modal-edit #nombreEm').val(compromiso.nombreEm);
                $('#modal-edit #fecha').val(compromiso.fecha);
                $('#modal-edit #fechaS').val(compromiso.fechaS);
                $('#modal-edit #metodo').val(compromiso.metodo);
                $('#modal-edit #metodo').attr('selected','');
                $('#modal-edit #total').val(compromiso.total);
               
                
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