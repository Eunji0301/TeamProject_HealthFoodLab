
package com.example.healingfoodlab.service;
import com.example.healingfoodlab.repository.DiagnosisRepository;
import com.example.healingfoodlab.vo.DiagnosisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    public DiagnosisService (DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public void saveDiagnosis(DiagnosisVO diagnosisVO) {
        if (diagnosisVO.getDrDisease() == null || diagnosisVO.getDrDisease().isEmpty()) {
            diagnosisVO.setDrDisease("none");  // 기본값 설정
        }
        diagnosisRepository.insertDiagnosis(diagnosisVO);
    }






}
