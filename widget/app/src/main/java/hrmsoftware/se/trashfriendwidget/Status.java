package hrmsoftware.se.trashfriendwidget;

public class Status {

    enum Weather {
        SUNNY, SUNANDCLOUD, CLOUD, RAIN
    }

    private int busyStatus;
    private int clowdy;
    private int rainFall;
    private int count;

    public int getBusyStatus() {
        return busyStatus;
    }

    public void setBusyStatus(int busyStatus) {
        this.busyStatus = busyStatus;
    }

    public int getClowdy() {
        return clowdy;
    }

    public void setClowdy(int clowdy) {
        this.clowdy = clowdy;
    }

    public int getRainFall() {
        return rainFall;
    }

    public void setRainFall(int rainFall) {
        this.rainFall = rainFall;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    Weather getWeather() {
        if (rainFall > 0) {
            return Weather.RAIN;
        }
        else if (clowdy == 0) {
            return Weather.SUNNY;
        }
        else if (clowdy >= 7) {
            return Weather.CLOUD;
        }

        return Weather.SUNANDCLOUD;
    }
}
