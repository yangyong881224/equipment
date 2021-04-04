package com.equipment.system.service;

import com.equipment.system.domain.Statistics;

import java.util.List;

/**
 * @IStatisticsService:
 * @author: Yayo
 * @date: 2021/4/4 22:20
 */
public interface IStatisticsService {

    List<Statistics> exportStatistics();


}
