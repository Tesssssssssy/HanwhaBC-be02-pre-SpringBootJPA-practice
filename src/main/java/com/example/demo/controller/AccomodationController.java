package com.example.demo.controller;

import com.example.demo.model.GetAccomodationRes;
import com.example.demo.model.PatchAccomodationReq;
import com.example.demo.model.PostAccomodationReq;
import com.example.demo.service.AccomodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accomodation")
public class AccomodationController {
    private final AccomodationService accomodationService;    // 제어의 역전 사례 (이제 스프링이 객체 생성 및 관리)

    public AccomodationController(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Object> createAccomodation(PostAccomodationReq postAccomodationReq) {
        accomodationService.createAccomodation(postAccomodationReq);
        return ResponseEntity.ok().body("숙소 생성 성공");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/find")
    public ResponseEntity<Object> findAccomodation(int id) {
        GetAccomodationRes accomodationRes = accomodationService.findAccomodation(id);
        return ResponseEntity.ok().body(accomodationRes);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/findList")
    public ResponseEntity<Object> findAccomodationList() {
        List<GetAccomodationRes> accomodationListRes = accomodationService.findAccomodationList();
        return ResponseEntity.ok().body(accomodationListRes);
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity<Object> updateAccomodation(PatchAccomodationReq patchAccomodationReq, int id) {
        accomodationService.updateAccomodation(patchAccomodationReq, id);
        return ResponseEntity.ok().body(patchAccomodationReq);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity<Object> deleteAccomodation(int id) {
        accomodationService.deleteAccomodation(id);
        return ResponseEntity.ok().body("숙소 삭제 성공");
    }
}
