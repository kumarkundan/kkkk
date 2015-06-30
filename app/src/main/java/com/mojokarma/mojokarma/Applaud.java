package com.mojokarma.mojokarma;

/**
 * Created by kundan on 6/10/2015.
 */
public class Applaud {
    /**
     * Item text
     */
    @com.google.gson.annotations.SerializedName("content")
    private String mContent;

    /**
     * Item Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    /**
     * Item to
     */
    @com.google.gson.annotations.SerializedName("to")
    private String mTo;
    /**
     * Item from
     */
    @com.google.gson.annotations.SerializedName("from")
    private String mFrom;
    // private String mContent;




    /**
     * ToDoItem constructor
     */
    public Applaud() {

    }

    @Override
    public String toString() {
        return getContent();
    }

    /**
     * Initializes a new ToDoItem
     *
     * @param text
     *            The item text
     * @param id
     *            The item id
     * @param to
     *            The item to
     * @param from
     *            The item from
     */
    public Applaud(String text, String id, String to ,String from) {
        this.setContent(text);
        this.setId(id);
        this.setTo(to);
        this.setFrom(from);
    }

    public final void setFrom(String from) {mFrom=from;}

    public final void setTo(String to) {mTo=to;}

    /**
     * Returns the item text
     */
    public String getContent() {
        return mContent;
    }
    /**
     * Returns the item text
     */
    public String getTo() {
        return mTo;
    }
    /**
     * Returns the item text
     */
    public String getFrom() {
        return mFrom;
    }

    /**
     * Sets the item text
     *
     * @param content
     *            text to set
     */
    public final void setContent(String content) {
        mContent = content;
    }

    /**
     * Returns the item id
     */
    public String getId() {
        return mId;
    }

    /**
     * Sets the item id
     *
     * @param id
     *            id to set
     */
    public final void setId(String id) {
        mId = id;
    }


    @Override
    public boolean equals(Object o) {
        return o instanceof Applaud && ((Applaud) o).mId == mId;
    }
}
