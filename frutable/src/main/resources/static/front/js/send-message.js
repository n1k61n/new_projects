document.addEventListener("DOMContentLoaded", function() {
    const success = document.body.getAttribute('data-success');
    if (success === 'true') {
        var myModal = new bootstrap.Modal(document.getElementById('successModal'));
        myModal.show();
    }
});
