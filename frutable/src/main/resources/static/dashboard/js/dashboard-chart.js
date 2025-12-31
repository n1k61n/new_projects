document.addEventListener("DOMContentLoaded", function() {
    var ctxArea = document.getElementById("myAreaChart");
    var ctxPie = document.getElementById("myPieChart");

    if (ctxArea) {
        var earningsData = JSON.parse(ctxArea.getAttribute("data-earnings") || "[]");

        // Gradient rəng yaratmaq üçün (Xəttin altındakı kölgə)
        var gradient = ctxArea.getContext('2d').createLinearGradient(0, 0, 0, 400);
        gradient.addColorStop(0, 'rgba(78, 115, 223, 0.2)');
        gradient.addColorStop(1, 'rgba(78, 115, 223, 0)');

        new Chart(ctxArea, {
            type: 'line',
            data: {
                labels: ["Yan", "Fev", "Mar", "Apr", "May", "İyun", "İyul", "Avq", "Sen", "Okt", "Noy", "Dek"],
                datasets: [{
                    label: "Aylıq Gəlir",
                    data: earningsData,
                    fill: true,
                    backgroundColor: gradient, // Gradient tətbiq olunur
                    borderColor: '#4e73df',
                    borderWidth: 4, // Xəttin qalınlığı
                    tension: 0.4,   // Xəttin dalğalı (soft) olması
                    pointRadius: 4,
                    pointBackgroundColor: '#4e73df',
                    pointBorderColor: '#fff',
                    pointHoverRadius: 6,
                }]
            },
            options: {
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false } // Üst başlıq gizlədilir
                },
                scales: {
                    x: {
                        grid: { display: false } // Şaquli xətlər silinir
                    },
                    y: {
                        beginAtZero: true,
                        grid: { color: 'rgba(0, 0, 0, 0.05)' } // Üfüqi xətlər solğunlaşdırılır
                    }
                }
            }
        });
    }

    if (ctxPie) {
        var categoryData = JSON.parse(ctxPie.getAttribute("data-categories") || "[]");
        new Chart(ctxPie, {
            type: 'doughnut',
            data: {
                labels: ["Meyvə", "Tərəvəz", "Digər"],
                datasets: [{
                    data: categoryData,
                    backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                    borderWidth: 5,
                    hoverOffset: 10
                }],
            },
            options: {
                maintainAspectRatio: false,
                cutout: '75%', // Ortadakı boşluq (müasir görünüş üçün)
                plugins: {
                    legend: { position: 'bottom' }
                }
            }
        });
    }
});