package ru.vsu.cs.boldyrev.shopik.service;

import org.springframework.stereotype.Service;
import ru.vsu.cs.boldyrev.shopik.dto.CreateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.entity.Order;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class OrderService {
    public Order createOrder(CreateOrderDTO dto) {
        System.out.println(dto.getPhone());
        return new Order();
    }


    public static BigDecimal calculateDistance(BigDecimal lat1, BigDecimal lon1,
                                               BigDecimal lat2, BigDecimal lon2) {
        BigDecimal EARTH_RADIUS_KM = new BigDecimal("6371.0");
        MathContext MC = new MathContext(10, RoundingMode.HALF_UP);
        double lat1Rad = Math.toRadians(lat1.doubleValue());
        double lon1Rad = Math.toRadians(lon1.doubleValue());
        double lat2Rad = Math.toRadians(lat2.doubleValue());
        double lon2Rad = Math.toRadians(lon2.doubleValue());

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM.doubleValue() * c;

        return new BigDecimal(distance, MC);
    }
}
