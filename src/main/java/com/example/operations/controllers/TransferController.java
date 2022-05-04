/** Контроллер для перевода с счета списания на счет зачисления */

package com.example.operations.controllers;

import com.example.operations.dto.TransferBodyRequest;
import com.example.operations.entity.Transaction;
import com.example.operations.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    /** Метод необходим, чтобы в теле запроса передавать счет списания, зачисления,
     * валюту зачисления и сумму */

    @PostMapping("/make-transfer")
    public Transaction makeTransfer(@RequestBody TransferBodyRequest transferRequest) throws JAXBException {
        return transferService.makeTransfer(transferRequest);
    }

    /** Вывод транзакции по id */
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable("id") Long id){
        return transferService.findTransaction(id);
    }

}
