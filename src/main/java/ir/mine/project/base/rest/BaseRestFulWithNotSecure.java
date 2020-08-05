package ir.mine.project.base.rest;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import ir.mine.project.base.domin.BaseEntity;
import ir.mine.project.base.dozer.DozerConvertorGenericMethod;
import ir.mine.project.base.dto.BaseDTO;
import ir.mine.project.base.dto.empty.ResultMessageList;
import ir.mine.project.base.service.BaseService;
import ir.mine.project.web.rest.util.PaginationUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRestFulWithNotSecure<E extends BaseEntity<PK>, D extends BaseDTO<PK>, PK extends Serializable,
        Service extends BaseService<E, PK>>
        extends DozerConvertorGenericMethod<E, D> {


    public final Service baseService;


    public String ENTITY_NAME;

    public BaseRestFulWithNotSecure(Service baseService) {
        this.baseService = baseService;
    }


    @GetMapping("/pageable")
    @Timed
    public ResponseEntity<List<D>> getAllNotSecure(@ApiParam Pageable pageable) {
        Page<E> page = baseService.findAllNotSecure(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "*/getAll");
        return new ResponseEntity<>(convertListEntityToDTO(page.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping
    @Timed
    public ResponseEntity<List<D>> getAllNotSecure() {
        List<E> page = baseService.findAllNotSecure();
        return new ResponseEntity<>(convertListEntityToDTO(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<D> getByIdNotSecure(@PathVariable PK id) {
        E e = baseService.findOneNotSecure(id);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(convertEntityToDTO(e)));
    }


    @GetMapping("/pageable/rm")
    @Timed
    public ResponseEntity<ResultMessageList<D>> getAllWithResultMessage(@ApiParam Pageable pageable) {
        Page<E> page = baseService.findAllNotSecure(pageable);
        return new ResponseEntity<>(new ResultMessageList<>(
                convertListEntityToDTO(page.getContent()),
                page.getTotalElements()),
                HttpStatus.OK);
    }

    @GetMapping("/rm")
    @Timed
    public ResponseEntity<ResultMessageList<D>> getAllWithResultMessage() {
        List<E> page = baseService.findAllNotSecure();
        return ResponseEntity.ok(new ResultMessageList<>(
                convertListEntityToDTO(page),
                (long) page.size()));
    }

}
