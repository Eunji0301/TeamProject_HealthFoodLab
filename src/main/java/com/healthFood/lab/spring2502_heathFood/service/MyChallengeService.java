package com.healthFood.lab.spring2502_heathFood.service;

import com.healthFood.lab.spring2502_heathFood.repository.MyChallengeRepository;
import com.healthFood.lab.spring2502_heathFood.vo.MyChallengeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyChallengeService {

    private final MyChallengeRepository myChallengeRepository;

    // 생성자
    @Autowired
    public MyChallengeService(MyChallengeRepository myChallengeRepository) {
        this.myChallengeRepository = myChallengeRepository;
    }

    // 챌린지 전체 조회
    public List<MyChallengeVo> getAllMyChallenges() {
        return myChallengeRepository.getAllMyChallenges();
    }

    // 특정 챌린지 조회
    public MyChallengeVo getMyChallengeById(int uccIdx) {
        return myChallengeRepository.getMyChallengeById(uccIdx);
    }

    public List<MyChallengeVo> getMyChallengesByUser(int uIdx) {
        return myChallengeRepository.getMyChallengesByUser(uIdx);
    }

    // 챌린지 등록
    public void insertMyChallenge(MyChallengeVo myChallenge) {
        myChallengeRepository.insertMyChallenge(myChallenge);
    }

    // 챌린지 수정
    public void updateMyChallenge(MyChallengeVo myChallenge) {
        myChallengeRepository.updateMyChallenge(myChallenge);
    }

//    // 챌린지 삭제
//    public void deleteMyChallenge(int uccIdx) {
//        myChallengeRepository.deleteMyChallenge(uccIdx);
//    }
}
