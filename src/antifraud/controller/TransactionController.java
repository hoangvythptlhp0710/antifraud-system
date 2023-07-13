package antifraud.controller;

import antifraud.dto.Response;
import antifraud.models.Status;
import antifraud.models.Transaction;
import antifraud.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/antifraud")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<Response> makePurchase(@RequestBody Transaction transaction) {
        Long amount;
        try {
            amount = Long.parseLong(transaction.getAmount().toString());
        } catch (NumberFormatException e) {
            amount = 0L;
        }
        if (amount <= 0) {
            return ResponseEntity.badRequest().build();
        }
        else if (amount <= 200) {
            return ResponseEntity.ok(Response.builder().result(Status.ALLOWED).build());
        } else if (amount <= 1500) {
            return ResponseEntity.ok(Response.builder().result(Status.MANUAL_PROCESSING).build());
        }
        return ResponseEntity.ok(Response.builder().result(Status.PROHIBITED).build());
    }
}
