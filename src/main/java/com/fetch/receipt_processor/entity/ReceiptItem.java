package com.fetch.receipt_processor.entity;

import lombok.Data;

@Data
public class ReceiptItem {
    private String shortDescription;
    private String price;
}
