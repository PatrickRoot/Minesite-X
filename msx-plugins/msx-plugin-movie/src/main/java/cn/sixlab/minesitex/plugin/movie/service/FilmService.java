/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/10/26 11:14
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.minesitex.plugin.movie.service;

import cn.sixlab.minesitex.bean.movie.entity.MsxFilm;
import cn.sixlab.minesitex.data.movie.FilmRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class FilmService {
    private static Logger logger = LoggerFactory.getLogger(ShowService.class);
    
    @Autowired
    private FilmRepo filmRepo;
    
    public MsxFilm addFilm(MsxFilm film) {
        film.setInsertTime(Timestamp.from(Instant.now()));
        filmRepo.save(film);
        
        return film;
    }
    
    public MsxFilm updateFilm(MsxFilm film) {
        MsxFilm oldFilm = filmRepo.findOne(film.getId());
        oldFilm.setMovieName(film.getMovieName());
        oldFilm.setProduceYear(film.getProduceYear());
        oldFilm.setDirector(film.getDirector());
        oldFilm.setDoubanKey(film.getDoubanKey());
        oldFilm.setDoubanScore(film.getDoubanScore());
        oldFilm.setRemark(film.getRemark());
        oldFilm.setCinema(film.getCinema());
        oldFilm.setViewDate(film.getViewDate());
        return filmRepo.save(oldFilm);
    }
    
    public List<MsxFilm> searchFilm(String keyword) {
        List<MsxFilm> filmList;
        
        if (StringUtils.isEmpty(keyword)) {
            filmList = filmRepo.findAll();
        } else {
            filmList = filmRepo.queryByKeyword(keyword);
        }
        
        return filmList;
    }
    
    public MsxFilm fetchFilm(Integer id) {
        return filmRepo.findOne(id);
    }
    
    public List<MsxFilm> fetchRecentFilm(Integer num) {
        List<MsxFilm> filmList;
        
        if (num == null) {
            num = 10;
        }
        
        Page<MsxFilm> filmPage = filmRepo.findAll(new PageRequest(0, num,
                new Sort(Sort.Direction.DESC, "viewDate")));
    
        filmList = filmPage.getContent();
    
        return filmList;
    }
    
    public List<String> fetchCinema() {
        List<String> cinemaList = filmRepo.queryCinemas();
        return cinemaList;
    }
    
    public List<MsxFilm> fetchDb() {
        List<MsxFilm> filmList = filmRepo.queryTop10ByDoubanKeyIsNullOrderByIdDesc();
        
        return filmList;
    }
}
