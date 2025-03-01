package com.addressbook.addressbookapp.controller;

import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressBookService addressBookService;

    public AddressController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllAddresses() {
        logger.info("Fetching all addresses");
        return ResponseEntity.ok(addressBookService.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AddressBook>> getAddressById(@PathVariable Long id) {
        logger.info("Fetching address with id: {}", id);
        Optional<AddressBook> addressBook = addressBookService.getAddressById(id);
        return addressBook.isPresent() ? ResponseEntity.ok(addressBook) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AddressBook> saveAddress(@RequestBody AddressBook addressBook) {
        logger.info("Saving new address: {}", addressBook);
        return ResponseEntity.ok(addressBookService.saveAddress(addressBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateAddress(@PathVariable Long id, @RequestBody AddressBook addressBook) {
        logger.info("Updating address with id: {}", id);
        return ResponseEntity.ok(addressBookService.updateAddress(id, addressBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        logger.info("Deleting address with id: {}", id);
        return ResponseEntity.ok(addressBookService.deleteAddress(id));
    }
}
