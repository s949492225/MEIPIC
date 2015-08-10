package syiyi.com.meipic.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songlintao on 15/8/6.
 */
public class PageItem implements Parcelable {
    private String url;
    private String title;

    public PageItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
    }

    protected PageItem(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<PageItem> CREATOR = new Parcelable.Creator<PageItem>() {
        public PageItem createFromParcel(Parcel source) {
            return new PageItem(source);
        }

        public PageItem[] newArray(int size) {
            return new PageItem[size];
        }
    };
}
