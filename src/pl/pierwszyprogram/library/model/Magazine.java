package pl.pierwszyprogram.library.model;

public class Magazine extends Publication {
    private int month;
    private int day;
    private String language;

    public Magazine(String title, String publisher, String language, int year, int month, int day) {
        this.setYear(year);
        this.setTitle(title);
        this.setPublisher(publisher);
        this.month = month;
        this.day = day;
        this.language = language;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void printInfo() {
        String info = getTitle() + "; " + getPublisher() + "; " + getYear() + "-" + getMonth() + "-" + getDay()
                + "; lang:" + getLanguage();
        System.out.println(info);
    }

}