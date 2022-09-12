package com.example.demo0.models;

import javax.persistence.*;



    @Entity
    @Table(name = "users")
    public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @OneToOne(optional = true, cascade = CascadeType.ALL)
        @JoinColumn(name = "addres_id")

        private Addres addres;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Addres getAddres() {
            return addres;
        }

        public void setAddres(Addres addres) {
            this.addres = addres;
        }

        public Person(String name, Addres addres) {
            this.name = name;
            this.addres = addres;
        }

        public Person() {
        }
    }

