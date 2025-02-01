package com.FetchAssessment.ReceiptProcessor.controller;

import com.FetchAssessment.ReceiptProcessor.model.Receipt;
import com.FetchAssessment.ReceiptProcessor.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public Map<String, String> saveReceipt(@RequestBody Receipt receipt) {
        String id = receiptService.saveReceipt(receipt);
        return Collections.singletonMap("id", id);
    }

    @GetMapping("/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable String id) {
        return Collections.singletonMap("points", receiptService.getPoints(id));
    }
}
