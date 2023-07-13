package antifraud.controller;

import antifraud.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/antifraud")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity makePurchase(@RequestBody Map<String, String> map) {
        String str = map.get("amount");
        Long amount;
        try {
            amount = Long.parseLong(str);
        } catch (NumberFormatException e) {
            amount = 0L;
        }
        if (amount <= 0) {
            return new ResponseEntity(Map.of("amount", amount), HttpStatus.BAD_REQUEST);
        }
        else if (amount <= 200) {
            return new ResponseEntity(Map.of("result", "ALLOWED"), HttpStatus.OK);
        } else if (amount <= 1500) {
            return new ResponseEntity(Map.of("result", "MANUAL_PROCESSING"), HttpStatus.OK);
        }
        return new ResponseEntity(Map.of("result", "PROHIBITED"), HttpStatus.OK);
    }
}
