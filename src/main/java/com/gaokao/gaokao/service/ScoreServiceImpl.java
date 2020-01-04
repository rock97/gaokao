package com.gaokao.gaokao.service;

import com.gaokao.gaokao.entity.Score;
import com.gaokao.gaokao.mapper.ScoreDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;
    @Override
    public void insertList(List<Score> list) {
        scoreDao.insertList(list);
    }

    @Override
    public void update(String name, int code) {
        scoreDao.update(name, code);
    }
}
