function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        output.style.display = 'inline-block';
    };
    reader.readAsDataURL(event.target.files[0]);

    // Seçilən faylın adını input üzərində göstərmək üçün
    var fileName = event.target.files[0].name;
    event.target.nextElementSibling.innerText = fileName;
}