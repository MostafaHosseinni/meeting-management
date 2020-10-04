package ir.mine.project.base.rest;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
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
import ir.mine.project.base.dto.IBaseDTO;
import ir.mine.project.base.service.BaseService;
import ir.mine.project.web.rest.util.HeaderUtil;
import ir.mine.project.web.rest.util.PaginationUtil;
import lombok.Getter;
import lombok.Setter;


/**
 *  @author Hossein
 *  fdakf;ad dfaj dfdfa sad
 *   dfdas;fkj; 
 *   sdjklafj 
 *   dfsaj 
 */


@Setter
@Getter
public class BaseRestFulServiceSecure<E extends BaseEntity<PK>, D extends BaseDTO<PK>, PK extends Serializable, Service extends BaseService<E, PK>, Brief extends IBaseDTO<PK>>
		extends DozerConvertorGenericMethod<E, D> {

	public final Service baseService;
	public String ENTITY_NAME;

	public Class<Brief> briefClass;

	@SuppressWarnings("unchecked")
	public BaseRestFulServiceSecure(Service baseService) {
		this.baseService = baseService;

		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
			if (genericSuperclass != null && genericSuperclass.getActualTypeArguments() != null
					&& genericSuperclass.getActualTypeArguments().length > 0) {
				if (genericSuperclass.getActualTypeArguments()[4] instanceof Class) {
					briefClass = (Class<Brief>) genericSuperclass.getActualTypeArguments()[4];
				}
			}
		}

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

	@PostMapping("/searchByExample")
	@Timed
	public ResponseEntity<List<D>> searchByExample(@RequestBody D d) throws URISyntaxException {
		if (d == null) {
			return getAllNotPageable();
		}
		return new ResponseEntity<>(convertListEntityToDTO(baseService.searchByExample(convertDTOToEntity(d))),
				HttpStatus.OK);
	}

	@PostMapping("/countByExample")
	@Timed
	public ResponseEntity<Long> countByExample(@RequestBody D d) throws URISyntaxException {
		if (d == null) {
			return ResponseEntity.ok(baseService.countAll());
		}

		return new ResponseEntity<>(baseService.countByExample(convertDTOToEntity(d)), HttpStatus.OK);
	}

	@GetMapping("/briefPageable")
	@Timed
	public ResponseEntity<List<Brief>> briefPageable(@ApiParam Pageable pageable) {
		Page<Brief> page = baseService.findAllWithProjection(briefClass, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "*/briefPageable");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@PostMapping("/briefSearchByExample")
	@Timed
	public ResponseEntity<List<Brief>> briefSearchByExample(@RequestBody D d, @ApiParam Pageable pageable)
			throws URISyntaxException {
		if (d == null) {
			return briefPageable(pageable);
		}
		Page<Brief> page = baseService.searchByExampleWithProjection(convertDTOToEntity(d), briefClass, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "*/briefSearchByExample");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

}
