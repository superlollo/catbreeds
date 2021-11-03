package controllers;


import assembler.GetCatBreedsAssembler;
import commands.GetCatBreedsCommand;
import dto.GetCatBreedsOutputBin;
import dto.GetCatBreedsResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.net.HttpURLConnection.*;

@RestController
@RequestMapping(path = "/breeds", produces={MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
@AllArgsConstructor
@Slf4j
public class GetCatBreedsController {

    @Autowired
    private GetCatBreedsCommand command;

    private GetCatBreedsAssembler assembler;

    @GetMapping(path = "/getBreeds")
    @ApiOperation(
            value = "Get all cat breeds.",
            tags = "breeds"
    )
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "OK"),
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad request"),
            @ApiResponse(code = HTTP_FORBIDDEN, message = "Forbidden"),
            @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "Server error")
    })
    public ResponseEntity<GetCatBreedsResource> getCorrieri() throws Exception, GenericException {

        GetCatBreedsOutputBin outputBin = command.execute();
        GetCatBreedsResource resource = assembler.toResource(outputBin);

        return ResponseEntity.ok(resource);

    }

}
