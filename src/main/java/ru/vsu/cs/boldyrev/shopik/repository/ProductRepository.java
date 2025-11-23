package ru.vsu.cs.boldyrev.shopik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.boldyrev.shopik.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    Product save(Product entity);


}
