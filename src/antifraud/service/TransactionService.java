package antifraud.service;

import antifraud.dto.Response;
import antifraud.models.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    ResponseEntity<Response> checkTransaction(Transaction transaction);
}
