package com.addressbook.addressbookapp.controller;

import com.addressbook.addressbookapp.dto.AddressBookDTO;
import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private final AddressBookService service;

    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressBook>> getAllEntries() {
        return ResponseEntity.ok(service.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getEntryById(@PathVariable Long id) {
        AddressBook entry = service.getEntryById(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<AddressBook> createEntry(@RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(service.createEntry(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBook> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        AddressBook updatedEntry = service.updateEntry(id, dto);
        return updatedEntry != null ? ResponseEntity.ok(updatedEntry) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        return service.deleteEntry(id) ? ResponseEntity.ok("Deleted Successfully") : ResponseEntity.notFound().build();
    }
}
