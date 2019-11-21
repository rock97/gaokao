package com.gaokao.gaokao.service;

import com.gaokao.gaokao.entity.Score;
import java.util.List;

public interface ScoreService {
    void insertList(List<Score> list);
    void update(String name,int code);

}
