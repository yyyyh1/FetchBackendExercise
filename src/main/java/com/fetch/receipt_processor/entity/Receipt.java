package com.fetch.receipt_processor.entity;

import lombok.Data;

import java.util.List;

@Data
public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private String total;
    private List<ReceiptItem> items;
}
