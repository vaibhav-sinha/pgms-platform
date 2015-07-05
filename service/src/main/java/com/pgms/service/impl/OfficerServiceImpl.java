package com.pgms.service.impl;

import com.pgms.service.api.OfficerService;
import com.pgms.service.repository.OfficerRepository;
import com.pgms.shared.model.AccountStatus;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.Officer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class OfficerServiceImpl implements OfficerService {

    @Autowired
    OfficerRepository officerRepository;

    @Override
    public Officer createOfficer(Officer officer) {
        officer.setEntryStatus(EntryStatus.ACTIVE);
        officer.setAccountStatus(AccountStatus.ACTIVE);
        officer.setSalt(RandomStringUtils.randomAlphanumeric(8));
        officer.setEncryptedPassword(encrypt(officer.getSalt(), officer.getEncryptedPassword()));
        return officerRepository.save(officer);
    }

    @Override
    public Officer saveOfficer(Officer officer) {
        return officerRepository.save(officer);
    }

    @Override
    public Officer getOfficer(Long id) {
        return officerRepository.findOne(id);
    }

    @Override
    public List<Officer> getAllOfficer() {
        return officerRepository.findAll();
    }

    @Override
    public List<Officer> findOfficersByName(String name, int page, int count) {
        PageRequest pageRequest = new PageRequest(page, count, Sort.Direction.DESC, name);
        return officerRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Officer> getAllActiveOfficer() {
        return officerRepository.findByAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public Officer login(String username, String password) {
        Officer officer = officerRepository.findByUsername(username);
        if(officer == null || officer.getEntryStatus() != EntryStatus.ACTIVE || officer.getAccountStatus() != AccountStatus.ACTIVE) {
            return null;
        }
        String encryptedPassword = encrypt(officer.getSalt(), password);
        boolean isPasswordCorrect = encryptedPassword.equals(officer.getEncryptedPassword());
        if(!isPasswordCorrect) {
            return null;
        }
        else {
            return officer;
        }
    }

    private String encrypt(String salt, String password) {
        try {
            String encryptedPassword = Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest((password + salt).getBytes()));
            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            //Should never happen
            return "";
        }
    }
}
