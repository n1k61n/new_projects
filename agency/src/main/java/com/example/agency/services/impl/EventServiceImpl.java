package com.example.agency.services.impl;

import com.example.agency.dtos.responce.EventResponceDTO;
import com.example.webchat.ResourceNotFoundException;
import com.example.agency.models.Event;
import com.example.agency.repositories.EventRepository;
import com.example.agency.services.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final ModelMapper modelMapper;

    /**
     * Belirtilen ID'ye sahip bir Event'i bulur ve DTO'ya dönüştürür.
     * Bulunamazsa bir istisna fırlatır (Örn: ResourceNotFoundException).
     */
    @Override
    public EventResponceDTO getEventById(Long id) {
        // ID ile veritabanından varlığı (entity) getir
        Event entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));

        // Varlığı DTO'ya dönüştür ve döndür
        return modelMapper.map(entity, EventResponceDTO.class);
    }

    /**
     * Yeni bir Event oluşturur. Gelen DTO'yu varlığa dönüştürür,
     * kaydeder ve geri DTO'ya dönüştürerek döndürür.
     */
    @Override
    public EventResponceDTO createEvent(EventResponceDTO dto) {
        // Gelen DTO'yu varlığa dönüştür
        Event entityToSave = modelMapper.map(dto, Event.class);

        // Varlığı veritabanına kaydet
        Event savedEntity = repository.save(entityToSave);

        // Kaydedilen varlığı tekrar DTO'ya dönüştür ve döndür
        return modelMapper.map(savedEntity, EventResponceDTO.class);
    }

    /**
     * Belirtilen ID'ye sahip Event'i günceller.
     * Bulunamazsa bir istisna fırlatır.
     */
    @Override
    public EventResponceDTO updateEvent(Long id, EventResponceDTO dto) {
        // Güncellenecek mevcut varlığı ID ile bul
        Event existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));

        // Gelen DTO'daki alanları mevcut varlığa aktar (ModelMapper burada yardımcı olur)
        // ID'yi korumak önemlidir. ModelMapper genellikle hedef nesneye (existingEntity) map yapar.
        modelMapper.map(dto, existingEntity);

        // Güncellenmiş varlığı kaydet
        Event updatedEntity = repository.save(existingEntity);

        // Güncellenen varlığı DTO'ya dönüştür ve döndür
        return modelMapper.map(updatedEntity, EventResponceDTO.class);
    }

    /**
     * Belirtilen ID'ye sahip Event'i siler.
     * Başarılı olursa 'true' döner.
     */
    @Override
    public boolean deleteById(Long id) {
        // Silme işleminden önce varlığın varlığını kontrol etmek iyi bir pratiktir,
        // ancak JpaRepository'nin deleteById metodu genellikle varlık yoksa bir şey yapmaz veya
        // yapılandırmaya bağlı olarak istisna fırlatabilir. Güvenli bir kontrol ekleyelim:
        if (!repository.existsById(id)) {
            // Silinecek bir şey yoksa, başarısız kabul edilebilir veya varlık zaten yoksa
            // operasyonun amacına ulaşıldığı kabul edilebilir.
            // Bu örnekte, NotFound hatası yerine false döndürmeyi tercih edelim.
            return false;
        }

        repository.deleteById(id);

        // Silme işleminin başarılı olup olmadığını kontrol etmek için tekrar bakılabilir.
        // Genellikle deleteById çağrıldıktan sonra metot true döner.
        return true;
    }
}

