package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.Club;
import com.example.active.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    public List<Club> getClub() {
        return clubRepository.findAll();
    }

    public void addClub(Club club) {
        clubRepository.save(club);
    }

    public void updateClub(Club club, Integer clubID) {
        Club oldClub=clubRepository.findClubByID(clubID);
        if (oldClub == null) {
            throw new ApiException("clubID not found");
        }
        oldClub.setDescription(club.getDescription());
        oldClub.setName(club.getName());
        oldClub.setCollageID(club.getCollageID());
        clubRepository.save(oldClub);

    }

    public void deleteClub(Integer clubID) {
        Club club=clubRepository.findClubByID(clubID);
        if (club == null) {
            throw new ApiException("clubID not found");
        }
        clubRepository.delete(club);
    }

    public List<Club> findAllByCollageID(Integer collageID) {
        return clubRepository.findAllByCollageID(collageID);
    }

    public Club findClubByID(Integer clubID) {
        Club club=clubRepository.findClubByID(clubID);
        if (club == null) {
            throw new ApiException("clubID not found");
        }
        return club;
    }
}
