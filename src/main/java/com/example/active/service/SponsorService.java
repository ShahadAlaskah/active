package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.Sponsor;
import com.example.active.repository.SponsorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorService {
    private final SponsorRepository sponsorRepository;

    public List<Sponsor> getSponsors() {
        return sponsorRepository.findAll();
    }

    public void addSponsor(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
    }

    public void updateSponsor(Sponsor sponsor, Integer sponsorID) {
        Sponsor oldSponsor=sponsorRepository.findByID(sponsorID);
        if (oldSponsor == null) {
            throw new ApiException("sponsorID not found");
        }
        oldSponsor.setDescription(sponsor.getDescription());
        oldSponsor.setName(sponsor.getName());
        sponsorRepository.save(oldSponsor);

    }

    public void deleteSponsor(Integer sponsorID) {
        Sponsor sponsor=sponsorRepository.findByID(sponsorID);
        if (sponsor == null) {
            throw new ApiException("sponsorID not found");
        }
        sponsorRepository.delete(sponsor);
    }
}
