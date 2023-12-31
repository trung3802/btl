package com.example.sinhnhat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sinhnhat.entity.Order;
import com.example.sinhnhat.entity.OrderDetail;
import com.example.sinhnhat.model.request.CreateOrderRequest;
import com.example.sinhnhat.model.response.MessageResponse;
import com.example.sinhnhat.service.OderdetailService;
import com.example.sinhnhat.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OderdetailService oderdetailService;


    @GetMapping("/")
    @Operation(summary="Lấy ra danh sách đặt hàng")
    public ResponseEntity<List<Order>> getList(){
        List<Order> list = orderService.getList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/user")
    @Operation(summary="Lấy ra danh sách đặt hàng của người dùng bằng username")
    public ResponseEntity<List<Order>> getListByUser(@RequestParam("username") String username){
        List<Order> list = orderService.getOrderByUser(username);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    @Operation(summary="Đặt hàng sản phẩm")
    public ResponseEntity<?> placeOrder(@RequestBody CreateOrderRequest request){

        orderService.placeOrder(request);

        return ResponseEntity.ok(new MessageResponse("Order Placed Successfully!"));
    }
    
//    @GetMapping("/chitiet/{id}")
//    @Operation(summary="Lấy ra danh sách đơn hàng bằng id của ddơn hàng")
//    public ResponseEntity<List<OrderDetail>> getOrderDetailsByOrderId(@PathVariable long id){
//        List<OrderDetail> list = oderdetailService.getOrderDetailsByOrderId(id);
//        return ResponseEntity.ok(list);
//    }
}
