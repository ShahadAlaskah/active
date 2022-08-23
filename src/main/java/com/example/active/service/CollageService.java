package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.Club;
import com.example.active.model.Collage;
import com.example.active.repository.CollageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollageService {
    private final CollageRepository collageRepository;

    public List<Collage> getCollage() {
        return collageRepository.findAll();
    }

    public void addCollage(Collage collage ) {
        collageRepository.save(collage);
    }

    public void updateCollage(Collage collage, Integer CollageID) {
        Collage oldCollage=collageRepository.findByID(CollageID);
        if (oldCollage == null) {
            throw new ApiException("CollageID not found");
        }
        oldCollage.setName(collage.getName());
        collageRepository.save(oldCollage);

    }

    public void deleteCollage(Integer CollageID) {
        Collage collage=collageRepository.findByID(CollageID);
        if (collage == null) {
            throw new ApiException("CollageID not found");
        }
        collageRepository.delete(collage);
    }
}
