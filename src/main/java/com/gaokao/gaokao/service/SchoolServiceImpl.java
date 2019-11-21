package com.gaokao.gaokao.service;

import com.gaokao.gaokao.entity.School;
import com.gaokao.gaokao.mapper.SchoolDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDao schoolDao;
    @Override
    public void insertList(List<School> list) {
        schoolDao.insertList(list);
    }
}
