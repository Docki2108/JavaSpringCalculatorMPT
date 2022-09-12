package com.example.demo0.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Coca   {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;
        @ManyToMany
        @JoinTable (name="coca_shop",
                joinColumns=@JoinColumn (name="coca_id"),
                inverseJoinColumns=@JoinColumn(name="shop_id"))
        private List<Shop> shops;

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

        public List<Shop> getShops() {
            return shops;
        }

        public void setShops(List<Shop> shops) {
            this.shops = shops;
        }

}
