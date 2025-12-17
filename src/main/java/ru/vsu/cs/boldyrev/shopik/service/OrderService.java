package ru.vsu.cs.boldyrev.shopik.service;

import org.springframework.stereotype.Service;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderItemStatus;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderStatus;
import ru.vsu.cs.boldyrev.shopik.dictionary.PaymentStatus;
import ru.vsu.cs.boldyrev.shopik.dto.CreateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.OrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.ProductAmount;
import ru.vsu.cs.boldyrev.shopik.entity.Customer;
import ru.vsu.cs.boldyrev.shopik.entity.Order;
import ru.vsu.cs.boldyrev.shopik.entity.OrderItem;
import ru.vsu.cs.boldyrev.shopik.entity.PickupPoint;
import ru.vsu.cs.boldyrev.shopik.entity.Product;
import ru.vsu.cs.boldyrev.shopik.repository.CustomerRepository;
import ru.vsu.cs.boldyrev.shopik.repository.OrderItemRepository;
import ru.vsu.cs.boldyrev.shopik.repository.OrderRepository;
import ru.vsu.cs.boldyrev.shopik.repository.PickupPointRepository;
import ru.vsu.cs.boldyrev.shopik.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PickupPointRepository pickupPointRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, ProductRepository productRepository, PickupPointRepository pickupPointRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.pickupPointRepository = pickupPointRepository;
    }

    public OrderDTO createOrder(CreateOrderDTO dto, UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        BigDecimal finalCost = BigDecimal.ZERO;
        List<OrderItem> products = new ArrayList<>();
        for (ProductAmount pa : dto.getProducts()) {
            Product product =  productRepository.findById(pa.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemStatus(OrderItemStatus.PENDING);
            orderItem.setProduct(product);
            orderItem.setQuantity(pa.getQuantity());
            LocalDate now = LocalDate.now();
            LocalDate deliveryDate = now.plusDays((long) (Math.random() * 7));
            orderItem.setDeliveryDate(deliveryDate);
            products.add(orderItem);

            BigDecimal itemCost = product.getPrice().multiply(BigDecimal.valueOf(pa.getQuantity()));
            finalCost = finalCost.add(itemCost);
        }
        Order newOrder = new Order();
        newOrder.setAmount(finalCost);
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setPaymentStatus(PaymentStatus.PENDING);
        PickupPoint pickupPoint = pickupPointRepository.findById(dto.getPickup_point_id()).orElseThrow(() -> new IllegalArgumentException("PickupPoint not found"));
        newOrder.setPickupPoint(pickupPoint);
        newOrder.setCustomer(customer);
        newOrder = orderRepository.save(newOrder);

        for (int i = 0; i < products.size(); i++) {
            OrderItem curItem = products.get(i);
            curItem.setOrder(newOrder);
            products.set(i, orderItemRepository.save(curItem));
        }

        return new OrderDTO(newOrder, products);
    }
//
//
//    public static BigDecimal calculateDistance(BigDecimal lat1, BigDecimal lon1,
//                                               BigDecimal lat2, BigDecimal lon2) {
//        BigDecimal EARTH_RADIUS_KM = new BigDecimal("6371.0");
//        MathContext MC = new MathContext(10, RoundingMode.HALF_UP);
//        double lat1Rad = Math.toRadians(lat1.doubleValue());
//        double lon1Rad = Math.toRadians(lon1.doubleValue());
//        double lat2Rad = Math.toRadians(lat2.doubleValue());
//        double lon2Rad = Math.toRadians(lon2.doubleValue());
//
//        double deltaLat = lat2Rad - lat1Rad;
//        double deltaLon = lon2Rad - lon1Rad;
//
//        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
//                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
//                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
//
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double distance = EARTH_RADIUS_KM.doubleValue() * c;
//
//        return new BigDecimal(distance, MC);
//    }
}
