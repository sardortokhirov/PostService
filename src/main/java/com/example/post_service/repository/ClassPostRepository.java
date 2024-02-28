package com.example.post_service.repository;

import com.example.post_service.model.ClassPost;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Date-2/27/2024
 * By Sardor Tokhirov
 * Time-4:14 AM (GMT+5)
 */

@Repository
public interface ClassPostRepository extends CassandraRepository<ClassPost, UUID> {
}
