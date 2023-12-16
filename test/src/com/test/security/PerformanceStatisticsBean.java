package com.test.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@javax.inject.Named("performanceStatistics")
//@javax.enterprise.context.ApplicationScoped
public class PerformanceStatisticsBean {

  private Map<String, PerformanceStatistic> statistics = new HashMap<>();

  public void logTime(String resourceName, long time) {
    PerformanceStatistic statistic = statistics.get(resourceName);
    if (statistic == null) {
      statistic = new PerformanceStatistic();
      statistic.setResourceName(resourceName);
      statistics.put(resourceName, statistic);
    }
    statistic.addTime(time);
  }

  public List<PerformanceStatistic> getStatistics() {
    return new ArrayList<>(statistics.values());
  }
}