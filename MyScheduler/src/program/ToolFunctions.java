package program;

public class ToolFunctions {
	public static long convertToSeconds(int year, int month, int day, int hour, int minute) {
		//midnight, January 1, 1970 first leap year: 1972
		long totalTime =0;
		//years
		long time_yearCheck= year-1970;
		int leapYear= 2;
		int aYearsSeconds=365*(24*60*60); //year in seconds
		int	aLeapYearsSeconds=366*(24*60*60); //year in seconds
		while(time_yearCheck>0) {
			int subtractedTime=0;
			if (leapYear==4) {
				leapYear=1;
				subtractedTime=aLeapYearsSeconds;
			}else {
				leapYear+=1;
				subtractedTime=aYearsSeconds;
			}
			totalTime+=subtractedTime;
			time_yearCheck-=1;
		}
		//months
		long time_monthCheck= month-1;
		boolean ly= false;
		if (leapYear==1) {//running year is leap year
			ly=true;
		}
		Integer[] secondsPerMonth= getSecondsPerMonth(ly);
		int i=0;
		while (time_monthCheck>0) {
			i++;
			time_monthCheck-=1;
			totalTime+=secondsPerMonth[i];
		}		
		//days
		totalTime+=(day-1)*24*60*60;
		//hours
		totalTime+=hour*60*60;
		//minutes
		totalTime+=minute*60;
		//summer time/ winter time
		if (month<4||month>10) {
			totalTime-=60*60;
		}
		return totalTime;
		
	}
	public static Integer[] convertFromSeconds(long time_seconds) {
		Integer[] date  = new Integer[5]; //year, month , day , hour, minute
		long time_yearCheck = time_seconds;
		long time_monthCheck =0;
		long time_dayCheck =0;
		long time_hourCheck =0;
		long time_minuteCheck =0;
		int leapYear= 2;
		int aYearsSeconds=365*(24*60*60); //year in seconds
		int	aLeapYearsSeconds=366*(24*60*60); //year in seconds
		date[0]=1969; //year
		while(time_yearCheck>0) {
		//midnight, January 1, 1970 first leap year: 1972
			int subtractedTime=0;
			if (leapYear==4) {
				leapYear=1;
				subtractedTime=aLeapYearsSeconds;
			}else {
				leapYear+=1;
				subtractedTime=aYearsSeconds;
			}
			date[0]+=1;
			time_yearCheck-=subtractedTime;
			if (time_yearCheck<0) {
				time_monthCheck=time_yearCheck+subtractedTime;
			}
		}
		//month calculation
		boolean ly= false;
		if (leapYear==1) {//running year is leap year
			ly=true;
		}
		Integer[] secondsPerMonth= getSecondsPerMonth(ly);
		int i=0;
		while (time_monthCheck>0) {
			i++;
			time_monthCheck-= secondsPerMonth[i];
				if (time_monthCheck<0) {
					date[1]=i;										
				}
		}
		time_dayCheck=time_monthCheck + secondsPerMonth[i];
		//summer time/ winter time
		if (date[1]<4||date[1]>10) {
			time_dayCheck+=60*60;
		}
		//day calculation		
		date[2]=(int) (time_dayCheck/(24*60*60))+1;
		time_hourCheck=time_dayCheck%(24*60*60);
		//hour calculation
		date[3]= (int) (time_hourCheck/(60*60));
	
		time_minuteCheck= time_hourCheck%(60*60);
		//minute calculation
		date[4]= (int) (time_minuteCheck/60);
		return date;
	}
	public static String getPrettyDateString(Integer[] date) {
		String dateOutput =date[0]+"";
		for (int j = 1; j < date.length; j++) {
			if (date[j]<10) {
				dateOutput+=" 0"+date[j];
			}else {
				dateOutput+=" "+date[j];
			}
		}
		return dateOutput;
	}
	public static Integer[] getDateFromMillis(long time_millisecond) {
		return convertFromSeconds(time_millisecond/1000);
	}
	public static Integer[] getCurrentDate() {
		long time_seconds= System.currentTimeMillis()/1000;
		return convertFromSeconds(time_seconds);
	}
	public static Integer[] getSecondsPerMonth(boolean leapYear) {
		/** returns a 13 size array witch contains the seconds per month 
		 * index is equal to month number (january = index 1) */
		Integer[] secondsPerMonth= new Integer[13];
		secondsPerMonth[1]=31*24*60*60;//january
		if (leapYear) {//running year is leap year
			secondsPerMonth[2]=29*24*60*60;
		}else{
			secondsPerMonth[2]=28*24*60*60;//february
		}
		secondsPerMonth[3]=31*24*60*60;//march
		secondsPerMonth[4]=30*24*60*60;//april
		secondsPerMonth[5]=31*24*60*60;//may
		secondsPerMonth[6]=30*24*60*60;//june
		secondsPerMonth[7]=31*24*60*60;//july
		secondsPerMonth[8]=31*24*60*60;//august
		secondsPerMonth[9]=30*24*60*60;//september
		secondsPerMonth[10]=31*24*60*60;//october
		secondsPerMonth[11]=30*24*60*60;//november
		secondsPerMonth[12]=31*24*60*60;//december
		return secondsPerMonth;
	}
	public static int aDaysSeconds() {
		return 24*60*60;
	}
}
