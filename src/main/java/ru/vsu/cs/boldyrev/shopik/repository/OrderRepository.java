package ru.vsu.cs.boldyrev.shopik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.boldyrev.shopik.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
