package com.postalSystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePostItem type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPostItem status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private PostOffice targetPostOffice;

    @OneToMany(mappedBy = "postItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<History> histories;

    @Column(nullable = false)
    private String nameRecipient;

    @Column(nullable = false)
    private String addressRecipient;

    @Column(nullable = true)
    private int nextPostOfficeIndex;

    @Column(nullable = true)
    private int curPostOfficeIndex;

}

