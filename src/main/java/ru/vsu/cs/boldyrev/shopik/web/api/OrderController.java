package ru.vsu.cs.boldyrev.shopik.web.api;

import org.apache.coyote.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.boldyrev.shopik.dto.CreateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.OrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.OrderItemDTO;
import ru.vsu.cs.boldyrev.shopik.dto.UpdateOrderDTO;
import ru.vsu.cs.boldyrev.shopik.dto.UpdateOrderItemDTO;
import ru.vsu.cs.boldyrev.shopik.service.OrderService;

import java.util.UUID;

/**
 * Контроллер REST API для управления заказами.
 * Предоставляет эндпоинты для создания, получения и обновления заказов и позиций заказов.
 * Все операции требуют передачи идентификатора покупателя в заголовке запроса.
 *
 * @author Boldyrev
 * @version 1.0
 * @see OrderService
 */
@Tag(name = "Заказы", description = "API для управления заказами и позициями заказов")
@RestController
@RequestMapping(OrderController.BASE_URL)
public class OrderController {
    // http://localhost:8080/api/swagger-ui/index.html
    /**
     * Базовый URL для всех эндпоинтов контроллера заказов.
     */
    public static final String BASE_URL = "/orders";

    private final OrderService orderService;

    /**
     * Конструктор для внедрения зависимости сервиса заказов.
     *
     * @param orderService сервис для управления заказами
     */
    public OrderController(OrderService orderService) {this.orderService = orderService;}

    /**
     * Создает новый заказ для указанного покупателя.
     *
     * @param dto DTO с данными для создания заказа
     * @param customerId идентификатор покупателя, передаваемый в заголовке запроса
     * @return DTO созданного заказа
     */
    @Operation(
            summary = "Создание нового заказа",
            description = "Создает новый заказ для указанного покупателя с товарами и пунктом выдачи"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса"),
            @ApiResponse(responseCode = "404", description = "Покупатель, товары или пункт выдачи не найдены")
    })
    @PostMapping("")
    public OrderDTO createOrder(
            @Parameter(description = "Данные для создания заказа", required = true)
            @RequestBody CreateOrderDTO dto,

            @Parameter(description = "Идентификатор покупателя", required = true)
            @RequestHeader("customer-id") UUID customerId) {
        return orderService.createOrder(dto, customerId);
    }

    /**
     * Получает информацию о заказе по его идентификатору.
     * Проверяет, что заказ принадлежит указанному покупателю.
     *
     * @param id идентификатор заказа
     * @param customerId идентификатор покупателя, передаваемый в заголовке запроса
     * @return DTO запрашиваемого заказа
     * @throws BadRequestException если заказ не принадлежит указанному покупателю
     */
    @Operation(
            summary = "Получение заказа по ID",
            description = "Возвращает информацию о заказе по его идентификатору с проверкой прав доступа"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "400", description = "Заказ не принадлежит указанному покупателю"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    @GetMapping("/{id}")
    public OrderDTO getOrder(
            @Parameter(description = "Идентификатор заказа", required = true)
            @PathVariable UUID id,

            @Parameter(description = "Идентификатор покупателя", required = true)
            @RequestHeader("customer-id") UUID customerId) throws BadRequestException {
        return orderService.getOrderById(id, customerId);
    }

    /**
     * Обновляет статус существующего заказа.
     *
     * @param id идентификатор заказа для обновления
     * @param dto DTO с новыми данными заказа
     * @return DTO обновленного заказа
     */
    @Operation(
            summary = "Обновление статуса заказа",
            description = "Обновляет статус существующего заказа"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    @PatchMapping("/{id}")
    public OrderDTO updateOrder(
            @Parameter(description = "Идентификатор заказа", required = true)
            @PathVariable UUID id,

            @Parameter(description = "Данные для обновления заказа", required = true)
            @RequestBody UpdateOrderDTO dto) {
        return orderService.updateOrder(id, dto);
    }

    /**
     * Обновляет информацию о позиции заказа.
     *
     * @param itemId идентификатор позиции заказа для обновления
     * @param dto DTO с новыми данными позиции заказа
     * @return DTO обновленной позиции заказа
     */
    @Operation(
            summary = "Обновление позиции заказа",
            description = "Обновляет информацию о позиции заказа (статус и дату доставки)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Позиция заказа успешно обновлена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItemDTO.class))),
            @ApiResponse(responseCode = "404", description = "Позиция заказа не найдена")
    })
    @PatchMapping("/items/{itemId}")
    public OrderItemDTO updateOrderItem(
            @Parameter(description = "Идентификатор позиции заказа", required = true)
            @PathVariable UUID itemId,

            @Parameter(description = "Данные для обновления позиции заказа", required = true)
            @RequestBody UpdateOrderItemDTO dto) {
        return orderService.updateOrderItem(itemId, dto);
    }

    /**
     * Получает информацию о позиции заказа по ее идентификатору.
     * Проверяет, что позиция заказа принадлежит указанному покупателю.
     *
     * @param itemId идентификатор позиции заказа
     * @param customerId идентификатор покупателя, передаваемый в заголовке запроса
     * @return DTO запрашиваемой позиции заказа
     * @throws BadRequestException если позиция заказа не принадлежит указанному покупателю
     */
    @Operation(
            summary = "Получение позиции заказа по ID",
            description = "Возвращает информацию о позиции заказа по ее идентификатору с проверкой прав доступа"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Позиция заказа успешно получена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItemDTO.class))),
            @ApiResponse(responseCode = "400", description = "Позиция заказа не принадлежит указанному покупателю"),
            @ApiResponse(responseCode = "404", description = "Позиция заказа не найдена")
    })
    @GetMapping("/items/{itemId}")
    public OrderItemDTO getOrderItem(
            @Parameter(description = "Идентификатор позиции заказа", required = true)
            @PathVariable UUID itemId,

            @Parameter(description = "Идентификатор покупателя", required = true)
            @RequestHeader("customer-id") UUID customerId) throws BadRequestException {
        return orderService.getOrderItem(itemId, customerId);
    }

}