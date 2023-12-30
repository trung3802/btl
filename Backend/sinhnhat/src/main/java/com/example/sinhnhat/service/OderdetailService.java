package com.example.sinhnhat.service;

import java.util.List;
import com.example.sinhnhat.entity.OrderDetail;

public interface OderdetailService {
    List<OrderDetail> getOrderDetailsByOrderId(long id);
    List<OrderDetail> getList();

}
