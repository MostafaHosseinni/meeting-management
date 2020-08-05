package ir.mine.project.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.RoomService;
import ir.mine.project.service.RoomServiceService;
import ir.mine.project.service.dto.RoomServiceDTO;

/**
 * REST controller for managing RoomService.
 */
@RestController
@RequestMapping("/RoomService")
public class RoomServiceResource
		extends BaseRestFulServiceSecure<RoomService, RoomServiceDTO, Long, RoomServiceService> {

	private static final String ENTITY_NAME = "roomservice";

	public RoomServiceResource(RoomServiceService roomserviceService) {
		super(roomserviceService);
		setENTITY_NAME(ENTITY_NAME);
	}
}
