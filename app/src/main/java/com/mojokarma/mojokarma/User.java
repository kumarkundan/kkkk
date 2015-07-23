package com.mojokarma.mojokarma;

/**
 * Created by kundan on 7/4/2015.
 */
public class User {
    /**
     * Item text
     */
    @com.google.gson.annotations.SerializedName("name")
    private String mName;

    @com.google.gson.annotations.SerializedName("designation")
    private String mDesignation;

    /**
     * Item Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;




    /**
     * ToDoItem constructor
     */
    public User() {

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
     * @param designation
     */
    public User(String text, String id ,String designation) {
        this.setContent(text);
        this.setId(id);
        this.setDesignation(designation);
    }

    /**
     * Returns the item text
     */
    public String getContent() {
        return mName;
    }

    public String getDesignation() {
        return mDesignation;
    }

    /**
     * Sets the item text
     *
     * @param content
     *            text to set
     */
    public final void setContent(String content) {
        mName = content;
    }

    public final void setDesignation(String designation) {
        mDesignation = designation;
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
        return o instanceof User && ((User) o).mId == mId;
    }
}

