//package com.example.githubactiontest.repository;
//
//
//import com.example.githubactiontest.model.Product;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface ProductRepository extends JpaRepository<Product, Long> {
//    Page<Product> findAllByUserId(Long userId, Pageable pageable);
//
//    List<Product> findAllByUserId(Long userId);
//}