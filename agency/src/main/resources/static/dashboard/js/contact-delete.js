function deleteContact(element) {
    const id = element.getAttribute('data-id');
    const name = element.getAttribute('data-name');
    if (!id) {
        console.error("Silinəcək kontakt ID-si tapılmadı.");
        alert('Silmə əməliyyatı üçün ID tapılmadı!');
        return;
    }
    if (!confirm(`Əminsiniz? '${name}' adlı  kontakti silməyi təsdiqləyin. Bu əməliyyat geri qaytarılmazdır.`)) {
        return;
    }
    const url = `/dashboard/contact/delete/${id}`;
    window.location.href = url;
}