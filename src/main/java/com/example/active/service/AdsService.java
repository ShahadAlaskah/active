package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.Ads;
import com.example.active.model.MyUser;
import com.example.active.repository.AdsRepository;
import com.example.active.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsService {
    private final AdsRepository adsRepository;

    public List<Ads> getAds() {
        return adsRepository.findAll();
    }

    public void addAds(Ads ads, MyUser myUser) {
        ads.setClubID(myUser.getClubID());
        adsRepository.save(ads);
    }

    public void updateAds(Ads ads, Integer adsID,MyUser myUser) {
        Ads oldAds=adsRepository.findByClubIDAndID(myUser.getClubID(), adsID);
        if (oldAds == null) {
            throw new ApiException("adsID not found");
        }
        oldAds.setDescription(ads.getDescription());
        oldAds.setFiles(ads.getFiles());
        oldAds.setTitle(ads.getTitle());
        adsRepository.save(oldAds);

    }

    public void deleteAds(Integer adsID,MyUser myUser) {
        Ads ads=adsRepository.findByClubIDAndID(myUser.getClubID(), adsID);
        if (ads == null) {
            throw new ApiException("adsID not found");
        }
        adsRepository.delete(ads);
    }
}
