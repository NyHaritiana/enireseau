package com.enireseau.enireseau.repository;

import com.enireseau.enireseau.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
