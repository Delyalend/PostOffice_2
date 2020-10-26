package com.postalSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostOffice {

    @Id
    private int index;

    @Column(length = 250, nullable = false)
    private String address;

    @Column(length = 250, nullable = false)
    private String name;

    @OneToMany(mappedBy = "targetPostOffice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PostItem> postItemList = new ArrayList<>();

}