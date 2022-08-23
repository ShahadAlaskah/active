package com.example.active.service;


import com.example.active.exception.ApiException;
import com.example.active.model.Certificate;
import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import com.example.active.repository.CertificateRepository;
import com.example.active.repository.MembershipRepository;
import com.example.active.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final MembershipRepository membershipRepository;


    public List<Certificate> getCertificate() {
        return certificateRepository.findAll();
    }

    public void addCertificate(Certificate certificate, MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("HR"))) {
            throw new ApiException("un");
        }
        certificateRepository.save(certificate);
    }

    public void updateCertificate(Certificate certificate, Integer certificateID,MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("HR"))) {
            throw new ApiException("un");
        }
        Certificate oldCertificate=certificateRepository.findByID(certificateID);
        if (oldCertificate == null) {
            throw new ApiException("certificateID not found");
        }
        oldCertificate.setDescription(certificate.getDescription());
        oldCertificate.setName(certificate.getName());
        oldCertificate.setPicURL(certificate.getPicURL());
        oldCertificate.setUserID(certificate.getUserID());
        certificateRepository.save(oldCertificate);

    }

    public void deleteCertificate(Integer certificateID,MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("HR"))) {
            throw new ApiException("un");
        }
        Certificate certificate=certificateRepository.findByID(certificateID);
        if (certificate == null) {
            throw new ApiException("certificateID not found");
        }
        certificateRepository.delete(certificate);
    }


    public List<Certificate> findAllByUserID(Integer userID) {

        return certificateRepository.findAllByUserID(userID);
    }
}
