package antifraud.service;

import antifraud.dto.Response;
import antifraud.models.Status;
import antifraud.models.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Override
    public ResponseEntity<Response> checkTransaction(Transaction transaction) {
        Long amount = transaction.getAmount();
        try {

        }
        catch (NumberFormatException e){
            amount = 0L;
        }
        if (amount > 0 && amount <= 200) {
            return ResponseEntity.ok(Response.builder().result(Status.ALLOWED).build());
        }
        else if (amount > 200 && amount <= 1500) {
            return ResponseEntity.ok(Response.builder().result(Status.MANUAL_PROCESSING).build());
        }
        else if (amount > 1500) {
            return ResponseEntity.ok(Response.builder().result(Status.PROHIBITED).build());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
