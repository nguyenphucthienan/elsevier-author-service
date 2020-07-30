package com.elsevier.elsevierauthorservice.service.impl;

import com.elsevier.elsevierauthorservice.domain.Author;
import com.elsevier.elsevierauthorservice.repository.AuthorRepository;
import com.elsevier.elsevierauthorservice.service.AuthorService;
import com.elsevier.elsevierauthorservice.shared.dto.AuthorDto;
import com.elsevier.elsevierauthorservice.utils.PagedResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  private final ModelMapper modelMapper;

  public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
    this.authorRepository = authorRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public PagedResponse<AuthorDto> getAuthors(Pageable pageable) {
    Page<Author> authorPage = authorRepository.findAll(pageable);

    List<AuthorDto> authorDtos =
        modelMapper.map(authorPage.getContent(), new TypeToken<List<AuthorDto>>() {}.getType());

    return new PagedResponse<>(
        authorDtos,
        authorPage.getNumber(),
        authorPage.getSize(),
        authorPage.getTotalElements(),
        authorPage.getTotalPages(),
        authorPage.isFirst(),
        authorPage.isLast());
  }

  @Override
  public AuthorDto getAuthorById(UUID id) {
    Author author =
        authorRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Author with ID " + id + "not found"));

    return modelMapper.map(author, AuthorDto.class);
  }

  @Override
  public AuthorDto createAuthor(AuthorDto authorDto) {
    Author author = modelMapper.map(authorDto, Author.class);
    Author savedAuthor = authorRepository.save(author);
    return modelMapper.map(savedAuthor, AuthorDto.class);
  }
}
