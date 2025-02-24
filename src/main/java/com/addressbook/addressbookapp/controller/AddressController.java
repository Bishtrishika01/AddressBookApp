package com.addressbook.addressbookapp.controller;

import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.service.AddressBookService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@RestController
@RequestMapping("/addressbook")
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressBookService addressBookService;

    @GetMapping
    public List<AddressBook> getAllAddress(){
        logger.info("All address endpoint called ");
        return addressBookService.getAllAddresses();
    }

    @GetMapping("/get/{id}")
    public Optional<AddressBook> getAddressById(@PathVariable Long id){
        logger.info("By id address endpoint called ");
        return addressBookService.getAddressById(id);
    }

    @PostMapping("/create")
    public AddressBook saveAddress(@RequestBody AddressBook addressBook){
        logger.info("Create address endpoint called ");
        return addressBookService.addAddress(addressBook);
    }

    @PutMapping("/update/{id}")
    public AddressBook updateAddress(@PathVariable Long id,@RequestBody AddressBook addressBook){
        logger.info("update address end point called");
        return addressBookService.updateAddress(id,addressBook);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id){
        logger.info("Delete address end point called");
        return addressBookService.deleteAddressById(id);
    }
}
