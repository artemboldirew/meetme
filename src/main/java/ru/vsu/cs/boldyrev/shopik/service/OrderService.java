package ru.vsu.cs.boldyrev.shopik.service;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderItemStatus;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderStatus;
import ru.vsu.cs.boldyrev.shopik.dictionary.PaymentStatus;
import ru.vsu.cs.boldyrev.shopik.dto.CreateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.OrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.OrderItemDTO;
import ru.vsu.cs.boldyrev.shopik.dto.ProductAmount;
import ru.vsu.cs.boldyrev.shopik.dto.UpdateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.UpdateOrderItemDTO;
import ru.vsu.cs.boldyrev.shopik.entity.Customer;
import ru.vsu.cs.boldyrev.shopik.entity.Order;
import ru.vsu.cs.boldyrev.shopik.entity.OrderItem;
import ru.vsu.cs.boldyrev.shopik.entity.PickupPoint;
import ru.vsu.cs.boldyrev.shopik.entity.Product;
import ru.vsu.cs.boldyrev.shopik.mapper.OrderItemMapper;
import ru.vsu.cs.boldyrev.shopik.mapper.OrderMapper;
import ru.vsu.cs.boldyrev.shopik.repository.CustomerRepository;
import ru.vsu.cs.boldyrev.shopik.repository.OrderItemRepository;
import ru.vsu.cs.boldyrev.shopik.repository.OrderRepository;
import ru.vsu.cs.boldyrev.shopik.repository.PickupPointRepository;
import ru.vsu.cs.boldyrev.shopik.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для управления заказами и позициями заказов.
 * Предоставляет бизнес-логику для создания, обновления и получения информации о заказах.
 * Обрабатывает связи между заказами, покупателями, товарами и пунктами выдачи.
 *
 * @author Boldyrev
 * @version 1.0
 * @see Order
 * @see OrderItem
 * @see OrderDTO
 * @see OrderItemDTO
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PickupPointRepository pickupPointRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    /**
     * Конструктор для внедрения зависимостей репозиториев и мапперов.
     *
     * @param orderRepository репозиторий для работы с заказами
     * @param orderItemRepository репозиторий для работы с позициями заказов
     * @param customerRepository репозиторий для работы с покупателями
     * @param productRepository репозиторий для работы с товарами
     * @param pickupPointRepository репозиторий для работы с пунктами выдачи
     * @param orderMapper маппер для преобразования заказов
     * @param orderItemMapper маппер для преобразования позиций заказов
     */
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, ProductRepository productRepository, PickupPointRepository pickupPointRepository, OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.pickupPointRepository = pickupPointRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    /**
     * Создает новый заказ на основе переданных данных.
     * Процесс включает проверку существования товаров, расчет итоговой стоимости,
     * создание позиций заказа и установку статусов.
     *
     * @param dto DTO с данными для создания заказа
     * @param customerId идентификатор покупателя, создающего заказ
     * @return DTO созданного заказа
     * @throws EntityNotFoundException если указанные товары не найдены
     * @see CreateOrderDTO
     * @see OrderDTO
     */
    @Transactional
    public OrderDTO createOrder(CreateOrderDTO dto, UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        BigDecimal finalCost = BigDecimal.ZERO;
        List<OrderItem> products = new ArrayList<>();

        List<UUID> productIds = dto.getProducts().stream()
                .map(ProductAmount::getProductId)
                .collect(Collectors.toList());

        Map<UUID, Product> productMap = productRepository.findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        List<UUID> missingProducts = productIds.stream()
                .filter(id -> !productMap.containsKey(id))
                .toList();

        if (!missingProducts.isEmpty()) {
            throw new EntityNotFoundException("Products not found: " + missingProducts);
        }

        Order newOrder = new Order();
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setPaymentStatus(PaymentStatus.PENDING);
        PickupPoint pickupPoint = pickupPointRepository.findById(dto.getPickupPointId()).orElseThrow(() -> new EntityNotFoundException("PickupPoint not found"));
        newOrder.setPickupPoint(pickupPoint);
        newOrder.setCustomer(customer);

        for (ProductAmount pa : dto.getProducts()) {
            System.out.println(pa.getProductId());
            Product product =  productMap.get(pa.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemStatus(OrderItemStatus.PENDING);
            orderItem.setProduct(product);
            orderItem.setQuantity(pa.getQuantity());
            LocalDate now = LocalDate.now();
            LocalDate deliveryDate = now.plusDays((long) (Math.random() * 7));
            orderItem.setDeliveryDate(deliveryDate);
            orderItem.setOrder(newOrder);

            products.add(orderItem);

            BigDecimal itemCost = product.getPrice().multiply(BigDecimal.valueOf(pa.getQuantity()));
            finalCost = finalCost.add(itemCost);
        }
        newOrder.setAmount(finalCost);
        newOrder.setItems(products);
        Order full = orderRepository.save(newOrder);

        return orderMapper.toDto(full);
    }

    /**
     * Обновляет статус существующего заказа.
     *
     * @param id идентификатор заказа для обновления
     * @param dto DTO с новыми данными заказа
     * @return DTO обновленного заказа
     * @throws EntityNotFoundException если заказ с указанным идентификатором не найден
     * @see UpdateOrderDTO
     */
    @Transactional
    public OrderDTO updateOrder(UUID id, UpdateOrderDTO dto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setOrderStatus(dto.getOrderStatus());
        Order updatedorder = orderRepository.save(order);
        return orderMapper.toDto(updatedorder);
    }


    /**
     * Обновляет информацию о позиции заказа.
     * Может обновлять статус позиции и/или дату доставки.
     *
     * @param itemId идентификатор позиции заказа для обновления
     * @param dto DTO с новыми данными позиции заказа
     * @return DTO обновленной позиции заказа
     * @throws EntityNotFoundException если позиция заказа с указанным идентификатором не найдена
     * @see UpdateOrderItemDTO
     */
    @Transactional
    public OrderItemDTO updateOrderItem(UUID itemId,  UpdateOrderItemDTO dto) {
        OrderItem orderItem = orderItemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));
        if (dto.getOrderItemStatus() != null) {
            orderItem.setOrderItemStatus(dto.getOrderItemStatus());
        }
        if (dto.getDeliveryDate() != null) {
            orderItem.setDeliveryDate(dto.getDeliveryDate());
        }
        OrderItem item = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(item);
    }


    /**
     * Получает информацию о заказе по его идентификатору с проверкой прав доступа.
     * Проверяет, принадлежит ли заказ указанному покупателю.
     *
     * @param orderId идентификатор заказа
     * @param customerId идентификатор покупателя для проверки прав доступа
     * @return DTO заказа
     * @throws EntityNotFoundException если заказ с указанным идентификатором не найден
     * @throws BadRequestException если заказ не принадлежит указанному покупателю
     */
    @Transactional
    public OrderDTO getOrderById(UUID orderId, UUID customerId) throws BadRequestException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        if (!order.getCustomer().getId().equals(customerId)) {
            throw new BadRequestException("The user does not have such an order.");
        }
        return orderMapper.toDto(order);
    }

    /**
     * Получает информацию о позиции заказа по ее идентификатору с проверкой прав доступа.
     * Проверяет, принадлежит ли позиция заказа указанному покупателю.
     *
     * @param itemId идентификатор позиции заказа
     * @param customerId идентификатор покупателя для проверки прав доступа
     * @return DTO позиции заказа
     * @throws EntityNotFoundException если позиция заказа с указанным идентификатором не найдена
     * @throws BadRequestException если позиция заказа не принадлежит указанному покупателю
     */
    @Transactional
    public OrderItemDTO getOrderItem(UUID itemId, UUID customerId) throws BadRequestException {
        OrderItem orderItem = orderItemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));
        if (!orderItem.getOrder().getCustomer().getId().equals(customerId)) {
            throw new BadRequestException("The user does not have such an orderItem.");
        }
        return orderItemMapper.toDto(orderItem);
    }
}