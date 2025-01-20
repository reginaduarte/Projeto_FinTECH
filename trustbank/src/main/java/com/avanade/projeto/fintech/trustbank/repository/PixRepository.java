package com.avanade.projeto.fintech.trustbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanade.projeto.fintech.trustbank.entities.Pix;

public interface PixRepository extends JpaRepository <Pix, Integer> {

}
