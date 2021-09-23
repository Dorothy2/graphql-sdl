package com.drifai.graphqlsdl.service.impl;

import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.exception.ResourceNotFoundException;
import com.drifai.graphqlsdl.mapper.AuthorMapper;
import com.drifai.graphqlsdl.model.Author;
import com.drifai.graphqlsdl.repository.AuthorRepository;
import com.drifai.graphqlsdl.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> all = authorRepository.findAll();
        return all.stream()
                .map(authorMapper::convertAuthorToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        Author author =authorOptional.orElseThrow(() -> new ResourceNotFoundException("Author does not exist"));
        return authorMapper.convertAuthorToDto(author);
    }

    @Override
    public UUID createAuthor(AuthorDto authorDto) {
        Author author = authorMapper.convertDtoToAuthor(authorDto);
        Author createdAuthor = authorRepository.saveAndFlush(author);
        return createdAuthor.getId();
    }

    @Override
    public Map<UUID, AuthorDto> getAllAuthorsByIds(Set<UUID> authorIds) {
        List<Author> list = authorRepository.findAllById(authorIds);
        return list.stream()
                .map(authorMapper::convertAuthorToDto)
                // toMap takes 2 lambda expressions, one for key, one for author object
                //.collect(Collectors.toMap(author -> author.getId(), author -> author));
                .collect(Collectors.toMap(AuthorDto::getId, author -> author));
    }
}
