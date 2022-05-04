package com.example.operations.service;

import com.example.operations.configurations.CBRClient;
import com.example.operations.dto.TransferBodyRequest;
import com.example.operations.dto.TransferRequest;
import com.example.operations.entity.Transaction;
import com.example.operations.entity.Transfer;
import com.example.operations.enums.CurrencyEnum;
import com.example.operations.properties.ExternalProperties;
import com.example.operations.repositories.TransactionRepository;
import com.example.operations.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;
    private final RestTemplate restTemplate;
    private final ExternalProperties externalProperties;
    private final CBRClient cbrClient;

    private TransferRequest createRequest(String number) {
        TransferRequest transfer = restTemplate
                .getForObject(externalProperties.getBankservice() + number, TransferRequest.class);

        return transfer;
    }

    private TransferRequest fromAccount(String fromAccountNumber) {
        TransferRequest fromAccount = createRequest(fromAccountNumber);

        return fromAccount;
    }

    private TransferRequest toAccount(String toAccountNumber) {
        TransferRequest toAccount = createRequest(toAccountNumber);

        return toAccount;
    }

    public Transaction makeTransfer(TransferBodyRequest transferRequest) throws JAXBException {
        String fromAccountNumber = transferRequest.getFromAccountNumber();
        String toAccountNumber = transferRequest.getToAccountNumber();
        String currency = transferRequest.getCurrency();
        BigDecimal amount = transferRequest.getAmount();
        TransferRequest fromAccount = fromAccount(fromAccountNumber);
        TransferRequest toAccount = toAccount(toAccountNumber);
        Transfer transferFrom = transferRepository.findByAccountNumber(fromAccount.getAccountNumber());
        Transfer transferTo = transferRepository.findByAccountNumber(toAccount.getAccountNumber());

        transferFrom.setAmount(transferFrom.getAmount().subtract(amount));
        transferRepository.save(transferFrom);
        BigDecimal result = convertValue(fromAccount, toAccount, currency, amount);
        transferTo.setAmount(transferTo.getAmount().add(result));
        transferRepository.save(transferTo);

        Transaction transaction = transactionRepository.save(new Transaction(0L, toAccountNumber, currency, amount));

        return transaction;
    }

    private BigDecimal convertValue(TransferRequest fromAccount, TransferRequest toAccount, String currency, BigDecimal amount) throws JAXBException {
        BigDecimal result = cbrClient.getCurrencyData().getUSD().getCurs();
        Assert.notNull(result, "Currency value cannot be null.");
        boolean isCurrencyEquals = fromAccount.getCurrentCurrency().equals(toAccount.getCurrentCurrency());
        currency = CurrencyEnum.USD.getAbbreviation();

        if (isCurrencyEquals) {
            result = amount;
        } else if (fromAccount.getCurrentCurrency().equals(currency) && !isCurrencyEquals) {
            result = amount.multiply(amount);
        } else {
            result = amount.divide(result, 2, RoundingMode.HALF_UP);
        }

        return result;
    }

    public Transaction findTransaction(Long id) {
        return transactionRepository.findTransactionById(id);
    }

}
