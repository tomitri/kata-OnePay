package fr.poc.kata.onepay.services;

import fr.poc.kata.onepay.domain.Transaction;
import fr.poc.kata.onepay.domain.enums.PaymentStatus;
import fr.poc.kata.onepay.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transaction createTransaction(Transaction transactionToSave) {
        LOGGER.info("creatingTransaction -> ()");
        transactionToSave.setPaymentStatus(PaymentStatus.NEW);
        Transaction transaction = transactionRepository.save(transactionToSave);
        LOGGER.info("creatingTransaction <- ()");
        return transaction;
    }

    @Transactional
    public Transaction updateTransactionStatus(Long id, PaymentStatus status) {

        LOGGER.info(String.format("updatingTransaction -> id Transaction is %s", id));

        //TODO maybe à améliorer
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        if (transaction.getPaymentStatus() == PaymentStatus.CAPTURED || transaction.getPaymentStatus() == PaymentStatus.CANCELED) {
            throw new IllegalStateException("Cannot change status of a captured or canceled transaction");
        }
        if (status == PaymentStatus.CAPTURED && transaction.getPaymentStatus() != PaymentStatus.AUTHORIZED) {
            throw new IllegalStateException("Can only capture an authorized transaction");
        }
        transaction.setPaymentStatus(status);
        return transactionRepository.save(transaction);

    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     Specify the name of the file to search
     @param fileToSearch
     */
    /*
    public String searchFile(String fileToSearch) throws IOException, GeneralSecurityException {
        AtomicReference<String> iDFile = new AtomicReference<>("");

        Drive service = constructClientDrive();

        FileList result = service.files().list()
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<com.google.api.services.drive.model.File> files = result.getFiles();

        if (files == null || files.isEmpty()) {
            LOGGER.info("searchFile() -> Aucun fichiers trouvés");
        }
        else {
            files.stream().filter(e -> e.getName().equalsIgnoreCase(fileToSearch)).findFirst().ifPresent(e -> iDFile.set(e.getId()));
        }

        return iDFile.get();
    }

     */

}
