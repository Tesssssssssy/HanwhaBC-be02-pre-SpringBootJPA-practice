package com.example.demo.service;

import com.example.demo.AccomodationDao;
import com.example.demo.model.GetAccomodationRes;
import com.example.demo.model.PatchAccomodationReq;
import com.example.demo.model.PostAccomodationReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccomodationService {

    private final AccomodationDao accomodationDao;

    public AccomodationService(AccomodationDao accomodationDao) {
        this.accomodationDao = accomodationDao;
    }

    public void createAccomodation(PostAccomodationReq postAccomodationReq) {
        accomodationDao.createAccomodation(postAccomodationReq);
    }

    public GetAccomodationRes findAccomodation(int id) {
        return accomodationDao.findAccomodation(id);
    }

    public List<GetAccomodationRes> findAccomodationList() {
        return accomodationDao.findAccomodationList();
    }


    public void updateAccomodation(PatchAccomodationReq patchAccomodationReq, int id) {
        accomodationDao.updateAccomodation(patchAccomodationReq, id);
    }


    public void deleteAccomodation(int id) {
        accomodationDao.deleteAccomodation(id);
    }
}
