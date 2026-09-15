package com.snehasingh.backend.repository;

import com.snehasingh.backend.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository automatically save(), findById(), findAll() jaise functions de deta hai
// humein khud SQL likhne ki zaroorat nahi
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}