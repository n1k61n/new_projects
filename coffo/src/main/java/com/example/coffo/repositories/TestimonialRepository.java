package com.example.coffo.repositories;

import com.example.coffo.models.OrderItem;
import com.example.coffo.models.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository  extends JpaRepository<Testimonial, Long> {
}
