package syiyi.com.meipic.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songlintao on 15/8/5.
 */
public class DirItem implements Parcelable {
    private String imgeUrl;
    private String itemPageURL;
    private String nextPageURL;
    private String title;
    private String count;

    public DirItem(String imgeUrl, String itemPageURL, String nextPageURL, String title,String count) {
        this.imgeUrl = imgeUrl;
        this.itemPageURL = itemPageURL;
        this.nextPageURL = nextPageURL;
        this.title = title;
        this.count=count;
    }

    public String getCount() {
        if (count == null)
            count = "";
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemPageURL() {
        return itemPageURL;
    }

    public void setItemPageURL(String itemPageURL) {
        this.itemPageURL = itemPageURL;
    }

    public String getImgeUrl() {
        return imgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public String getNextPageURL() {
        return nextPageURL;
    }

    public void setNextPageURL(String nextPageURL) {
        this.nextPageURL = nextPageURL;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imgeUrl);
        dest.writeString(this.itemPageURL);
        dest.writeString(this.nextPageURL);
    }

    protected DirItem(Parcel in) {
        this.imgeUrl = in.readString();
        this.itemPageURL = in.readString();
        this.nextPageURL = in.readString();
    }

    public static final Parcelable.Creator<DirItem> CREATOR = new Parcelable.Creator<DirItem>() {
        public DirItem createFromParcel(Parcel source) {
            return new DirItem(source);
        }

        public DirItem[] newArray(int size) {
            return new DirItem[size];
        }
    };
}
