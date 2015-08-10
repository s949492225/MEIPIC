package syiyi.com.meipic.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songlintao on 15/8/6.
 */
public class PicItem implements Parcelable {
    private String url;
    private String title;
    private String content;



    public PicItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getContent() {
        if (content == null)
            content = "";
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    protected PicItem(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
    }

    public static final Creator<PicItem> CREATOR = new Creator<PicItem>() {
        public PicItem createFromParcel(Parcel source) {
            return new PicItem(source);
        }

        public PicItem[] newArray(int size) {
            return new PicItem[size];
        }
    };
}
