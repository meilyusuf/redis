

$( document ).ready(function() {
    $("#submitForm").click(function(e){
        e.preventDefault();
        submitForm();
    });

});

function submitForm() {

    let formItem = $('#formItem');
    let data = new FormData(formItem[0]);
    console.log(data);
    $.ajax({
        type: 'POST',
        enctype: 'application/json',
        processData: false,
        contentType:false,
        url: '/add',
        data: data,
        success: function (response) {
            success("data Saved", formItem);
        },
        error: function (e) {
            error("error save data", formItem);
        }
    });

}

function success(message,e){

    $( "div.success, div.error:not('.col')").remove();
    $(e).after('<div class="alert alert-success success"></div>');
    $( "div.success" ).text(message);
    $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );

}

function error(message, e){
    console.log(message);
    $( "div.error, div.success:not('.col')").remove();
    $(e).after('<div class="alert alert-danger error"></div>');
    $( "div.error" ).text(message);
    $( "div.error" ).fadeIn( 300 ).delay( 2500 );

}