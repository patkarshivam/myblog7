package com.myblog7.repository;

import com.myblog7.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);//building a custom method to build a query to search a particular data
    //based on a particular column of your table
}
