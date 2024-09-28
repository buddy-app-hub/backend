package org.buddy.backend.models;

import java.util.Date;

public class RecommendedBuddy {
    private Buddy buddy;
    private int score;
    private int distanceToKM;
    private boolean isDismissed;
    private Date dateDismissed;


    public Buddy getBuddy() {
        return buddy;
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDistanceToKM() {
        return distanceToKM;
    }

    public void setDistanceToKM(int distanceToKM) {
        this.distanceToKM = distanceToKM;
    }

    public boolean getIsDismissed() {
        return isDismissed;
    }

    public void setIsDismissed(boolean isDismissed) {
        this.isDismissed = isDismissed;
    }

    public Date getDateDismissed() {
        return dateDismissed;
    }

    public void setDateDismissed(Date dateDismissed) {
        this.dateDismissed = dateDismissed;
    }

    @Override
    public String toString() {
        return "RecommendedBuddy{" +
                ", buddy=" + buddy +
                ", score=" + score +
                ", distanceToKM=" + distanceToKM +
                ", isDismissed=" + isDismissed +
                ", dateDismissed=" + dateDismissed +
                '}';
    }
}
