function removeCategory(productId) {
    // 1. İstifadəçidən təsdiq almaq
    if (confirm("Əminsiniz? Bu kateqoriya silinəcək.")) {

        // 2. Silmə üçün serverə sorğu göndərmək (FETCH API istifadə edərək)
        // '/dashboard/category/delete/' hissəsini Spring Controller-inizdəki silmə mapping-inə uyğun dəyişdirin
        const deleteUrl = '/dashboard/category/delete/' + productId;

        fetch(deleteUrl, {
            method: 'POST', // DELETE metodu istifadə olunur (RESTful API üçün)
            headers: {
                'Content-Type': 'application/json'
                // Əgər Spring Security istifadə edirsinizsə, CSRF tokeni də əlavə etməlisiniz
            }
        })
        .then(response => {
            if (response.ok) {
                // 3. Əməliyyat uğurlu olarsa
                alert("Kateqoriya uğurla silindi.");
                // Səhifəni yeniləmək və ya DOM-dan silinmiş sətiri çıxarmaq
                window.location.reload();
            } else {
                // 4. Əməliyyat uğursuz olarsa
                // Cavab JSON formatında error mesajı ehtiva edə bilər
                response.json().then(data => {
                    alert("Silinmə zamanı xəta baş verdi: " + (data.message || "Naməlum xəta."));
                }).catch(() => {
                    alert("Silinmə zamanı xəta baş verdi. (Status: " + response.status + ")");
                });
            }
        })
        .catch(error => {
            // Şəbəkə xətası (Serverə çatmaq mümkün olmadı)
            console.error('Şəbəkə xətası:', error);
            alert("Şəbəkə xətası: Serverə qoşulmaq mümkün olmadı.");
        });
    }
}

