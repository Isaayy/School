package pl.edu.pwsztar;

import java.time.Month;

public class Calendar {
    int day;
    Months month ;
    int year;

    public String toString() {
            return (day+"/"+month.getName()+"/"+year);
    }

    Calendar(int d,int m, int y){
        if (d > month.getMonth(m).getDays() || d < 1 ) {
            throw new dataError("Wrong days number");
        }
        else {
            this.day = d;
            this.month = Months.getMonth(m);
            this.year = y ;
        }

    }

    public void weekForward() {
        if ((this.day + 7) > month.getDays()){
            if ( (month.getName() == "February") && (year % 4 == 0 && ((year%100==0&&year%400==100)  ||  year % 100 != 0 ) )) { // leap year
                this.day = (7 - (this.month.getDays()+1 - this.day));
            }
            else {
                this.day = (7 - (this.month.getDays() - this.day));
            }

            if (this.month.getOrder()+1 > 12) {
                month = Months.getMonth(1);
                year+=1;
            }
            else
                month = Months.getMonth(this.month.getOrder()+1);
        }
        else
            this.day += 7;
    }

    public void weekBackward() {
        if (this.day-7 < 1){
            if (this.month.getOrder()-1 < 1) {
                month = Months.getMonth(12);
                year-=1;
            }
            else
                month = Months.getMonth(this.month.getOrder()-1);
            if ( (month.getName() == "February") && (year % 4 == 0 && ((year%100==0&&year%400==100)  ||  year % 100 != 0 ) )) { // leap year
                this.day = (this.month.getDays()+1) - (7-this.day);
            }
            else {
                this.day = this.month.getDays() - (7-this.day);
            }
        }
        else {
            this.day -= 7;
        }
    }

    public boolean goForward(Calendar referenceDate){
        if (this.year > referenceDate.year)
            return true;
        else if (this.year == referenceDate.year && this.month.getOrder() > referenceDate.month.getOrder() )
            return true;
        else if (this.year == referenceDate.year && this.month.getOrder() == referenceDate.month.getOrder() && this.day > referenceDate.day )
            return true;
        else
            return false;
    }

    public String getDayName() {
        Calendar referenceDate = new Calendar(30,11,2020); // > Monday
        int diff = -1;
        if (goForward(referenceDate)){

            while(referenceDate.day<this.day || (referenceDate.month.getName() != this.month.getName()  || referenceDate.year != this.year))
            {
                referenceDate.weekForward();
                if(referenceDate.year > this.year || (referenceDate.year == this.year && referenceDate.month.getOrder() > this.month.getOrder())){
                    referenceDate.weekBackward();
                    diff = (this.day - referenceDate.day) + 1;
                    break;
                }

            }
        }
        else {
            while(referenceDate.day>this.day || (referenceDate.month.getName() != this.month.getName()  || referenceDate.year != this.year))
            {
                referenceDate.weekBackward();
            }
            referenceDate.weekForward();
            if (referenceDate.day < this.day){
                diff = (referenceDate.day + (this.month.getDays() - this.day))  ;
            }
        }
        if (diff == -1){
            diff = referenceDate.day - this.day;

        }

        switch (diff){
            case 0:
                break;
            case 1 :
                return "Sunday";
            case 2:
                return  "Saturday";
            case 3:
                return "Friday";
            case 4:
                return "Thursday";
            case 5:
                return "Wednesday";
            case 6:
                return "Tuesday";
        }
        return "Monday";

    }

    public static final int DATEFORMAT_LONG = 56524202;
    public static final int DATEFORMAT_SHORT = 23562690;
    public static final int DATEFORMAT_CLASSIC = 12526392;
    public static final int DATEFORMAT_ROMAN = 62697301;

    public String getDateInFormat(int dateFormat){

        String str = "";

        switch (dateFormat){

            case 56524202:
                str = this.getDayName() + ", " + this.day + " " + this.month.getName() + " " + this.year;
                break;
            case 23562690:
                str = this.day + " " + this.month.getName() + " " + this.year;
                break;
            case 12526392:
                str = this.getDayName().substring(0,2) + "., " + this.day + "-" + this.month.getName().substring(0,3) + "-" + this.year;
                break;
            case 62697301:
                if (this.day<10)
                    str += "0";
                str += this.day + "." + this.month.getRomanName() + "." + this.year;
        }

        return str;

    }




}
