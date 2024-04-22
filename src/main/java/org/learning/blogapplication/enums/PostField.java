package org.learning.blogapplication.enums;

import java.util.Locale;

public enum PostField {
    TITLE("title"),POSTID("postId");

    private String fieldName;

    private PostField(String fieldName)
    {
        this.fieldName = fieldName;
    }
    public static PostField fromString(String value) {

        try {
            return PostField.valueOf(value.toUpperCase(Locale.US));
        } catch (Exception e) {
            StringBuilder postFields = new StringBuilder();
            for(PostField postField : PostField.values())
            {
                postFields.append(" " +postField.getFieldName()+",");
            }
            throw new IllegalArgumentException(String.format(
                    "Invalid value '%s' for fieldName given; Has to be %s (case insensitive)", value,postFields), e);
        }
    }

    public String getFieldName() {
        return fieldName;
    }
}
