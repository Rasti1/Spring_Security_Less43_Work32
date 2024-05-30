package com.example.app.service.impl;

import com.example.app.dto.ContactDto;
import com.example.app.entity.Contact;
import com.example.app.entity.Role;
import com.example.app.repository.ContactRepository;
import com.example.app.repository.RoleRepository;
import com.example.app.service.ContactService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ContactServiceImpl(ContactRepository contactRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.contactRepository = contactRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhone(contactDto.getPhone());
        contact.setPassword(passwordEncoder.encode(contactDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        contact.setRoles(List.of(role));
        contactRepository.save(contact);
    }

    @Override
    public Contact findByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    @Override
    public List<ContactDto> findAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private ContactDto convertEntityToDto(Contact contact) {
        return new ContactDto(contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail(), null);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
