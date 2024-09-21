package com.fetch.receipt_processor.Controller;

import com.fetch.receipt_processor.entity.Receipt;
import com.fetch.receipt_processor.service.ReceiptService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Resource
    private ReceiptService receiptService;

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt){
        String receiptId = receiptService.processReceipt(receipt);
        return Collections.singletonMap("id", receiptId);
    }

    @GetMapping("/{id}/points")
    public Map<String, Integer> getReceiptPoints(@PathVariable String id){
        Integer points = receiptService.getPoints(id);
        if (points == null){
            throw new NoSuchElementException("Receipt not found");
        }
        return Collections.singletonMap("points", points);
    }
}
