package com.healthFood.lab.spring2502_heathFood.service;

import com.healthFood.lab.spring2502_heathFood.repository.ChallengeRepository;
import com.healthFood.lab.spring2502_heathFood.vo.ChallengeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    // 생성자
    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    // 챌린지 전체 조회
    public List<ChallengeVo> getAllChallenges() {
        return challengeRepository.getAllChallenges();
    }

    // 특정 챌린지 조회
    public ChallengeVo getChallengeById(int ciIdx) {
        return challengeRepository.getChallengeById(ciIdx);
    }

    // 챌린지 등록
    public void insertChallenge(ChallengeVo challenge) {
        challengeRepository.insertChallenge(challenge);
    }

    // 챌린지 수정
    public void updateChallenge(ChallengeVo challenge) {
        challengeRepository.updateChallenge(challenge);
    }

    // 챌린지 삭제
    public void deleteChallenge(int ciIdx) {
        challengeRepository.deleteChallenge(ciIdx);
    }
}
