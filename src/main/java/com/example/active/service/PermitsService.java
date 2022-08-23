package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.Permits;
import com.example.active.repository.PermitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermitsService {
    private final PermitsRepository permitsRepository;

    public List<Permits> getPermits() {
        return permitsRepository.findAll();
    }

    public void addPermits(Permits permits) {
        permitsRepository.save(permits);
    }

    public void updatePermits(Permits permits, Integer PermitsID) {
        Permits oldPermits=permitsRepository.findByID(PermitsID);
        if (oldPermits == null) {
            throw new ApiException("PermitsID not found");
        }
        oldPermits.setDescription(permits.getDescription());
        oldPermits.setName(permits.getName());
        oldPermits.setTitle(permits.getTitle());
        permitsRepository.save(oldPermits);

    }

    public void deletePermits(Integer PermitsID) {
        Permits permits=permitsRepository.findByID(PermitsID);
        if (permits == null) {
            throw new ApiException("PermitsID not found");
        }
        permitsRepository.delete(permits);
    }
}
