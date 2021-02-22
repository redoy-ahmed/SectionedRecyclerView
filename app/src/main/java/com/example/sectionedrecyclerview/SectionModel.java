package com.example.sectionedrecyclerview;

import java.util.ArrayList;

public class SectionModel {

    private String sectionLabel;
    private ArrayList<String> itemArrayList;
    private boolean isExpanded;

    public SectionModel(String sectionLabel, ArrayList<String> itemArrayList, boolean isExpanded) {
        this.sectionLabel = sectionLabel;
        this.itemArrayList = itemArrayList;
        this.isExpanded = isExpanded;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }

    public ArrayList<String> getItemArrayList() {
        return itemArrayList;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
