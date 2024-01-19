package com.mentoria.integraProducts.gateways.outputs.http.feign;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.mentoria.integraProducts.gateways.outputs.http.resources.SellerResource;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "sellerIntegration", url = "${integration.seller.url}", decode404 = true)
public interface SellersFeignIntegration {
  @GetMapping(path = "/sellers/{sellerId}", consumes = APPLICATION_JSON_VALUE)
  Optional<SellerResource> get(@PathVariable("sellerId") final String sellerId);
}
