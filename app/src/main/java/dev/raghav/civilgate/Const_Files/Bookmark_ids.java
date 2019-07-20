package dev.raghav.civilgate.Const_Files;

public class Bookmark_ids {
    int testid;
    String name;
    String Bookmarks_id;



    public Bookmark_ids(int testid, String name, String bookmarkId) {
        this.testid = testid;
        this.name = name;
        this.Bookmarks_id = bookmarkId;
    }

    public String getBookmarks_id() {
        return Bookmarks_id;
    }

    public void setBookmarks_id(String bookmarks_id) {
        this.Bookmarks_id = bookmarks_id;
    }
    public int getTestid() {
        return testid;
    }

    public void setTestid(int testid) {
        this.testid = testid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
