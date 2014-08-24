package com.mygdx.HVSS;

public class Timer implements Runnable {
	private static final boolean NORMAL = false;
	private static final boolean BACK = true;
	
	private Integer minutes, seconds, thousandths;
	private String m, s, t;
	private Thread thread;
	private boolean timerActive;
	private boolean mode;
	
	public Timer() {
		minutes = seconds = thousandths = 0;
		m = "";
		s = "";
		t = "";
		mode = NORMAL;
	}
	public Timer(int minutes) {
		this.minutes = minutes;
		seconds = thousandths = 0;
		m = "";
		s = "";
		t = "";
		mode = BACK;
	}
	
	public void run() {
		if(mode == NORMAL) {
			try {
				while(timerActive) {
					Thread.sleep(4);
					thousandths += 4;
					if(thousandths == 1000) {
						thousandths = 0;
						seconds += 1;
						if(seconds == 60) {
							seconds = 0;
							minutes++;
						}
					}
				}
			} catch(Exception e) {}
		}
		else if(mode == BACK) {
			try {
				while(timerActive) {
					if(minutes == 0)
						timerActive = false;
					else {
						Thread.sleep(4);
						thousandths -= 4;
						if(thousandths < 0) {
							thousandths = 999;
							seconds -= 1;
							if(seconds < 0) {
								seconds = 59;
								minutes--;
							}
						}
					}
				}
			} catch(Exception e) {}
		}
	}
	
	public void reset() {
		minutes = seconds = thousandths = 0;
		m = "";
		s = "";
		t = "";
		start();
	}

	public void start() {
		timerActive = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop(){
		timerActive = false;
	}
	
	public String getTime() {
		if(minutes < 10) m = "0" + minutes;
		else m = minutes.toString();
		if(seconds < 10) s = "0" + seconds;
		else s = seconds.toString();
		return m + ":" + s;
	}
	public String getTimeT() {
		if(minutes < 10) m = "0" + minutes;
		else m = minutes.toString();
		if(seconds < 10) s = "0" + seconds;
		else s = seconds.toString();
		if(thousandths < 10) t = "00" + thousandths;
		else if(thousandths < 100) t = "0" + thousandths;
		else t = thousandths.toString();
		return m + ":" + s + ":" + t;
	}
	
	public boolean isOver() {
		if(mode == BACK && minutes == 0)
			return true;
		else
			return false;
	}
}