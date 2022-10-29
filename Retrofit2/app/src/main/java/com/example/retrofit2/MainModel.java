package com.example.retrofit2;
import java.util.List;

import javax.xml.transform.Result;

public class MainModel {
    private List<Result> results;
    public class result{
        private int id;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
