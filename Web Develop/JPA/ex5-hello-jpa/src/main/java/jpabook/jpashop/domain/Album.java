package jpabook.jpashop.domain;


import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
public class Album extends Item{
    private String artist;
    private String etc;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
