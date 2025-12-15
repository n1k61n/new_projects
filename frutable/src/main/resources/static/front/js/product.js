function removeProduct(productId) {
    if (confirm("Əminsiniz? Bu kateqoriya silinəcək.")) {
        const deleteUrl = '/dashboard/product/delete/' + productId;

        fetch(deleteUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                alert("Kateqoriya uğurla silindi.");
                window.location.reload();
            } else {
                response.json().then(data => {
                    alert("Silinmə zamanı xəta baş verdi: " + (data.message || "Naməlum xəta."));
                }).catch(() => {
                    alert("Silinmə zamanı xəta baş verdi. (Status: " + response.status + ")");
                });
            }
        })
        .catch(error => {
            console.error('Şəbəkə xətası:', error);
            alert("Şəbəkə xətası: Serverə qoşulmaq mümkün olmadı.");
        });
    }
}