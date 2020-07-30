package com.elsevier.elsevierauthorservice.controller;

import com.elsevier.elsevierauthorservice.payload.request.AuthorRequest;
import com.elsevier.elsevierauthorservice.payload.response.AuthorResponse;
import com.elsevier.elsevierauthorservice.service.AuthorService;
import com.elsevier.elsevierauthorservice.shared.dto.AuthorDto;
import com.elsevier.elsevierauthorservice.utils.PagedResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

  private final AuthorService authorService;

  private final ModelMapper modelMapper;

  public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
    this.authorService = authorService;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public PagedResponse<AuthorResponse> getAuthors(Pageable pageable) {
    PagedResponse<AuthorDto> authorDtoPagedResponse = authorService.getAuthors(pageable);
    List<AuthorResponse> authorResponses =
        modelMapper.map(
            authorDtoPagedResponse.getContent(),
            new TypeToken<List<AuthorResponse>>() {}.getType());

    return new PagedResponse<>(authorResponses, authorDtoPagedResponse.getPagination());
  }

  @GetMapping("/{id}")
  public AuthorResponse getAuthor(@PathVariable UUID id) {
    AuthorDto authorDto = authorService.getAuthorById(id);
    return modelMapper.map(authorDto, AuthorResponse.class);
  }

  @PostMapping
  public AuthorResponse createAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
    AuthorDto authorDto = modelMapper.map(authorRequest, AuthorDto.class);
    AuthorDto savedAuthorDto = authorService.createAuthor(authorDto);
    return modelMapper.map(savedAuthorDto, AuthorResponse.class);
  }
}
