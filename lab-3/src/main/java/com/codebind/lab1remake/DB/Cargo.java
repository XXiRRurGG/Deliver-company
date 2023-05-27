    package com.codebind.lab1remake.DB;

    import jakarta.persistence.*;

    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Table(name = "cargo")
    public class Cargo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        public Long id;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "listofstringsid")
        public ListOfStringsTableElement listofstrings;

    }
