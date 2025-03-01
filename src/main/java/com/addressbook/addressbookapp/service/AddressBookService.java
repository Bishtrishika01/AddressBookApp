package com.addressbook.addressbookapp.service;

import com.addressbook.addressbookapp.dto.AddressBookDTO;
import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.repository.AddressBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {
    @Autowired
    private AddressBookRepository repository;

    public List<AddressBook> getAllEntries() {
        return repository.findAll();
    }

    public AddressBook getEntryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public AddressBook createEntry(AddressBookDTO dto) {
        AddressBook entry = new AddressBook();
        entry.setName(dto.getName());
        entry.setEmail(dto.getEmail());
        entry.setPhone(dto.getPhone());
        entry.setAddress(dto.getAddress());
        return repository.save(entry);
    }

    public AddressBook updateEntry(Long id, AddressBookDTO dto) {
        Optional<AddressBook> existingEntry = repository.findById(id);
        if (existingEntry.isPresent()) {
            AddressBook entry = existingEntry.get();
            entry.setName(dto.getName());
            entry.setEmail(dto.getEmail());
            entry.setPhone(dto.getPhone());
            entry.setAddress(dto.getAddress());
            return repository.save(entry);
        }
        return null;
    }

    public boolean deleteEntry(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
