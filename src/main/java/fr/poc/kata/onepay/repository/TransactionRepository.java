package fr.poc.kata.onepay.repository;

import fr.poc.kata.onepay.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //List<Transaction> findAll();

}