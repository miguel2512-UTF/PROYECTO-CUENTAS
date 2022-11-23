$(document).ready(function(){

    $('.nBtn, .eBtn').on('click', function(event) {
        event.preventDefault();

        var href = $(this).attr('href');
        var text = $(this).attr('id');

        if (text=='editar') {
            $.get(href, function(user, status) {
                $('#modal-edit #idPrestamo').val(user.idPrestamo);
                $('#modal-edit #documentoPrestamista').val(user.documentoPrestamista);
                $('#modal-edit #nombrePrestamista').val(user.nombrePrestamista);
                $('#modal-edit #fechaPagoOportuno').val(user.fechaPagoOportuno);
                $('#modal-edit #valorPrestamo').val(user.valorPrestamo);
                $('#modal-edit #tasaPrestamo').val(user.tasaPrestamo);
                $('#modal-edit #periodo').text(user.periodoCuota)
                $('#modal-edit #periodo').val(user.periodoCuota);
                $('#modal-edit #periodo').attr('selected','')
                $('#modal-edit #numeroCuotas').val(user.numeroCuotas);
                $('#modal-edit #estadoP').text(user.estadoPrestamo)
                $('#modal-edit #estadoP').val(user.estadoPrestamo);
                $('#modal-edit #estadoP').attr('selected','')
                // $('#modal-edit #valorPrestamoInicial').attr(user.valorPrestamoInicial);
                $('#modal-edit #valorPrestamoInicial').val(user.valorPrestamoInicial);
                $('#modal-edit #valorPrestamoInicial').text(user.valorPrestamoInicial);

            });
            $('#modal-edit').modal();
        } else {
            $('#modal-add #idPrestamo').val('');
            $('#modal-add #documentoPrestamista').val('');
            $('#modal-add #nombrePrestamista').val('');
            $('#modal-add #fechaPagoOportuno').val('');
            $('#modal-add #valorPrestamo').val('');
            $('#modal-add #tasaPrestamo').val('');
            $('#modal-add #periodo').text('Seleccionar');
            $('#modal-add #periodo').attr('selected', '')
            $('#modal-add #periodo').val('');
            $('#modal-add #numeroCoutas').val('');
            $('#modal-add #estadoP').text('');
            $('#modal-add #estadoP').val('');
            $('#modal-add').modal();
        }
    });
});