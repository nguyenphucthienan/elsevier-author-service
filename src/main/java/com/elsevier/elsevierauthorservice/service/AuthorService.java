package com.elsevier.elsevierauthorservice.service;

import com.elsevier.elsevierauthorservice.shared.dto.AuthorDto;
import com.elsevier.elsevierauthorservice.utils.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AuthorService {

  PagedResponse<AuthorDto> getAuthors(Pageable pageable);

  AuthorDto getAuthorById(UUID id);

  AuthorDto createAuthor(AuthorDto authorDto);
}
