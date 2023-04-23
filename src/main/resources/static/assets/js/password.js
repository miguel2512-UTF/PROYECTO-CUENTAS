$(document).ready(function(){

    $('#test').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (test) {
            if (!test) {
                $('.ans').text('contraseña anterior incorrecta')
                $('#modal-add').modal('show')
            }else{
                $('#formpassword').submit()
            }            
        })
    });
});