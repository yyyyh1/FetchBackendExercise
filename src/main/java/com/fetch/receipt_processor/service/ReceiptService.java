package com.fetch.receipt_processor.service;

import com.fetch.receipt_processor.entity.Receipt;
import com.fetch.receipt_processor.entity.ReceiptItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Integer> receiptScore = new HashMap<>();

    public String processReceipt(Receipt receipt){
        String receiptId = UUID.randomUUID().toString();
        int points = getPoints(receipt);
        receiptScore.put(receiptId, points);
        return receiptId;
    }

    public Integer getPoints(String receiptId){
        return  receiptScore.get(receiptId);
    }

    private int getPoints(Receipt receipt) {
        int points = 0;

        // 1. One point for every alphanumeric character in the retailer name
        for(char c : receipt.getRetailer().toCharArray()){
            if (Character.isAlphabetic(c) || Character.isDigit(c)){
                points++;
            }
        }


        // 2. 50 points if the total is a round dollar amount (no cents)
        double total = Double.parseDouble(receipt.getTotal());
        if (total == Math.floor(total)){
            points += 50;
        }

        // 3. 25 points if the total is a multiple of 0.25
        if (total % 0.25 == 0){
            points += 25;
        }

        // 4. 5 points for every item on the receipt
        points += 5 * (receipt.getItems().size() / 2);

        // 5. Multiply points by the length of the item description
        for (ReceiptItem item: receipt.getItems()) {
            if (item.getShortDescription().trim().length() % 3 == 0){
                Double p = Double.parseDouble(item.getPrice()) * 0.2;
                points += Math.ceil(p);
            }
        }

        // 6. 6 points if the purchase date is odd
        LocalDate purchaseDate = LocalDate.parse(receipt.getPurchaseDate());
        if (purchaseDate.getDayOfMonth() % 2 == 1){
            points += 6;
        }

        // 7. 10 points if the purchase time is between 2:00 PM and 4:00 PM
        LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime());
        if (purchaseTime.isAfter(LocalTime.of(14, 0)) && purchaseTime.isBefore(LocalTime.of(16, 0))){
            points += 10;
        }

        return  points;
    }
}
