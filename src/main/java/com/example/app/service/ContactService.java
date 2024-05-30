package com.example.app.service;

import com.example.app.dto.ContactDto;
import com.example.app.entity.Contact;

import java.util.List;

public interface ContactService {
    void saveContact(ContactDto contactDto);
    Contact findByEmail(String email);
    List<ContactDto> findAllContacts();
}
