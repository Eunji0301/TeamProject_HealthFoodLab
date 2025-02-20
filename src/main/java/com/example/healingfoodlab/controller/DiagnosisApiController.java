/*


package com.example.healingfoodlab.controller;


import com.example.healingfoodlab.service.DiagnosisService;
import com.example.healingfoodlab.vo.DiagnosisVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnosis")  // ✅ API 경로 지정
public class DiagnosisApiController {

    private final DiagnosisService diagnosisService;

    public DiagnosisApiController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitDiagnosis(@RequestBody DiagnosisVO diagnosisVO) {
        System.out.println("진단 정보 수신: " + diagnosisVO);
        System.out.println("입력된 drHeight :" + diagnosisVO.getDrHeight());
        System.out.println("입력된 drWeight : " + diagnosisVO.getDrWeight());
        System.out.println("입력된 drDisease : " + diagnosisVO.getDrDisease());
        System.out.println("입력된 drLiquor : " + diagnosisVO.getDrLiquor());
        System.out.println("입력된 drActivity : " + diagnosisVO.getDrActivity());
        diagnosisService.saveDiagnosis(diagnosisVO);
        return ResponseEntity.ok("진단 정보 저장 완료");
    }

}

*/
