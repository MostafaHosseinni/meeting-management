package ir.mine.project.base.rest;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import ir.mine.project.base.domin.BaseEntity;
import ir.mine.project.base.dozer.DozerConvertorGenericMethod;
import ir.mine.project.base.dto.BaseDTO;
import ir.mine.project.base.service.BaseService;
import ir.mine.project.web.rest.util.HeaderUtil;
import ir.mine.project.web.rest.util.PaginationUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseRestFulServiceSecure<E extends BaseEntity<PK>, D extends BaseDTO<PK>, PK extends Serializable, Service extends BaseService<E, PK>>
		extends DozerConvertorGenericMethod<E, D> {

	public final Service baseService;
	public String ENTITY_NAME;

	public BaseRestFulServiceSecure(Service baseService) {
		this.baseService = baseService;
	}

	@PostMapping
	@Timed
	public ResponseEntity<D> create(@RequestBody D d) throws URISyntaxException {
		if (d.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entity cannot already have an ID"))
					.body(null);
		}

		E result = baseService.save(convertDTOToEntity(d));
		return ResponseEntity.created(new URI("*/create/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(convertEntityToDTO(result));

	}

	@PutMapping
	@Timed
	public ResponseEntity<D> update(@RequestBody D d) throws URISyntaxException {
		if (d.getId() == null) {
			return create(d);
		}
		E result = baseService.save(convertDTOToEntity(d));
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, d.getId().toString()))
				.body(convertEntityToDTO(result));
	}

	@GetMapping("/pageable")
	@Timed
	public ResponseEntity<List<D>> getAll(@ApiParam Pageable pageable) {
		Page<E> page = baseService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "*/getAll");
		return new ResponseEntity<>(convertListEntityToDTO(page.getContent()), headers, HttpStatus.OK);
	}

	@GetMapping
	@Timed
	public ResponseEntity<List<D>> getAllNotPageable() {
		List<E> page = baseService.findAll();
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "*/getAll");
		return new ResponseEntity<>(convertListEntityToDTO(page), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Timed
	public ResponseEntity<D> getById(@PathVariable PK id) {
		E e = baseService.findOne(id);

		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(convertEntityToDTO(e)));
	}

	@DeleteMapping("/{id}")
	@Timed
	public ResponseEntity<Void> deleteById(@PathVariable PK id) {
		baseService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	@ExceptionHandler
	public void handleException() {
		System.out.println("exception occured");
	}

}
