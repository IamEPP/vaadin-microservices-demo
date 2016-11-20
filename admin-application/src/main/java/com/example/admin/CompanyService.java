package com.example.admin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

@FeignClient(value = "biz-application", fallback = CompanyServiceFallback.class)
public interface CompanyService extends Serializable {

    @RequestMapping("/companies")
    Resources<Company> findAll();

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    Company add(@RequestBody Company company);

    @RequestMapping(value = "/companies/{id}", method = RequestMethod.PUT)
    Company update(@PathVariable("id") Long id, @RequestBody Company company);

    @RequestMapping(value = "/companies/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Long id);

}
