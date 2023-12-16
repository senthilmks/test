package com.test.security;

public class PerformanceStatistic {

	  private String resourceName;
	  private long totalTime;
	  private long count;

	  public void setResourceName(String resourceName) {
	    this.resourceName = resourceName;
	  }

	  public String getResourceName() {
	    return resourceName;
	  }

	  public long getTotalTime() {
	    return totalTime;
	  }

	  public long getCount() {
	    return count;
	  }

	  public long getAverage() {
	    return totalTime / count;
	  }

	  public void addTime(long time) {
	    totalTime += time;
	    count++;
	  }
	}