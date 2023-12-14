package com.wipro.atmcustomer.services;

import com.wipro.atmcustomer.entities.MiniStatement;
import com.wipro.atmcustomer.entities.BankCheque;
import com.wipro.atmcustomer.entities.BankCustomer;
import com.wipro.atmcustomer.repositories.BankChequeRepository;
import com.wipro.atmcustomer.repositories.BankCustomerRepository;
import com.wipro.atmcustomer.repositories.MiniStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AtmService {

    @Autowired
    private BankCustomerRepository bankCustomerRepository;
    @Autowired
    private BankChequeRepository bankChequeRepository;
    @Autowired
    private MiniStatementRepository miniStatementRepository;

    public Optional<BankCustomer> login(String cardNo, String pin) {
        return bankCustomerRepository.findByAccountNoAndAtmPin(cardNo, pin);
    }
    public void createStatement(BankCustomer bankCustomer, Double amount, Boolean isDeposit){
        MiniStatement statement = new MiniStatement(null, amount, bankCustomer, LocalDate.now(), LocalTime.now(),isDeposit  ? "DEPOSITED" : "WITHDRAW");
        miniStatementRepository.save(statement);
    }

    @Transactional
    public String withdrawCashFromUser(String username, String atmPin, Double amount){
        Optional<BankCustomer> customerOptional = bankCustomerRepository.findByUsernameIgnoreCaseAndAtmPinIgnoreCase(username, atmPin);
        if(customerOptional.isEmpty()){
            return "Invalid ATM Card details";
        }
        BankCustomer bankCustomer = customerOptional.get();
        if(bankCustomer.getBalance() < amount){
            return "Insufficient Balance";
        }
        bankCustomer.setBalance(bankCustomer.getBalance() - amount);
        bankCustomerRepository.save(bankCustomer);
        createStatement(bankCustomer, amount, false);
        return amount+ " , Cash withdraw successful";
    }
    public List<MiniStatement> generateStatement(String username, String atmPin){
        Optional<BankCustomer> customerOptional = bankCustomerRepository.findByUsernameIgnoreCaseAndAtmPinIgnoreCase(username, atmPin);
        if(customerOptional.isEmpty()){
            return List.of();
        }
        BankCustomer bankCustomer = customerOptional.get();
        return miniStatementRepository.findByBankCustomer(bankCustomer);
    }

    public String resetPin(String username, String oldPin, String newPin){
        Optional<BankCustomer> customerOptional = bankCustomerRepository.findByUsernameIgnoreCaseAndAtmPinIgnoreCase(username, oldPin);
        if(customerOptional.isEmpty()){
            return "Invalid ATM Card details";
        }
        BankCustomer bankCustomer = customerOptional.get();
        bankCustomer.setAtmPin(newPin);
        bankCustomerRepository.save(bankCustomer);
        return "Pin successfully changed";
    }
    @Transactional
    public String createCheque(BankCheque bankCheque){
        Optional<BankCustomer> customerOptional = bankCustomerRepository.findByAccountNoIgnoreCase(bankCheque.getAccountNo());
        if(customerOptional.isEmpty()){
            return "Invalid Account details";
        }
        BankCustomer bankCustomer = customerOptional.get();
        bankCustomer.setBalance(bankCustomer.getBalance()+ bankCheque.getAmount());
        bankChequeRepository.save(bankCheque);
        bankCustomerRepository.save(bankCustomer);
        createStatement(bankCustomer, bankCheque.getAmount(), true);
        return "Cheque successfully deposited";
    }




}
