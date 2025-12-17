package ru.vsu.cs.boldyrev.shopik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.boldyrev.shopik.entity.PickupPoint;

import java.util.UUID;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, UUID> {
}
