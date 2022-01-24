package com.mannan.demoapp.Model.PostPackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Post implements Comparable<Post> {
    private Long id;
    private String name;
    private String image;
    private String title;
    private String description;
    private Long accountPCN;
    private String date;

    public Post(String title, String description, Long accountPCN, String date) {
        this.title = title;
        this.description = description;
        this.accountPCN = accountPCN;
        this.date = date;
    }

    @Override
    public int compareTo(Post o) {
        if (getId() == null || o.getId() == null) {
            return 0;
        }
        return getId().compareTo(o.getId());
    }
}
