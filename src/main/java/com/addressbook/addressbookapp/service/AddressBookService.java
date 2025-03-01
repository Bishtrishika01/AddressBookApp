package com.addressbook.addressbookapp.service;

import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.repository.AddressBookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {
    private final AddressBookRepository addressBookRepository;

    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    public List<AddressBook> getAllAddresses() {
        return addressBookRepository.findAll();
    }

    public Optional<AddressBook> getAddressById(Long id) {
        return addressBookRepository.findById(id);
    }

    public AddressBook saveAddress(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    public AddressBook updateAddress(Long id, AddressBook updatedAddressBook) {
        return addressBookRepository.findById(id)
                .map(addressBook -> {
                    addressBook.setName(updatedAddressBook.getName());
                    addressBook.setEmail(updatedAddressBook.getEmail());
                    addressBook.setPhone(updatedAddressBook.getPhone());
                    return addressBookRepository.save(addressBook);
                }).orElseThrow(() -> new RuntimeException("AddressBook not found"));
    }

    public String deleteAddress(Long id) {
        addressBookRepository.deleteById(id);
        return "AddressBook deleted successfully";
    }
}
