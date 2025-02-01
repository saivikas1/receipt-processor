package com.FetchAssessment.ReceiptProcessor.service;

import com.FetchAssessment.ReceiptProcessor.model.Item;
import com.FetchAssessment.ReceiptProcessor.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Receipt> receiptStore = new ConcurrentHashMap<>();

    public String saveReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptStore.put(id, receipt);
        return id;
    }

    public int getPoints(String id) {
        Receipt receipt = receiptStore.get(id);
        if (receipt == null) {
            throw new NoSuchElementException("No receipt found for that ID.");
        }
        return calculatePoints(receipt);
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;

        // One point for every alphanumeric character in the retailer name
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        double total = Double.parseDouble(receipt.getTotal());

        // 50 points if the total is a round dollar amount with no cents
        if (total % 1 == 0) points += 50;

        // 25 points if the total is a multiple of 0.25
        if (total % 0.25 == 0) points += 25;

        // 5 points for every two items on the receipt
        points += (receipt.getItems().size() / 2) * 5;

        // Extra points for specific item descriptions
        for (Item item : receipt.getItems()) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                points += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }

        // Condition handling 6 points case if purchase date's day is odd
        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 == 1) points += 6;

        // Condition handling 10 points case if purchase time is between 2:00 pm and 4:00 pm
        int hour = Integer.parseInt(receipt.getPurchaseTime().split(":")[0]);
        if (hour >= 14 && hour < 16) points += 10;

        return points;
    }
}
