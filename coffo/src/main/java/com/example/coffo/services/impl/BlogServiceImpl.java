package com.example.coffo.services.impl;

import com.example.coffo.DTOs.responce.BlogResponceDTO;
import com.example.coffo.models.Blog;
import com.example.coffo.repositories.BlogRepository;
import com.example.coffo.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Override
    public BlogResponceDTO getBlogById(Long id) {

        // 1. Repository-dən Blog Entity-sini Optional olaraq alın
        Optional<Blog> blogOptional = blogRepository.findById(id);

        // 2. Blog-un mövcudluğunu yoxlayın
        if (blogOptional.isPresent()) {

            // 3. Əgər mövcuddursa (isPresent), Entity-ni əldə edin
            Blog blogEntity = blogOptional.get();

            // 4. Entity-ni DTO-ya çevirin və qaytarın
            // Burada ModelMapper istifadə edilməlidir.
            return modelMapper.map(blogEntity, BlogResponceDTO.class);
        }

        // 5. Əgər Entity tapılmayıbsa (Optional boşdursa), null qaytarın
        return null;
    }

    @Override
    public BlogResponceDTO createBlog(BlogResponceDTO blogDTO) {
        // 1. DTO-dan Entity-yə avtomatik çevirmə (Mapping)
        // ModelMapper-i istifadə edərək DTO-nu Blog Entity sinfinə çeviririk.
        Blog blog = modelMapper.map(blogDTO, Blog.class);

        // 2. Entity-ni bazaya saxla
        Blog savedBlog = blogRepository.save(blog);

        // 3. Saxlanılan Entity-dən DTO-ya avtomatik çevirmə (ID daxil olmaqla)
        // Saxlanılan Entity-ni (ID daxil olmaqla) cavab DTO-suna çeviririk.
        return modelMapper.map(savedBlog, BlogResponceDTO.class);
    }


    @Override
    public BlogResponceDTO updateBlog(Long id, BlogResponceDTO blogResponceDTO) {
        // 1. Entity-ni ID əsasında bazadan gətir (yoxdursa, null qaytar)
        Blog existingBlog = blogRepository.findById(id).orElse(null);

        // Yoxlama: Əgər Entity tapılmayıbsa, null qaytar
        if (existingBlog == null) {
            return null;
        }

        // 2. Dəyişiklikləri tətbiq et (Mapping)
        // ModelMapper istifadə edərək, gələn blogResponceDTO-dakı bütün yeni dəyərləri
        // bazadan gələn existingBlog obyektinin üzərinə yaz (update).
        // Bu, ID və ya digər statik sahələrə toxunmadan yalnız verilmiş dəyərləri yeniləyir.
        modelMapper.map(blogResponceDTO, existingBlog);

        // 3. Entity-ni bazada saxla
        // Entity-ni yenilənmiş vəziyyətdə (updated state) bazaya saxlayırıq.
        Blog savedBlog = blogRepository.save(existingBlog);

        // 4. Yenilənmiş Entity-ni DTO-ya çevir və qaytar
        // ModelMapper istifadə edərək son nəticəni cavab DTO-suna çeviririk.
        return modelMapper.map(savedBlog, BlogResponceDTO.class);
    }

    @Override
    public boolean deleteBlog(Long id) {
        if(blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

